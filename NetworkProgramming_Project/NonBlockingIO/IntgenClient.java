package NonBlockingIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class IntgenClient {

    public static int DEFAULT_PORT = 1919;

    public static void main(String[] args){
        int port = DEFAULT_PORT;

        try{
            SocketAddress address = new InetSocketAddress("localhost",port); // local에서 돌아가고 socket address 만들어냄
            SocketChannel client = SocketChannel.open(address); //
            ByteBuffer buffer = ByteBuffer.allocate(4);
            IntBuffer view = buffer.asIntBuffer(); // view는 버퍼를 intbuffer로 생각해라.

            for(int expected =0;;expected++){
                client.read(buffer);
                int actual = view.get();
                buffer.clear(); // 이게 없다면 -> buffer에 써야되는데 clear안하면 buffer의 position이 limit에 가있기 때문에 remaining이 없어서 buffer의 내용이 바뀌지 않고, 0이 나오게 되므로 expect는 1인데 buffer에 있는건 0이라서 break가 된다.
                view.rewind();

                if(actual!=expected){
                    System.err.println("Expected "+expected+"; received "+actual);
                    break;
                }
                System.err.println(actual);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
