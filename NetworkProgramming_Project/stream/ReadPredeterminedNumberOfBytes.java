package stream;

import java.io.IOException;

import static java.lang.System.in;

public class ReadPredeterminedNumberOfBytes {

    public static void main(String[] args){
        int bytesRead = 0;
        int bytesToRead = 1024;
        byte[] input = new byte[bytesToRead];
        while(bytesRead<bytesToRead){
            try {
                int result = in.read(input,bytesRead,bytesToRead-bytesRead);
                // 지금까지 읽은 바이트부터 시작. 못읽은 바이트까지
                if(bytesRead==-1) break;
                bytesRead+=result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            int bytesAvailable = in.available();
            byte[] input2 = new byte[bytesAvailable];
            int bytesRead2 = in.read(input,0,bytesAvailable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bytesRead);
    }
}
// 파일사이즈가 1024보다 작으면 무한루프에 빠지게 된다. -> while문을 빠져나오지 못하게 된다.
// while문을 break할 condition을 넣어주어야 한다.