package UDP;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPDiscardServerwithChannels {

    public final static int PORT = 9;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args){

        try{
            DatagramChannel channel = DatagramChannel.open(); //channel open
            DatagramSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(PORT);
            socket.bind(address); // datagram channel이 binding됨
            ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
            while(true){
                SocketAddress client = channel.receive(buffer); //버퍼에 저장이 됨.
                buffer.flip(); // 버퍼로부터 읽을 준비를 함
                System.out.println(client + "says ");
                while(buffer.hasRemaining()) System.out.write(buffer.get());
                System.out.println();
                buffer.clear(); // 쓰기 위해서 준비.
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
