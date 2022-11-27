package NonBlockingIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {

    public static int DEFAULT_PORT = 19;

    public static void main(String[] args){
//        if(args.length ==0){
//            System.out.println("Usage: java ChargenClient host [port]");
//            return;
//        }

        int port = DEFAULT_PORT;
//        try{
//             port = Integer.parseInt(args[1]);
//            port = 19;
//        } catch (RuntimeException ex){
//            port = DEFAULT_PORT;
//        }

        try{
            SocketAddress address = new InetSocketAddress("127.0.0.1",port);
            SocketChannel client = SocketChannel.open(address);

            ByteBuffer buffer = ByteBuffer.allocate(74);
            WritableByteChannel out = Channels.newChannel(System.out);

            File f = new File("file.txt");
            WritableByteChannel out2 = Channels.newChannel(new FileOutputStream(f));
            int i=0;
            while(client.read(buffer)!=-1){
                System.out.println(i++);
                buffer.flip();
                out.write(buffer);
                out2.write(buffer);
                buffer.clear();
//                if(i>100){
//                    break;
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
