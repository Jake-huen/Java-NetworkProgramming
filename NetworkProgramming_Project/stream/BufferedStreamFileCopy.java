package stream;

import java.io.*;

public class BufferedStreamFileCopy {

    public static void main(String[] args){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int readcount =0;
            byte[] buffer = new byte[512];

            while((readcount=bis.read(buffer))!=-1){
                bos.write(buffer,0,readcount);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
