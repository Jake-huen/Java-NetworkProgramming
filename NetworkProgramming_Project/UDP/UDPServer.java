package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.*;

public abstract class UDPServer implements Runnable{
    private final int bufferSize;
    private final int port;
    private final Logger logger = Logger.getLogger(UDPServer.class.getCanonicalName());
    private volatile boolean isShutDown = false;

    public UDPServer(int port, int bufferSize){
        this.bufferSize = bufferSize;
        this.port = port;
    }

    public UDPServer(int port){
        this(port,8192);
    }

    @Override
    public void run(){
        byte[] buffer = new byte[bufferSize];
        try(DatagramSocket socket = new DatagramSocket(port)){ // socket을 만들고 듣고 있음
            socket.setSoTimeout(10000);
            while(true){
                if(isShutDown) return;
                DatagramPacket incoming = new DatagramPacket(buffer,buffer.length); // buffer size만큼 data packet을 만듬
                try{
                    socket.receive(incoming); // socket에 datagram packet을 넣는다.
                    this.respond(socket,incoming); // respond를 해준다. socket과 datagram packet을 넣어서
                } catch (SocketTimeoutException ex) {
                    if(isShutDown) return; // 10초동안 아무것도 들어오지 않았다, shutDown method가 어디에서 호출되어야 한다.
                } catch(IOException ex){
                    logger.log(Level.WARNING, ex.getMessage(), ex);
                }
            }
        } catch (SocketException ex) {
            logger.log(Level.SEVERE, "Could not bind to port: "+port,ex);
        }
    }

    // respond는 abstract로 선언해서 serverclass가 구현하면 된다.
    public abstract void respond(DatagramSocket socket, DatagramPacket request) throws IOException;

    public void shutDown() {
        this.isShutDown = true;
    }

}
