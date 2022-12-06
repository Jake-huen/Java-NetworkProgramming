package UDP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class UDPPoke {

    private int bufferSize; // datagram packet의 buffersize
    private int timeout;
    private InetAddress host; // remote host
    private int port; // remote port

    public UDPPoke(InetAddress host, int port, int bufferSize, int timeout){
        this.bufferSize = bufferSize;
        this.host = host;
        if(port < 1 || port > 65535){
            throw new IllegalArgumentException("Port out of range");
        }
        this.port = port;
        this.timeout = timeout;
    }

    public UDPPoke(InetAddress host, int port, int bufferSize){
        this(host,port,bufferSize,30000);
    }

    public UDPPoke(InetAddress host, int port){
        this(host, port, 8192, 30000);
    }

    // 소켓을 만들고 Datagram Packet도 만든다.
    // 특정 host의 port에 보낸다
    // 그 host로부터 byte를 받아들여서 byte를 리턴함.
    public byte[] poke() { // 데이터를 보내고 읽어들이는 부분
        try(DatagramSocket socket = new DatagramSocket(0)){ // DatagramSocket을 생성
            DatagramPacket outgoing = new DatagramPacket(new byte[1], 1, host, port); // datagram packet을 1byte짜리
            socket.connect(host,port); // socket을 host와 port에 연결(이 호스트와 port로만 받을거야)
            socket.setSoTimeout(timeout);
            socket.send(outgoing); // packet을 send

            DatagramPacket incoming = new DatagramPacket(new byte[bufferSize],bufferSize); // 공간 만들기
            socket.receive(incoming); // socket이 incoming packet을 받고
            int numBytes = incoming.getLength();
            byte[] response = new byte[numBytes];
            System.arraycopy(incoming.getData(),0,response,0,numBytes); // array로 copy
            return response;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        InetAddress host;
        int port = 0;
        try{
            host = InetAddress.getByName("8192");
            port = 0;
        } catch (RuntimeException | UnknownHostException ex){
            System.out.println("Usage: java UDPPoke host port");
            return;
        }

        try{
            UDPPoke poker = new UDPPoke(host, port);
            byte[] response = poker.poke();
            if(response == null){
                System.out.println("No response within allotted time");
                return;
            }
            String result = new String(response, "US-ASCII"); // String을 byte형태로 encoding해서 보낸걸 알기 때문에 String으로 한것.
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}