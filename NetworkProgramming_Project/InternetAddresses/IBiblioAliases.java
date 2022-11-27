package InternetAddresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IBiblioAliases {

    public static void main(String[] args){
        try{
            InetAddress ibiblio = InetAddress.getByName("www.konkuk.ac.kr");
            InetAddress helios = InetAddress.getByName("winter.konkuk.ac.kr");
            if(ibiblio.equals(helios)){
                System.out.println("www.konkuk.ac.kr is the same as winter.konkuk.ac.kr");
            }else{
                System.out.println("www.konkuk.ac.kr is not the same as winter.konkuk.ac.kr");
            }
        }catch (UnknownHostException ex){
            System.out.println("Host lookup failed");
        }
    }
}
