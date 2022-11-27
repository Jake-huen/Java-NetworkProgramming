package URL_URI;

import java.net.*;

public class UriConstructorTest {

    public static void main(String[] args){
        try{
            URI voice = new URI("callme+82-2-450-1234");
            URI web = new URI("http://www.xml.com/pub/a/2003/09/17/stax.html#id=_hdc");
            URI book = new URI("urn:isbn:1-565-92870-9");
            // URL dddd = new URL("callme+82-2-450-1234");
            System.out.println(voice);
            System.out.println(web);
            System.out.println(book);
            // System.out.println(dddd);
        }catch (URISyntaxException ex){}
    }
}
