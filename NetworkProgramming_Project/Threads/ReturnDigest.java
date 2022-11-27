package Threads;

import java.io.*;
import java.security.*;

public class ReturnDigest extends Thread{

    private String filename;
    private byte[] digest; // file에 내용에 기반한 Hash값을 저장

    public ReturnDigest(String filename){
        this.filename = filename;
    }

    @Override
    public void run(){
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in,sha);
            while(din.read()!=-1) ;
            din.close();
            digest = sha.digest(); //여기가 차이점 : class내부 digest field에 저장
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public byte[] getDigest(){
        return digest;
    }

}
// test1.rtf test1.rtf.gz test2.rtf test3.txt