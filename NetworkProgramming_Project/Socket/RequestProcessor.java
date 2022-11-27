package Socket;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Logger;

public class RequestProcessor implements Runnable{

    private static final Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());

    private File rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;

    public RequestProcessor(File rootDirectory, String inedexFileName, Socket connection){
        if(rootDirectory.isFile()){ //rootDirectory가 파일이면
            throw new IllegalArgumentException("rootDirectory must be a directory, not a file");
        }
        try{
            rootDirectory = rootDirectory.getCanonicalFile(); // 절대경로를 가져온다.
        } catch (IOException ex){}
        this.rootDirectory = rootDirectory;

        if(indexFileName!=null) this.indexFileName = inedexFileName;
        this.connection = connection;
    }

    @Override
    public void run() {
        // for security checks
        String root = rootDirectory.getPath();
        System.out.println("root"+root);
        try{
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw);
            Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()),"US-ASCII");

            StringBuilder requestline = new StringBuilder(); // 첫번째 라인
            while(true){
                int c = in.read();
                if(c=='\r' || c=='\n') break; // headerline의 끝이라면
                requestline.append((char) c);
            }
            String get = requestline.toString();

            logger.info(connection.getRemoteSocketAddress() + " " + get);

            String[] tokens = get.split("\\s+");
            String method = tokens[0]; // method
            String version = "";

            if(method.equals("GET")){ // mehtod가 GET하고 같다
                String fileName = tokens[1];
                if(fileName.endsWith("/")) fileName += indexFileName; // 파일이름이 /로 끝난다 (파일 이름이 directory)
                String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
                if(tokens.length > 2){ // http version이 있다
                    version = tokens[2];
                }

                File theFile = new File(rootDirectory, fileName.substring(1,fileName.length())); // 슬래쉬 부분 떼기(첫번째 떼고)
                if(theFile.canRead() && theFile.getCanonicalPath().startsWith(root)){ // 읽기 권한이 있고, root로 시작하면 | root와 canonical path가 동일하면
                    byte[] theData = Files.readAllBytes(theFile.toPath());
                    if(version.startsWith("HTTP/")){ //directory 인것 (send a MIME header)
                        // root가 a.com/file/ -> a.com/file/../~~
                        sendHeader(out,"HTTP/1.0 200 OK",contentType,theData.length);
                    }
                    // send the file -> it may be an image or other bindary data
                    raw.write(theData);
                    raw.flush();
                }else{ // file을 못 찾을때
                    String body = new StringBuilder("<HTML>\r\n")
                            .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                            .append("</HEAD>\r\n")
                            .append("<BODY>")
                            .append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
                            .append("</BODY></HTML>\r\n").toString();
                    if(version.startsWith("HTTP/")){
                        sendHeader(out,"HTTP/1.0 404 File Not Found",
                                "text/html; charset=utf-8",body.length()
                        );
                        System.out.println(body);
                        out.write(body);
                        out.flush();
                    }
                    else{ // GET method가 아니다!
                        String body2 = new StringBuilder("<HTML>\r\n")
                                .append("<HEAD><TITLE>Not Implemented</TITLE>\r\n")
                                .append("</HEAD>\r\n")
                                .append("<BODY>")
                                .append("<H1>HTTP Error 501: Not Implemented</H1>\r\n")
                                .append("</BODY></HTML>\r\n").toString();
                        if(version.startsWith("HTTP/")){
                            sendHeader(out,"HTTP/1.0 501 Not Implemented",
                                    "text/html; charset=utf-8",body.length()
                                    );
                        }
                        System.out.println(body);
                        out.write(body);
                        out.flush();
                    }
                }
            } else if(method.equals("DELETE")){
                String fileName = tokens[1];
                if(fileName.endsWith("/")) fileName += indexFileName; // 파일이름이 /로 끝난다 (파일 이름이 directory)
                String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
                if(tokens.length > 2){
                    version = tokens[2];
                }
                File theFile = new File(rootDirectory, fileName.substring(1,fileName.length()));
                RequestDelete rd = new RequestDelete(rootDirectory,theFile, fileName.substring(1,fileName.length()));
                String result = rd.startDelete();
                sendHeader(out,"HTTP/1.0 404 File Not Found",
                        "text/html; charset=utf-8",result.length()
                );
                out.write(result);
                out.flush();
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendHeader(Writer out, String responseCode, String contentType, int length) throws IOException{
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: "+now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: "+length+"\r\n");
        out.write("Content-type: "+contentType+"\r\n\r\n");
        out.flush();
    }


}
