package stream;

import java.io.*;

public class InputStreamPractice {
    public static void main(String[] args){
        InputStream kbd = System.in;
        System.out.print("입력: ");

        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        try{
            int code = kbd.read();
            System.out.println(code);
            char ch = (char)code;
            System.out.println("char: "+ch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("종료");
    }

}
