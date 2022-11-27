package InternetAddresses;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachabilityTest {

    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getByName("www.naver.com");
            // InetAddress address = InetAddress.getByName("203.252.148.149");
            // byte[] addr = {(byte)202,30,38,108};
            // byte[] addr = {10,0,1,4};
            int timeout = 3000;
            int ttl = 10;

            if(address.isReachable(timeout)){
                System.out.println(address.getHostName()+" CAN BE reached within "+timeout + "ms");
            }else{
                System.out.println(address.getHostName()+" CANNOT BE reached within "+timeout+"ms");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
