package Threads;
import java.util.concurrent.*;

public class MultithreadMaxFinder {

    public static int max(int[] data) throws InterruptedException,ExecutionException{

        if(data.length==1){
            return data[0];
        }else if(data.length==0){
            throw new IllegalArgumentException();
        }

        FindMaxTask task1 = new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2 = new FindMaxTask(data,data.length/2,data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        return Math.max(future1.get(),future2.get());
    }
    public static int singlethread(int[] data){
        int iMax =0;
        for(int i:data) iMax = Math.max(i,iMax);
        return iMax;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] data = new int[] {10,30,20,4,2,3,100,342,32};
        long startTime = System.nanoTime();
        int result = max(data);
        long endTime = System.nanoTime();
        System.out.println(result+" 걸린시간: "+(endTime-startTime));

        long startTime2 = System.nanoTime();
        int result2 = singlethread(data);
        long endTime2 = System.nanoTime();
        System.out.println(result2+" 걸린시간: "+(endTime2-startTime2));
    }

}
