package URLConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.io.*;
import java.net.*;
import java.util.*;

public class LastModifiedExercise {
    public static void main(String[] args){
        try{
            URL u = new URL("https://www.google.com");
            URLConnection uc = u.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
            http.setRequestMethod("OPTIONS");
            System.out.println(uc.getHeaderField(0));
            for(int j=1; ;j++){
                String header = uc.getHeaderField(j); // 내가 request한 메서드 같은 것들이 있음
                String key = uc.getHeaderFieldKey(j);
                if(header==null||key==null) break;
                System.out.println(uc.getHeaderFieldKey(j)+": "+header);
            }
            System.out.println();
            // Response Header


            // content 부분 -> message body 출력
            InputStream raw = http.getInputStream();
            InputStream buffer = new BufferedInputStream(raw);
            Reader reader = new InputStreamReader(buffer);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{

        }
        System.out.println();
    }
}
