//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetSocketAddress;
//import java.net.SocketAddress;
//import java.net.UnknownHostException;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//
//
//public class Final_practice {
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Client client = new Client();
//        client.connect();
//    }
//
//}
//
//
//class Client {
//
//    private String IP = "203.252.148.148";
//    private int PORT = 2021;
//    private final int MAX_BUF_SIZE = 65507;
//
//    public Client(){}
//    public void connect(){
//        SocketChannel client = null;
//        try {
//            client = SocketChannel.open();
//            client.connect(new InetSocketAddress(IP,PORT));
//
//            // 내가 입력한 거 ByteBuffer에 담아서 읽기
//            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//            String theLine = userInput.readLine();
//            ByteBuffer output = ByteBuffer.allocate(MAX_BUF_SIZE);
//            for(int i=0;i<theLine.length();i++){
//                char ch = theLine.charAt(i);
//                output.putChar(ch);
//            }
//            output.flip(); // position이 0으로가고 원래 position은 limit으로 간다.
//            client.write(output);
//
//            // 서버에서 데이터 받아오기 -> 받아온 데이터는 input에 들어간다
//            ByteBuffer input = ByteBuffer.allocate(MAX_BUF_SIZE);
//            // System.out.println(client.read(input));
//            int num = client.read(input);
//            System.out.println(num);
//            input.flip();
//
//            // 한글 문자열 읽기
//            int size = input.remaining() - 80; // int 10개, float 10개라서 한글 문자열 size
//            byte[] tmp = new byte[size]; // tmp에 한글 문자열이 들어간다.
//            input.get(tmp);
//            String kor = new String(tmp,"EUC-KR");
//            System.out.println("한글 문자열 : "+ kor);
//
//            // int와 float 읽기
//            int[] intArray = new int[10];
//            float[] floatArray = new float[10];
//            for(int idx=0;idx<10;idx++){
//                intArray[idx] = input.getInt();
//                floatArray[idx] = input.getFloat();
//            }
//            //모든 데이터 읽었는지 확인하기
//            System.out.println("\n 모든 데이터를 읽었는지 확인하기");
//            System.out.println("(position , limit) : " + "("+input.position() + ", "+input.limit()+")");
//
//            // int형 중 최대, float형 중 최대 출력하기
//            // System.out.println("INTARRAY");
//            int MAXINT = intArray[0];
//            for(int idx =0; idx<10;idx++){
//                System.out.print(intArray[idx]+" ");
//                if(intArray[idx]>MAXINT){
//                    MAXINT = intArray[idx];
//                }
//            }
//
//            // System.out.println("FLOATARRAY");
//            float MAXFLOAT = floatArray[0];
//            for(int idx =0; idx<10;idx++){
//                System.out.print(floatArray[idx]+" ");
//                if(floatArray[idx]>MAXFLOAT){
//                    MAXFLOAT = floatArray[idx];
//                }
//            }
//            System.out.println("int형 숫자 중 최대 : "+MAXINT);
//            System.out.println("float형 숫자 중 최대 : "+MAXFLOAT);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if(client!=null){
//                try {
//                    client.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
//}