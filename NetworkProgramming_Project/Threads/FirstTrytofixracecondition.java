package Threads;


public class FirstTrytofixracecondition {

    public static void main(String[] args){
        ReturnDigest[] digests = new ReturnDigest[args.length];

        for(int i=0;i<args.length;i++){
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for(int i=0;i<args.length;i++){
            StringBuffer result = new StringBuffer(args[i]);
            result.append(": ");
            byte[] digest = digests[i].getDigest();
            result.append(DigestThread.toHexString(digest));

            System.out.println(result);
        }
    }
}
