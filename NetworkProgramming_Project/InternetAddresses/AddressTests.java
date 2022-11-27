package InternetAddresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressTests {

    public static void main(String[] args){
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
            String dottedQuad = ia.getHostAddress();
            System.out.println("My Address is "+dottedQuad);

            System.out.println("This is IPv"+ getVersion(ia));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    private static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if(address.length ==4 ) return 4;
        else if(address.length ==16) return 6;
        else return -1;
    }
}
