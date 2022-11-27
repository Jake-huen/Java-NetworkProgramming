package Socket;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.*;

public class PooledDaytimeServer {
    public final static int PORT = 13;
    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(50);

        try(ServerSocket server = new ServerSocket(PORT)){
            while(true){
                try{
                    Socket connection = server.accept();
                    Callable<Void> task = new DaytimeTask(connection);
                    pool.submit(task);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class DaytimeTask implements Callable<Void>{
        private Socket connection;
        DaytimeTask(Socket connection){
            this.connection = connection;
        }
        @Override
        public Void call(){
            try{
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                out.write(now.toString() + "\r\n");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

    }
}
