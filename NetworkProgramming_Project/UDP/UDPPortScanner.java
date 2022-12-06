package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPPortScanner {

    public static void main(String[] args){
        for(int port = 1024; port<=65535; port++){
            try{
                DatagramSocket server = new DatagramSocket(port);
                server.close();
            } catch (SocketException e) {
                System.out.println("There is a server on port "+ port + ".");
                // 이미 누가 사용하고 있다.
            }
        }
    }
}
