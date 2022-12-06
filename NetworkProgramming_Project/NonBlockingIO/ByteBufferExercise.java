package NonBlockingIO;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

public class ByteBufferExercise {

    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println(bb.mark());
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());

        byte[] data = "Some data".getBytes("UTF-8");
        System.out.println(data);
        char[] text = "Some text".toCharArray();
        CharBuffer buffer2 = CharBuffer.wrap(text);
        System.out.println(buffer2);

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

    }
}
