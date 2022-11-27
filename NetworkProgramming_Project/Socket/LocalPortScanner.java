package Socket;

import java.io.IOException;
import java.net.ServerSocket;

public class LocalPortScanner {

    public static void main(String[] args){
        for(int port = 1;port<=65535;port++){
            try {
                ServerSocket server = new ServerSocket(port);
                // port가 이미 사용되고 있으면 이 port에 binding 되는 것이 불가능하다.
            } catch (IOException e) {
                System.out.println("There is a server on port "+port+".");
            }
        }
    }
}
