package URLConnection;

import URL_URI.QueryString;

import java.io.*;
import java.net.*;
import java.util.Locale;

public class FormPoster {

    private URL url;
    private QueryString query = new QueryString();

    public FormPoster(URL url){ //http만 되도록한다.
        if(!url.getProtocol().toLowerCase().startsWith("http")){
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    public void add(String name, String value){
        query.add(name,value);
    }

    public URL getURL(){
        return this.url;
    }

    public InputStream post() throws IOException{ // 쓴 다음에 가져올려고 InputStream 반환
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        try(OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(),"UTF-8")){
            out.write(query.toString()); // 쓰는 부분
            out.write("\r\n");
            out.flush();
        }

        return uc.getInputStream();
    }

    public static void main(String[] args){
        URL url;
        if(args.length>0){
            try{
                url = new URL(args[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }
        }else{
            try{
                url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }
        }
        FormPoster poster = new FormPoster(url);
        poster.add("name","Elliotte Rusty Harold");
        poster.add("emial","elharo@ibiblio.org");
        poster.add("want to","go home");

        try(InputStream in = poster.post()){
            Reader r = new InputStreamReader(in);
            int c;
            while((c=r.read())!=-1){
                System.out.print((char)c);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
