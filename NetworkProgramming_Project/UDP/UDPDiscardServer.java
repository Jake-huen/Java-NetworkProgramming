package UDP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPDiscardServer {

    public final static int PORT = 9;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args){
        byte[] buffer = new byte[MAX_PACKET_SIZE];
        try(DatagramSocket server = new DatagramSocket(PORT)){
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length); // 받는 입장이니까 remote나 port 필요없음
            while(true){
                try{
                    server.receive(packet); // socket으로부터 읽어서 packet에 저장. // blocking mode
                    String s = new String(packet.getData(),0,packet.getLength(),"8859_1");
                    System.out.println(packet.getAddress() + " at port "+packet.getPort() + " says "+s);
                    // packet.setLength(buffer.length); // lenght를 set하는거.. 필요함? -> 65507
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
