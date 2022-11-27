package stream;

import java.io.IOException;

import static java.lang.System.in;

public class SystemInReadTest {
    public static void main(String[] args){
        int inChar = 0;
        int inChar2 = 0;
        System.out.print("Enter a Character : ");
        try{
            int i=0;
            inChar = in.read();
            System.out.println(inChar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] input = new byte[10];
        for(int i=0;i<input.length;i++){
            try {
                int b = in.read();
                if(b==-1) break;
                input[i] = (byte)b;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            System.out.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
