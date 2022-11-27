package Socket;

import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class SingleFileHTTPServer {

    // Logger를 이용해서 logging을 실행한다.
    private static final Logger logger = Logger.getLogger("SingleFileHTTPServer");

    private final byte[] content; // 내가 보낼 content
    private final byte[] header; // response header
    private final int port; // HTTP -> 80번
    private final String encoding;

    public SingleFileHTTPServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException{
        this(data.getBytes(encoding),encoding,mimeType,port); // 아래 생성자에 있음
    }
    public SingleFileHTTPServer(byte[] data, String encoding, String mimeType, int port){
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\r\n"  //header에서는 무조건 \r\n
                + "Server: OneFile 2.0\r\n"
                + "Content-length: "+ this.content.length + "\r\n"
                + "Content-type: "+mimeType + "; charset="+encoding+"\r\n\r\n"; // header의 끝을 의미
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public void start(){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try(ServerSocket server = new ServerSocket(this.port)){
            logger.info("Accepting connections on port "+server.getLocalPort());
            logger.info("Data to be sent:");
            logger.info(new String(this.content,encoding)); // 어떤 데이터가 보내질 것이다.

            while(true){
                try{
                    Socket connection = server.accept(); // connection request 기다림
                    pool.submit(new HTTPHandler(connection));
                } catch (IOException ex) {
                    logger.log(Level.WARNING, "Exception accepting connection",ex);
                } catch (RuntimeException ex) {
                    logger.log(Level.SEVERE, "Unexpected error",ex);
                }
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not start server",ex);
        }
    }
    private class HTTPHandler implements Callable<Void>{
        private final Socket connection;

        HTTPHandler(Socket connection){
            this.connection = connection;
        }

        @Override
        public Void call() throws IOException{
            try{
                OutputStream out = new BufferedOutputStream(
                        connection.getOutputStream()
                );
                InputStream in = new BufferedInputStream(
                        connection.getInputStream()
                );
                // 첫번째 라인만 필요하다. -> 보낼 파일이 하나이기 때문에. 들어오기만 하면 정해진 파일 보낸다.
                StringBuilder request = new StringBuilder(80); // header line을 넣기 위함
                while(true){
                    int c = in.read(); // InputStream에서 하나씩 읽음
                    if(c=='\r' || c=='\n' || c==-1) break;
                    request.append((char) c);
                }
                if(request.toString().indexOf("HTTP/")!=-1){ // -1이면 포함하지 않는다는 뜻
                    out.write(header); //HTTP/를 포함하면 header를 쓴다
                }
                out.write(content);
                out.flush();
            } catch (IOException ex){
                logger.log(Level.WARNING,"Error writing to client",ex);
            } finally {
                connection.close();
            }
            return null;
        }
    }

    public static void main(String[] args){
        int port;
        try {
            port = 80; // HTTP는 80이니까
            if(port<1 || port > 65535) port = 80;
        } catch (RuntimeException ex){
            port = 80;
        }
        String encoding = "UTF-8";
        // if(args.length >2) encoding = args[2];
        String fname = "./img_logo_ku.png";
        try{
            Path path = Paths.get(fname);
            byte[] data = Files.readAllBytes(path);

            String contentType = URLConnection.getFileNameMap().getContentTypeFor(fname);
            SingleFileHTTPServer server = new SingleFileHTTPServer(data,encoding,contentType,port);
            server.start();

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not start server",ex);
        }
    }
}
