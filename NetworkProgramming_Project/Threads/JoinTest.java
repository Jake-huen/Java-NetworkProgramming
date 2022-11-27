package Threads;

public class JoinTest {
    public static void main(String[] args){
        ReturnDigest[] digests = new ReturnDigest[args.length];

        for(int i=0;i<args.length;i++){
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for(int i=0;i<args.length;i++){
            try {
                digests[i].join(); // Thread가 살아있는지 아닌지를 먼저 확인함.
                byte[] digest = digests[i].getDigest();
                StringBuilder result = new StringBuilder(args[i]);
                result.append(": ");
                result.append(DigestThread.toHexString(digest));
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
