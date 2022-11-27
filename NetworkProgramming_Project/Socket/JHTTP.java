package Socket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JHTTP {

    private static final Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());
    private static final int NUM_THREADS = 50;
    private static final String INDEX_FILE = "img_logo_ku.png";

    private final File rootDirectory;
    private final int port;

    public JHTTP(File rootDirectory, int port) throws IOException{
        if(!rootDirectory.isDirectory()){ // 만약에 rootDirectory가 아니다.(존재하지 않는다) (서버의 local directory에 없는 directory다) -> exception 발생시킴
            throw new IOException(rootDirectory + " does not exist as a directory");
        }
        this.rootDirectory = rootDirectory;
        this.port = port;
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try(ServerSocket server = new ServerSocket(port)){
            logger.info("Accepting connections on port "+server.getLocalPort());
            logger.info("Document Root: "+rootDirectory);

            while(true){
                try{
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request); // 소켓을 이용해서 RequestProcessor를 만든다
                    pool.submit(r); // request processor를 시작함
                }catch(IOException ex){
                    logger.log(Level.WARNING, "Error accepting connection",ex);
                }
            }
        }
    }
    public static void main(String[] args){
        // 서버에 있는 directory를 입력인자로 받아들인다
        File docroot;
        try{
            docroot = new File("C:\\Users\\tae77\\OneDrive\\바탕 화면\\NetworkProgramming\\src\\Socket");
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Usage: java JHTTP docroot port");
            return;
        }
        int port;
        try{
            port = 80;
            // port = Integer.parseInt(args[1]);
            // if(port<0 || port>65535) port = 80;
        } catch(RuntimeException ex){
            port = 80;
        }

        try{
            JHTTP webserver = new JHTTP(docroot,port); // JHTTP 객체 생성
            webserver.start();
        } catch (IOException ex){
            logger.log(Level.SEVERE, "Server could not start",ex);
        }
    }
}
