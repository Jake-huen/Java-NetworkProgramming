package URL_URI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncoderTest {

    public static void main(String[] args){
        try{
            System.out.println(URLEncoder.encode("This string has spaces","UTF-8"));
            System.out.println(URLEncoder.encode("This*string*has*asterisks","UTF-8"));
            System.out.println(URLEncoder.encode("This%string%has%percent%signs","UTF-8")); // %25
            System.out.println(URLEncoder.encode("This+string+has+pluses","UTF-8")); // %2B
            System.out.println(URLEncoder.encode("This/string/has/slashes","UTF-8")); // %2F
            System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks","UTF-8")); // %22
            System.out.println(URLEncoder.encode("This:string:has:colons","UTF-8"));// %3A
            System.out.println(URLEncoder.encode("This~string~has~tildes","UTF-8")); // %7E
            System.out.println(URLEncoder.encode("This(string)has(parentheses)","UTF-8")); // %28
            System.out.println(URLEncoder.encode("This.string.has.periods","UTF-8"));
            System.out.println(URLEncoder.encode("This=string=has=equals=signs","UTF-8")); // %3D
            System.out.println(URLEncoder.encode("This&string&has&ampersands","UTF-8")); // %26
        }catch(UnsupportedEncodingException ex){
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }
}
