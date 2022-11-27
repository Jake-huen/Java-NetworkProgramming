package Socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DictClient3 {

    public static void main(String[] args){

        String host = "dict.org";
        try{
            Socket soc = new Socket(host,2628);
            OutputStream out = soc.getOutputStream();
            Writer writer = new OutputStreamWriter(out,"UTF-8");
            writer = new BufferedWriter(writer);
            InputStream in = soc.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in,"UTF-8"));
            String request = "DEFINE fd-eng-lat gold";
            writer.write(request);
            writer.flush();
            soc.shutdownOutput();

            for(String line = reader.readLine();line!=null;line=reader.readLine()){
                System.out.println(line);
            }
            soc.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
