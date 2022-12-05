package InternetAddresses;

import java.net.*;

public class AddressResolutionByName {

    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getByName("winter.konkuk.ac.kr");//DNS contact해서 IP주소도 제공
            System.out.println(address);

            InetAddress address2 = InetAddress.getByName("202.30.38.108"); // host에 IP주소가 들어가면 HostName을 반환하면 된다.
            System.out.println(address2);
            System.out.println(address2.getHostName());

            InetAddress address4 = InetAddress.getByName("www.yahoo.com");
            System.out.println("대표 : "+address4); // 이렇게 하면 대표 하나만 나옴.

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
