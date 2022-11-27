package Threads;

import java.io.*;
import java.security.*;

public class CallbackDigest implements Runnable{

    private String filename;

    public CallbackDigest(String filename){
        this.filename=filename;
    }

    @Override
    public void run(){
        try{
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in,sha);
            while (din.read()!=-1);
            din.close();
            byte[] digest = sha.digest();
            CallbackDigestUserInterface.receiveDigest(digest,filename);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
