package Threads;

public class ReturnDigestUserInterface {

//    public static void main(String[] args){
//        for(String filename : args){
//            ReturnDigest dr = new ReturnDigest(filename);
//            dr.start();
//
//            StringBuilder result = new StringBuilder(filename);
//            result.append(": ");
//            byte[] digest = dr.getDigest();
//            result.append(DigestThread.toHexString(digest)); // NUllpointerException 발생 이유?
//            System.out.println(result);
//        }
//    }
    // modified (expecting all threads are finished(1st loop) before printing(2nd loop)
    // first try
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
