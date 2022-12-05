package Socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DictClient3 {

    public static void main(String[] args){

        String host = "dict.org";
        try{
            Socket soc = new Socket(host,2628);
            InputStream in = soc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            OutputStream out = soc.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String request = "DEFINE fd-eng-lat gold";
            bw.write(request);
            bw.flush();
            soc.shutdownOutput();

            for(String line = br.readLine();line!=null;line=br.readLine()){
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
