package NonBlockingIO;

import java.nio.ByteBuffer;

public class BufferPropertyTest {

    public static void main(String[] args){
        ByteBuffer buf = ByteBuffer.allocate(10);
        System.out.println("initial position: "+buf.position());
        System.out.println("initial limit: "+buf.limit());
        System.out.println("capacity: "+buf.capacity());
        buf.position(4);
        buf.mark();
        System.out.println(buf);
        buf.reset();



        ByteBuffer buffer = ByteBuffer.allocateDirect(12);
        buffer.put((byte)10).put((byte)11).put((byte)12).put((byte)13).put((byte)14).put((byte)15);
        buffer.mark();
        System.out.println(buffer);
        buffer.put((byte)16).put((byte)17).put((byte)18).put((byte)19).put((byte)20).put((byte)21);
        buffer.reset();
        buffer.limit(11);
        System.out.println(buffer);
    }
}
