package URLConnection;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BinarySaver {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        try{
            URL root = new URL("http://www.konkuk.ac.kr/img/Main/img_logo_ku.png");
            saveBinaryFile(root);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Run-time: "+elapsedTime/1000.0);
    }

    private static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        System.out.println(contentLength);
        if(contentType.startsWith("text/") || contentLength==-1){
            throw new IOException("This is not a binary file.");
        }
        try(InputStream raw = uc.getInputStream()){
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset =0;
            while(offset<contentLength){
                int bytesRead = in.read(data,offset,data.length-offset);
                if(bytesRead==-1) break;
                offset+=bytesRead;
            }
            if(offset!=contentLength)
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            String filename = u.getFile();
            filename = filename.substring(filename.lastIndexOf('/')+1);
            try(FileOutputStream fout = new FileOutputStream(filename)){
                fout.write(data);
                fout.flush();
            }
        }
    }
}
