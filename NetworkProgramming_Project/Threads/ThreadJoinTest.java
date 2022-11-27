package Threads;

public class ThreadJoinTest {

    public static void main(String[] args){
        Thread t = new Thread(){
            public void run(){
                try{
                    Thread.sleep(2000);
                    System.out.println("Mythread ended");
                    Thread.sleep(3000);
                }catch(Exception e){

                }
            }
        };

        t.start();
        try{
            t.join();
            // Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("main() ended");
    }
}
