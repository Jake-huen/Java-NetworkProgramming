package NonBlockingIO;

import java.nio.ByteBuffer;

public class RelativeBufferTest {

    public static void main(String[] args){
        ByteBuffer buf = ByteBuffer.allocate(10);

        System.out.print("Init Position: "+buf.position());
        System.out.print(", Init Limit: "+buf.limit());
        System.out.println(", Init Capacity: "+buf.capacity());

        buf.mark();

        buf.put((byte) 10).put((byte) 11).put((byte) 12);

        buf.reset(); //mark로 돌아감

        System.out.println("Value: "+buf.get() + ", Position: "+buf.position());
        System.out.println("Value: "+buf.get() + ", Position: "+buf.position());
        System.out.println("Value: "+buf.get() + ", Position: "+buf.position());
        System.out.println("Value: "+buf.get() + ", Position: "+buf.position());
    }
}
