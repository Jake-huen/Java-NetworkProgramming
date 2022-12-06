package UDP;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.DatagramChannel;

public class DefaultSocketOptionValues {

    public static void main(String[] args){
        try(DatagramChannel channel = DatagramChannel.open()){
            for(SocketOption<?> option:channel.supportedOptions()){
                System.out.println(option.name() + ": "+channel.getOption(option));
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
// 위에 2개는 method로 변경시킬 수 있다.
//SO_REUSEADDR: false
//SO_RCVBUF: 65536 -> send buffer가 receive buffer보다 훨씬 작다 -> send buffer는 잘 받았는지 상관없이 보낸다, 그래서 클 필요가 없다. 근데 receive buffer는 application에 올려보내기 전에 기다리는 건데 속도가 낮으면 buffer가 full이면 drop될 수 있기 때문에 receive buffer가 더 크다.
//IP_MULTICAST_TTL: 1
//IP_MULTICAST_LOOP: true
//IP_TOS: 0
//SO_SNDBUF: 65536
//SO_BROADCAST: false
//IP_MULTICAST_IF: null