package Threads;
import java.io.*;
import java.security.*;

public class DigestThread extends Thread{

    private String filename;

    public DigestThread(String filename){
        this.filename = filename;
    }

    @Override
    public void run(){
        try{
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in,sha);
            while(din.read()!=-1);
            din.close();
            byte[] digest = sha.digest();

            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            result.append(toHexString(digest));
            System.out.println(result);

            // 이렇게 실행하면 -> Thread Synchronization(41페이지)

//            System.out.print(filename+":");
//            System.out.print(toHexString(digest));
//            System.out.println();
//            System.out.print(filename+": ");
//            System.out.print(toHexString(digest));
//            System.out.println();

            // synchronization
//            synchronized(System.out){
//                System.out.print(filename+": ");
//                System.out.print(toHexString(digest));
//                System.out.println();
//            }

            // synchronoization with sleeping
//            synchronized(System.out){
//                System.out.print(filename+": ");
//                if(filename.matches("./data.bin")){
//                    Thread.yield();
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        System.out.println(filename+ ": "+e);
//                    }
//                }
//                System.out.print(toHexString(digest));
//                System.out.println();
//            }
        }catch(IOException ex){
            System.err.println(ex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static String toHexString(byte[] bytes){
        StringBuilder hexString = new StringBuilder();

        for(int i=0;i<bytes.length;i++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args){
        for(String filename : args){
            Thread t = new DigestThread(filename);
            t.start();
        }

    }

}
// test1.rtf test1.rtf.gz test2.rtf test3.txt