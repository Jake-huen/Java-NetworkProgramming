package stream;

import java.io.*;

public class FileCopy {

    public static void main(String[] args){
//        if(args.length!=2){
//            System.out.println("사용법 : java stream.FileCopy 파일명1 파일명2");
//            System.exit(0);
//        }

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("skipTest.java");
            fw = new FileWriter("stream/CopyskipTest.java");
            char[] buffer = new char[512];
            int readcount =0;
            while((readcount=fr.read(buffer))!=-1){
                fw.write(buffer,0,readcount);
            }
            System.out.println("파일을 복사하였습니다.");

            fr.close();
            fw.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
