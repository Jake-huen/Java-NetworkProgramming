package stream;

import java.io.IOException;

public class availableTest {

    public static void main(String[] args){
        try {
            byte[] b = new byte[System.in.available()];
            System.in.read(b);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
