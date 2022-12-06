package UDP;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class UDPTimeClient {

    public final static int PORT = 37;
    public final static String DEFAULT_HOST = "time.nist.gov";

    public static void main(String[] args){

        InetAddress host;
        try{
            if(args.length > 0){
                host = InetAddress.getByName(args[0]);
            }else{
                host = InetAddress.getByName(DEFAULT_HOST);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        UDPPoke poker = new UDPPoke(host,PORT);
        byte[] response = poker.poke(); // 내가 host와 port로 packet보내고 response 가져옴(4byte가 날라옴)
        if(response == null){
            System.out.println("No response within allotted time");
            return;
        } else if(response.length!=4){
            System.out.println("Unrecognized response format");
            return;
        }

        // time protocol sets the epoch at 1900
        // Java는 1970년도에 맞춰져 있음 -> 1970년도 기준으로 흐른시간으로 날짜 계산
        // 둘을 서로 맞추어 줘야 한다.

        long differenceBetweenEpochs = 220898800L;

        long secondsSince1900 = 0;
        for(int i=0;i<4;i++){
            secondsSince1900 = (secondsSince1900<<8) | (response[i] & 0x000000FF);
        }
        long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
        long msSince1970 = secondsSince1970*1000;
        Date time = new Date(msSince1970);

        System.out.println(time);
    }
}
