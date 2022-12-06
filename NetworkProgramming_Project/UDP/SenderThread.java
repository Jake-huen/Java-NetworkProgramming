package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderThread extends Thread{

    private InetAddress server;
    private DatagramSocket socket;
    private int port;
    private volatile boolean stopped = false;

    SenderThread(DatagramSocket socket, InetAddress address, int port){
        this.server = address;
        this.port = port;
        this.socket = socket;
        this.socket.connect(server,port); // 이 서버와 port와만 communication 하겠다.
    }

    public void halt(){
        this.stopped = true;
    }

    @Override
    public void run(){
        try{
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); //키보드 입력 받아들인다.
            while(true){
                if(stopped) return;
                String theLine = userInput.readLine();
                if(theLine.equals(".")) break;
                byte[] data = theLine.getBytes("UTF-8");
                DatagramPacket output = new DatagramPacket(data,data.length,server,port);
                socket.send(output);
                Thread.yield(); // 다른 Thread가 CPU control 가져갈 수 있게.
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
