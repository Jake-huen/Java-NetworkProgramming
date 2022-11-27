package Socket;

import java.io.IOException;
import java.net.ServerSocket;

public class RandomPort {

    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(0);
            System.out.println("This server runs on port "
                + server.getLocalPort()+", Address "+server.getInetAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
