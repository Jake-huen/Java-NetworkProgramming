package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FastUDPDiscardServer extends UDPServer{

    public final static int DEFAULT_PORT = 9;

    public FastUDPDiscardServer(){
        super(DEFAULT_PORT); // 8192
    }

    public static void main(String[] args){
        UDPServer server = new FastUDPDiscardServer();
        Thread t = new Thread(server);
        t.start(); // run method실행됨 -> 계속 듣고 있는 것.
        // 듣고 있다가 incoming packet이 들어오면 respond가 호출이 된다.
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket request){
    }
}
