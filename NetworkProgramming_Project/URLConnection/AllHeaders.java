package URLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AllHeaders {

    public static void main(String[] args){
        try{
            URL u = new URL("http://www.oreilly.com");
            URLConnection uc = u.openConnection();
            String headerfield;
            int check =0;
            while((headerfield = uc.getHeaderField(check))!=null){
                System.out.println("HeaderFieldKey : " +uc.getHeaderFieldKey(check));
                System.out.println("HeaderField : "+headerfield);
                System.out.println();
                check+=1;
            }
            System.out.println(uc.getHeaderField(0));
            System.out.println(uc.getHeaderFieldKey(1));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
