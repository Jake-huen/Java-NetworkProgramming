package Socket;

import java.io.*;
import java.net.*;

public class EchoServer {
    private static ServerSocket server;
    public final static int PORT = 13;

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        try(ServerSocket server = new ServerSocket(PORT)){
            while(true){
                try(Socket connection = server.accept()){
                    System.out.println("Client랑 연결됨 !! ");

                    ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
                    // 받은 메시지 String으로 바꾸기
                    String message = (String) ois.readObject();
                    // Socket에 message 쓰기
                    ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
                    oos.writeObject(message);

                    ois.close();
                    oos.close();
                    connection.close();

                    if(message.equalsIgnoreCase("감사합니다"))
                        break;
                } catch (IOException ex) {
                    server.close();
                    System.out.println(ex);
                }
            }
        }catch (IOException ex) {
            System.out.println(ex);
        }finally {
            if(server!=null){
                server.close();
            }
        }
    }

}

