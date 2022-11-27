package Socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        while(true){
            socket = new Socket("localhost", 13);
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Server에 요청 보내기");

            Scanner sc = new Scanner(System.in);
            oos.writeObject(sc.nextLine());

            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Server에서 받은 메시지 : " + message);
            if(message.equals("감사합니다")){
                break;
            }

            ois.close();
            oos.close();
        }
    }
}
