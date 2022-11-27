package Threads;

public class CallbackDigestUserInterface {

    public static void receiveDigest(byte[] digest,String name){
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        result.append(DigestThread.toHexString(digest));
        System.out.println(result);

//        System.out.print(name+": ");
//        System.out.print(DigestThread.toHexString(digest));
//        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException{
        for(String filename:args){
            CallbackDigest cb = new CallbackDigest(filename);
            Thread t = new Thread(cb);
            t.start();
        }
        //Thread.sleep(10);
        //System.out.println("main Thread\n");
    }
}
