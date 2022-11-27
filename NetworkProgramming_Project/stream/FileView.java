package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileView {

    public static void main(String[] args){

        long start = System.nanoTime();
        FileInputStream fis = null;
        // FileOutputStream fos = null;
        try{
            // fos = new FileOutputStream("test3.txt");
            fis = new FileInputStream("test3.txt");
            int i=0;
            while((i=fis.read())!=-1){
                System.out.write(i);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }finally{
            try{
                fis.close();
            }catch(Exception e){

            }
        }
        long end = System.nanoTime();
        System.out.println("Run-time: "+(end-start)/1000.0);

        long start2 = System.nanoTime();
        try{
            FileInputStream fis2 = new FileInputStream("test3.txt");
            int readcount =0;
            byte[] buffer = new byte[512];

            while((readcount=fis2.read(buffer)) !=-1){
                System.out.write(buffer,0,readcount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end2 = System.nanoTime();
        System.out.println("Run-time: "+(end2-start2)/1000.0);
    }
}
