package URLConnection;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Last24 {

    public static void main(String[] args){
        Date today = new Date();
        long millisecondsPerDay = 24 * 60 * 60 * 1000; // 하루 24시간을 millisecond단위로 바꿈

        try{
            URL u = new URL("http://ecampus.konkuk.ac.kr");
            URLConnection uc = u.openConnection();
            System.out.println("Original if modified since: "
                + new Date(uc.getIfModifiedSince()));
            uc.setIfModifiedSince((new Date(today.getTime()
                - millisecondsPerDay)).getTime());
            System.out.println("Will retrieve file if it's modified since"
                +new Date(uc.getIfModifiedSince()));
            System.out.println(uc.getHeaderField(0));
            try(InputStream in = new BufferedInputStream(uc.getInputStream())){
                Reader r = new InputStreamReader(in);
                int c;
                while((c=r.read())!=-1){
                    System.out.print((char) c);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
