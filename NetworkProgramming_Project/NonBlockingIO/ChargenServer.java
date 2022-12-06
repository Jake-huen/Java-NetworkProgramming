package NonBlockingIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ChargenServer {

    public static int DEFAULT_PORT = 19;

    public static void main(String[] args){

        int port = 19; // 들을 PORT를 정해준다.
        System.out.println("Listening for connections on port "+port);

        byte[] rotation = new byte[95*2]; // buffer의 크기를 정해준다.
        for(byte i=' '; i<='~'; i++){ // buffer가 ~일 때까지
            // System.out.println("this : " + (i-' '));
            rotation[i-' '] = i;
            rotation[i+95-' '] = i;
        }

        ServerSocketChannel serverChannel; // 서버용 채널
        Selector selector; // 열려있는 채널을 찾기 위함
        try {
            serverChannel = ServerSocketChannel.open(); // ServerSocketChannel을 열기.
            ServerSocket ss = serverChannel.socket(); // ServerSocketChannel에 Socket달기
            InetSocketAddress address = new InetSocketAddress(port); // Socket주소 포트 사용
            ss.bind(address); // Socket에 주소 달기
            serverChannel.configureBlocking(false); // NonBlocking mode로 동작
            selector = Selector.open(); // 모든 connection을 돌아서 processing 준비 되어있는 것 찾기
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 새로운 connection accept할 준비 되어있음?
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(true){
            try{
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> readKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel(); // 채널 다시 복원
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from "+client);
                        client.configureBlocking(false);
                        SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
                        ByteBuffer buffer = ByteBuffer.allocate(74);
                        buffer.put(rotation,0,72);
                        buffer.put((byte) '\r');
                        buffer.put((byte) '\n');
                        buffer.flip();
                        key2.attach(buffer);
                    } else if(key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if(!buffer.hasRemaining()){
                            // buffer를 다음 라인으로 채움
                            buffer.rewind();
                            // 예전 first character 가져옴
                            int first = buffer.get();
                            // buffer의 data 바꿈
                            buffer.rewind();
                            // rotation에서 새로운 character 찾음
                            int position = first - ' ' + 1;
                            // rotation에 buffer 복사
                            buffer.put(rotation, position, 72);
                            // line break를 buffer 끝에 넣음
                            buffer.put((byte) '\r');
                            buffer.put((byte) '\n');
                            // buffer를 다시 마련
                            buffer.flip();
                        }
                        client.write(buffer);
                    }
                }  catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex){}
                }
            }
        }

    }
}
