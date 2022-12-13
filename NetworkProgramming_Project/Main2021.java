import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main2021 {

    public static void main(String[] args){
        String s = "This is a test";
        try {
            InetAddress ia = InetAddress.getByName("www.ibiblio.org");
            byte[] data = s.getBytes("UTF-8");
            int port =7;
            DatagramPacket dp = new DatagramPacket(data,data.length,ia,port);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}