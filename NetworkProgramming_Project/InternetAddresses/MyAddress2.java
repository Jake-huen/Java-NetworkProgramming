package InternetAddresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress2 {

    public static void main(String[] args){
        try{
            InetAddress ia = InetAddress.getLocalHost();
            String dottedQuad = ia.getHostAddress();
            System.out.println("My Address is "+ dottedQuad);

            byte[] address = {107,23,(byte)216,(byte)196};
            InetAddress ia2 = InetAddress.getByAddress(address);
            InetAddress ia2Named = InetAddress.getByAddress("named.com",address);
            System.out.println(ia2);
            System.out.println(ia2Named);
        }catch (UnknownHostException ex){
            System.out.println("I'm sorry. I don't know my own address. ");
        }
    }
}
