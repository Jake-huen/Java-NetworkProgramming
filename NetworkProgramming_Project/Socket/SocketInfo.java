package Socket;

import java.io.IOException;
import java.net.*;

public class SocketInfo {

    public static void main(String[] args){
        for(String host:args){
            try{
                Socket theSocket = new Socket(host,80);
                System.out.println("Connected to "+theSocket.getInetAddress()
                    + " on port " + theSocket.getPort() + " from port " +
                        theSocket.getLocalPort() + " of " +
                        theSocket.getLocalAddress()
                );
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
