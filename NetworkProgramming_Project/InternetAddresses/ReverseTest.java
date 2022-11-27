package InternetAddresses;

import java.net.*;

public class ReverseTest {

    public static void main(String[] args){
        try{
            InetAddress ia = InetAddress.getByName("203.252.142.197");
            System.out.println(ia.getHostName());;
            System.out.println(ia.getCanonicalHostName());

            InetAddress ia2 = InetAddress.getByName("www.ibm.com");
            System.out.println(ia2);
            System.out.println(ia2.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
