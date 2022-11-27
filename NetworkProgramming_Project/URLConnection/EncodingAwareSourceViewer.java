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
//            URL u = new URL("https://www.oreilly.com"); // 이 url은 encoding을 안줌
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType();
//            int encodingStart = contentType.indexOf("charset=");
//            if(encodingStart!=-1){ //text임
//                encoding = contentType.substring(encodingStart + 8); // charset= 이후 text 가져오기
//            }
//            System.out.println(contentType);
//            System.out.println(encoding); // 받는 내용이 무엇으로 encoding 되었는지 안 다음에
//            InputStream in = new BufferedInputStream(uc.getInputStream());
//            Reader r = new InputStreamReader(in,encoding); // encoding에 맞게 읽어들이기
//            int c;
//            while((c=r.read())!=-1){
//                System.out.print((char) c);
//            }
//            r.close();
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
