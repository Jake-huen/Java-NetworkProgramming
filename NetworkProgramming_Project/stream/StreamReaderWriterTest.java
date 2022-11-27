package stream;

import java.io.*;

public class StreamReaderWriterTest {
    public static void main(String[] args){
//        if(args.length!=1){
//            System.out.println("사용법: java StreamReaderTest 파일명");
//            System.exit(0);
//        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try{
            // working example(English)
            fis = new FileInputStream("src/stream/ChangeFileName.java");
            isr = new InputStreamReader(fis);
            osw = new OutputStreamWriter(System.out);

            // working example(Korean) : test3.txt created in Windows notepad
            fis = new FileInputStream("src/stream/test3.txt");
             //isr = new InputStreamReader(fis,"EUC-KR");
            isr = new InputStreamReader(fis,"UTF-8"); // 한글포맷
            osw = new OutputStreamWriter(System.out,"UTF-8"); //출력은 UT


// 영어만 있으면 상관없는데, 한글과 섞여있으면 encoding이 어떻게 되어있는지 정확히

            char[] buffer = new char[512];
            int readcount = 0;
            while((readcount = isr.read(buffer))!=-1){
                osw.write(buffer,0,readcount);
            }

//            BufferedReader br = new BufferedReader(isr);
//            String line = null;
//
//            if((line=br.readLine())!=null){
//                System.out.println(line);
//            }

            fis.close();
            isr.close();
            osw.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}

// working example(ENG+korean) : test5.txt encoding automatic
//            fis = new FileInputStream("test5.txt");
//            isr = new InputStreamReader(fis,"UTF-8");
//            osw = new OutputStreamWriter(System.out,"UTF-8");

// failing example(Korean+Engilsh)
//            fis = new FileInputStream("test4.rtf");
//            isr = new InputStreamReader(fis,"EUC-KR");
//            osw = new OutputStreamWriter(System.out);
//            osw = new OutputStreamWriter(System.out,"UTF-8");