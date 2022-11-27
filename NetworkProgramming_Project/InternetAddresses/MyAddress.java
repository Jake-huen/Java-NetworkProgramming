package InternetAddresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {

    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
            System.out.println(address.getHostAddress());
        }catch (UnknownHostException ex){
            System.out.println("Could not find this computer's address");
        }
    }
}
