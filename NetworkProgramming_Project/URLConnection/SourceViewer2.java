package URLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

    public static void main(String[] args){
        try{
            URL u = new URL("https://www.orielly.com");
            URLConnection uc = u.openConnection();
            // uc.setConnectTimeout(1);
            try(InputStream raw = uc.getInputStream()){
                uc.setConnectTimeout(1); //connection이 이미 establish된 후에 setConnectTimeout을 하니까 그 다음번 connection부터 적용
                InputStream buffer = new BufferedInputStream(raw);
                Reader reader = new InputStreamReader(buffer);
                int c;
                while((c=reader.read())!=-1){
                    System.out.print((char) c);
                }
            }
        }catch (MalformedURLException ex){
            System.err.println(args[0] + " is not a parseable URL");
        }catch (IOException ex){
            System.err.println(ex);
        }
    }

}
