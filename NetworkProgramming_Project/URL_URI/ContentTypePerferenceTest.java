package URL_URI;
import java.awt.image.ImageProducer;
import java.net.*;
import java.io.*;

public class ContentTypePerferenceTest {

    public static void main(String[] args){
        try{
            URL u = new URL("https://www.oreilly.com");
            //URL u = new URL("https://konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            Class<?>[] types = new Class[4];
            types[0] = String.class;
            types[1] = Reader.class;
            types[2] = InputStream.class;
            types[3] = ImageProducer.class;
            Object o = u.getContent(types);

            // 어떤 instance냐에 따라 객체를 감싸는 것을 바꾸어줌
            if(o instanceof String){
                System.out.println(o);
                System.out.println("string입니다.");
            }else if(o instanceof Reader){
                int c;
                Reader r = (Reader) o;
                while((c=r.read())!=-1)System.out.print((char)c);
                r.close();
                System.out.println("Reader입니다.");
            }else if(o instanceof InputStream){
                int c;
                InputStream in = (InputStream) o;
                while((c=in.read())!=-1) System.out.write(c);
                in.close();
                System.out.println("InputStream 입니다.");
            }else if(o instanceof ImageProducer){
                System.out.println("ImageProducer returned");
            }else{
                System.out.println("Error: unexpected type "+o.getClass());
            }
        }catch (MalformedURLException me){
            System.err.println(me);
        }catch(IOException ie){
            System.err.println(ie);
        }
    }
}
