package URLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {

    public static void main(String[] args){
        try{
            String encoding = "ISO-8859-1";
            URL u = new URL("http://ecampus.konkuk.ac.kr");
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType();
            int encodingStart = contentType.indexOf("charset=");
            System.out.println(contentType);
            // System.out.println(encodingStart);
            if(encodingStart!=-1){
                encoding = contentType.substring(encodingStart+8);
            }
            InputStream in = uc.getInputStream();
            InputStream buffer = new BufferedInputStream(in);
            Reader r = new InputStreamReader(buffer);
            int c;
            while((c=r.read())!=-1){
                System.out.print((char) c);
            }
            r.close();
        }catch(MalformedURLException ex){
            System.err.println(args[0] + " is not a parseable URL");
        }catch(UnsupportedEncodingException ex){
            System.err.println(
                    "Server sent an encoding Java does not support: "+ex.getMessage()
            );
        }catch(IOException ex){
            System.err.println(ex);
        }
    }
}
