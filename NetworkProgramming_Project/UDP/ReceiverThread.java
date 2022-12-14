package UDP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiverThread extends Thread{

    private DatagramSocket socket;
    private volatile boolean stopped = false;

    ReceiverThread(DatagramSocket socket){
        this.socket = socket;
    }

    public void halt(){
        this.stopped = true;
    }

    @Override
    public void run(){
        byte[] buffer = new byte[65507];
        while(true){
            if(stopped) return;
            DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
            try{
                socket.receive(dp); // packet을 넣기
                String s = new String(dp.getData(),0,dp.getLength(),"UTF-8");
                System.out.println(s);
                Thread.yield();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
