package NonBlockingIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class IntgenServer {

    public static int DEFAULT_PORT = 1919;

    public static void main(String[] args){
        ServerSocketChannel serverChannel; // 일단 먼저 ServerSocketChannel을 정의
        Selector selector; // Channel을 선택할 수 있는 Selector를 생성한다.
        try{
            serverChannel = ServerSocketChannel.open(); // ServerSocketChannel을 open
//            ServerSocket ss = serverChannel.socket(); // ServerSocket
//            InetSocketAddress address = new InetSocketAddress(DEFAULT_PORT);
//            ss.bind(address);
            serverChannel.bind(new InetSocketAddress(DEFAULT_PORT)); // 서버 채널에 소켓을 만들어서 IP주소와 port로 바인딩해준다.
            serverChannel.configureBlocking(false); // NonBlocking으로 작동할 수 있도록 한다.
            selector = Selector.open(); // Selector를 열어서
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // channel을 selector에 등록해준다.
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while(true){
            try{
                selector.select();
            }catch (IOException ex){
                ex.printStackTrace();
                break;
            }

            Set<SelectionKey> readKeys = selector.selectedKeys(); // selector가 loop를 돌면서 준비된 Channel을 찾는다. 찾으면 SelectionKey로 반환하기 때문에 모든 SelectionKey의 집합을 찾아준다.
            Iterator<SelectionKey> iterator = readKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try{
                    if(key.isAcceptable()){ // accept operation에 대해서 정의된 key
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept(); // serverSocket에서 socketchannel을 만들어낸다.
                        System.out.println("Accepted connection from "+client);
                        client.configureBlocking(false);
                        SelectionKey key2 = client.register(selector,SelectionKey.OP_WRITE);
                        ByteBuffer output = ByteBuffer.allocate(4);
                        output.putInt(0);
                        output.flip(); // 다음에 read해서 채널에 쓰겠다.
                        key2.attach(output);
                    } else if(key.isWritable()){ // 소켓에 쓰는 경우
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        if(!output.hasRemaining()){
                            output.rewind();
                            int value = output.getInt(); // Int를 하나 가져온다.
                            System.out.println(value);
                            output.clear(); // 쓰기위해서
                            output.putInt(value+1); // Int를 넣는다.
                            output.flip(); // 읽기 위해서
                        }
                        client.write(output);
                    }
                } catch (IOException e) {
                    key.channel();
                    try{
                        key.channel().close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

}
