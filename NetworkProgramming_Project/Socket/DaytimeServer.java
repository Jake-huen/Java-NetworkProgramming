package Socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {

    public final static int PORT = 13;

    public static void main(String[] args){
        try(ServerSocket server = new ServerSocket(PORT)){ // 듣고 있게 되는 것
            while(true){
                try(Socket connection = server.accept()){ // connection 메서드가 있으면 socket 리턴
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    // 시간을 써준다
                    Date now = new Date();
                    out.write(now.toString()+"\r\n");
                    // connection Socket에 써진다.
                    out.flush();
                    connection.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
