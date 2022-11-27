package Socket;

import java.io.IOException;
import java.net.*;

public class Practice {

    public static void main(String[] args){
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress("time.nist.gov",13);
            socket.connect(address);
            System.out.println(address);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
