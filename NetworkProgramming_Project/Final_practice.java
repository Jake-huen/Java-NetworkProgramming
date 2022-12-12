import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class Final_practice {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Client client = new Client();
        client.connect();
    }

}


class Client {
    private String ip = "203.252.148.148";
    private int port = 2021;

    private SocketChannel client = null;
    private final int MAX_BUF_SIZE = 65507;

    public Client() {}

    public void connect() {
        try {
            SocketAddress address = new InetSocketAddress(ip, port);
//            if(client!=null){
//                client.configureBlocking(false);
//            }
            client = SocketChannel.open();
            client.connect(address);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String theLine = userInput.readLine();

            ByteBuffer output = ByteBuffer.allocate(MAX_BUF_SIZE);
            for (int i = 0; i < theLine.length(); i++) {
                char ch = theLine.charAt(i);
                output.putChar(ch);
            }
            output.flip();
            client.write(output);

            ByteBuffer input = ByteBuffer.allocate(MAX_BUF_SIZE);
            int num = client.read(input);
            System.out.println(num);
            input.flip();

            // Read KOR String
            int size = input.remaining() - 80;
            byte[] tmp = new byte[size];
            input.get(tmp);
            String kor = new String(tmp, "EUC-KR");
            System.out.println("한글 문자열: " + kor);

            int[] intArray = new int[10];
            float[] floatArray = new float[10];
            for (int idx = 0; idx < 10; idx++) {
                intArray[idx] = input.getInt();
                floatArray[idx] = input.getFloat();
            }

            System.out.println("\n모든 데이터를 읽었는지 확인");
            System.out.println("(position, limit) = " + "(" + input.position() + ", " + input.limit() + ")\n");

            System.out.println("IntArray");
            for (int idx = 0; idx < 10; idx++) {
                System.out.print(intArray[idx] + " ");
            }
            System.out.println("\nFloatArray");
            for (int idx = 0; idx < 10; idx++) {
                System.out.print(floatArray[idx] + " ");
            }

            int maxInt = intArray[0];
            float maxFloat = floatArray[0];
            for (int idx = 1; idx < 10; idx++) {
                if (maxInt < intArray[idx]) {
                    maxInt = intArray[idx];
                }
                if (maxFloat < floatArray[idx]) {
                    maxFloat = floatArray[idx];
                }
            }

            System.out.println("\n\nMAX INT = " + maxInt);
            System.out.println("MAX FLOAT = " + maxFloat);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}