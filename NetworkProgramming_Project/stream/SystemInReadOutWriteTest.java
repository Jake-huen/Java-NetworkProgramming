package stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SystemInReadOutWriteTest {
    public static void main(String[] args){
        InputStream in = System.in;
        OutputStream out = System.out;

        try{
            int input = in.read();
            System.out.println(input);
            out.write(input);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                try{
                    out.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
    }
}
