package URLConnection;

import java.io.*;
import java.net.*;

public class SourceViewer4 {

    public static void main(String[] args){
        try {
            URL u = new URL("http://ecampus.konkuk.ac.kr/123.html");
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            try(InputStream raw = uc.getInputStream()){
                printFromStream(raw);
            }catch (IOException ex){
                printFromStream(uc.getErrorStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFromStream(InputStream raw) throws IOException{
        try(InputStream buffer = new BufferedInputStream(raw)){
            Reader reader = new InputStreamReader(buffer);
            int c;
            while((c=reader.read())!=-1){
                System.out.print((char) c);
            }
        }
    }
}
