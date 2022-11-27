package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamCopy {

    public static void main(String[] args){
        FileInputStream fis=null;
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream("skipTest.java");
            fos = new FileOutputStream("copynew.java");
            int readcount=0;
            byte[] buffer = new byte[512];

            while((readcount=fis.read(buffer))!=-1){
                fos.write(buffer,0,readcount);
            }
            System.out.println("복사가 완료되었습니다.");
        }catch(Exception ex){
            System.out.println(ex);
        }finally {
            try {
                if(fis!=null)
                    fis.close();
            }catch (IOException e){}
            try{
                if(fos!=null)
                    fos.close();
            }catch (IOException e){}
        }
    }
}
