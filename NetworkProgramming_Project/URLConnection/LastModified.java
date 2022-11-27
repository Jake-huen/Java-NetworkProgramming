package URLConnection;

import java.io.*;
import java.net.*;
import java.util.*;

public class LastModified {

    public static void main(String[] args){
        for(int i=0;i<args.length;i++){
            try{
                URL u = new URL(args[i]);
                HttpURLConnection http = (HttpURLConnection) u.openConnection();
                http.setRequestMethod("HEAD");
                System.out.println(u+" was last modified at "
                    + new Date(http.getLastModified()));
                http.setRequestMethod("GET");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
