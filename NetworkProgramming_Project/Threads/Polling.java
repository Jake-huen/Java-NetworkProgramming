package Threads;


public class Polling {

    public static void main(String[] args){
        ReturnDigest[] digests = new ReturnDigest[args.length];

        for(int i=0;i<args.length;i++){
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for (int i=0; i<args.length; i++) {
            // Now, print the result
            StringBuilder result = new StringBuilder(args[i]);
            result.append(": ");

            String hexStringResult;
            byte[] digest;
            while(true) {
                // System.out.println("Polling 중");
                int[] k1 = {1,2,3,4,5,6,7,8,9,10,11};
                if(digests[i].getDigest()!=null){
                    hexStringResult = DigestThread.toHexString(digests[i].getDigest());
                    break;
                }
            }
            result.append(hexStringResult);
            System.out.println(result);
        }

    }
}
