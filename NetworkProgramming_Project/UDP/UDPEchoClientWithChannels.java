package UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UDPEchoClientWithChannels {

    public final static int PORT = 7;
    public final static int LIMIT = 100; //100개만 보내고 받는 것

    public static void main(String[] args){

        SocketAddress remote;
        try{
            remote = new InetSocketAddress("localhost",PORT);
        } catch (RuntimeException ex){
            System.err.println("Usage: java UDPEchoClientWithChannels host");
            return;
        }

        try(DatagramChannel channel = DatagramChannel.open()){ // 채널 오픈
            channel.configureBlocking(false);
            channel.connect(remote); // remote한테만 보냄

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE); // 채널 등록 -> read or write

            ByteBuffer buffer = ByteBuffer.allocate(4);
            int n =0;
            int numbersRead = 0;
            while(true){
                if(numbersRead == LIMIT) break;
                selector.select(60000);
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                if(readyKeys.isEmpty() && n == LIMIT){
                    break;
                }
                else{
                    Iterator<SelectionKey> iterator = readyKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key= (SelectionKey) iterator.next();
                        iterator.remove();
                        if(key.isReadable()){
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read: "+echo);
                            numbersRead++;
                        }
                        if(key.isWritable()){
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Wrote: "+n);
                            n++;
                            if(n==LIMIT){
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            }
            System.out.println("Echoed "+numbersRead+ " out of "+LIMIT+" sent");
            System.out.println("Success rate: "+100.0*numbersRead/LIMIT + "%");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
