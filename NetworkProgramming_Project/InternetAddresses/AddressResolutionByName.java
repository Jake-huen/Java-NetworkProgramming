package InternetAddresses;

import java.net.*;

public class AddressResolutionByName {

    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getByName("winter.konkuk.ac.kr");//DNS contact해서 IP주소도 제공
            System.out.println(address);

            InetAddress address2 = InetAddress.getByName("202.30.38.108");
            System.out.println(address2);
            System.out.println(address2.getHostName());

            // www.daum.net이 mapping 가능한 모든 ip주소를 가지고 와라
            InetAddress[] addresses = InetAddress.getAllByName("www.yahoo.com");
            for(InetAddress address3:addresses)
                System.out.println(address3);
        }catch (UnknownHostException ex){
            System.out.println("Could not find www.konkuk.ac.kr");
        }
    }

    private static void displayInetAddressInformation (InetAddress address){
        System.out.println(address);
        System.out.println("CanonicalHostName: "+address.getCanonicalHostName());
        System.out.println("HostName: "+ address.getHostName());
        System.out.println("HostAddress: "+address.getHostAddress());
    }
}
