package Threads;

import java.io.*;
import java.security.*;

public class InstanceCallbackDigest implements Runnable{

    private String filename;
    private InstanceCallbackDigestUserInterface callback;
    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback){
        this.filename = filename;
        this.callback = callback;
    }

    @Override
    public void run(){
        try{
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in,sha);
            while(din.read()!=-1) ;
            din.close();
            byte[] digest = sha.digest();
            callback.receiveDigest(digest);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
