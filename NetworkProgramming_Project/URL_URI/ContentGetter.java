package URL_URI;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class ContentGetter {
    public static void main(String[] args){
        try{
            URL u = new URL("https://www.oreilly.com");
            Object o = u.getContent();
            InputStream r = (InputStream) o;
            int c;
            while((c=r.read())!=-1) System.out.print((char) c);
            r.close();
            System.out.println("I got a "+o.getClass().getName());
        }catch (MalformedURLException ex){
            System.err.println(args[0] +" is not a parseable URL");
        }catch (IOException ex){
            System.err.println();
        }
    }
}
