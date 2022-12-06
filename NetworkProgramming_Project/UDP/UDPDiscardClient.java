package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPDiscardClient {

    public final static int PORT = 9;

    public static void main(String[] args){

        String hostname = "localhost";

        try(DatagramSocket theSocket = new DatagramSocket()){
            InetAddress server = InetAddress.getByName(hostname);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String theLine = userInput.readLine();
                if(theLine.equals(".")) break;
                byte[] data = theLine.getBytes();
                DatagramPacket theOutput = new DatagramPacket(data,data.length,server,PORT);
                theSocket.send(theOutput);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
