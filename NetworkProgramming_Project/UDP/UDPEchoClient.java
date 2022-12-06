package UDP;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {

    public final static int PORT = 7;

    public static void main(String[] args){
        String hostname = "localhost";

        try{
            InetAddress ia = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            SenderThread sender = new SenderThread(socket, ia, PORT);
            sender.start(); // 보내기만 한다.
            Thread receiver = new ReceiverThread(socket);
            receiver.start(); // 받는 부분
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
