package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DaytimeClient {

    public static void main(String[] args){
        // String hostname = "time.nist.gov";
        String hostname = "localhost";
        Socket socket = null;
        try{
            socket = new Socket(hostname,13);
            socket.setSoTimeout(10000);
            InputStream in = socket.getInputStream(); // socket으로 부터 inputStream 가져옴
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in,"ASCII"); // InputStream을 감싸준다.
            for(int c = reader.read(); c!=-1; c=reader.read()){
                time.append((char) c);
            }
            System.out.println(time);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
