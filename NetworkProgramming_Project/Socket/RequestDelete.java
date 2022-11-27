package Socket;

import java.io.*;
import java.net.*;

public class RequestDelete{
    private File rootDirectory;
    private File theFile;
    private String fileTodelete;
    public RequestDelete(File rootDirectory,File theFile, String fileTodelete) {
        this.rootDirectory = rootDirectory;
        this.theFile = theFile;
        this.fileTodelete = fileTodelete;
    }

    public String startDelete(){
        String root = rootDirectory.getPath();
        try {
            URL url = new URL("http://localhost.com");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            uc.setRequestMethod("DELETE");
            if(theFile.canRead() && theFile.getCanonicalPath().startsWith(root)){
                theFile.delete();
                String body = new StringBuilder("<HTML>\r\n")
                        .append("<HEAD><TITLE>SUCCESS</TITLE>\r\n")
                        .append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>DELETE FILE</H1>\r\n")
                        .append(fileTodelete)
                        .append("</BODY></HTML>\r\n").toString();
                return body;
            } else{
                String body = new StringBuilder("<HTML>\r\n")
                        .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                        .append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
                        .append("</BODY></HTML>\r\n").toString();
                return body;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
