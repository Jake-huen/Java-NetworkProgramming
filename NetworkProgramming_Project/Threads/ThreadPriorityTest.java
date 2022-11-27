package Threads;

public class ThreadPriorityTest extends Thread{

    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println(getName()+": "+i);
        }
    }

    public static void main(String[] args){
        ThreadPriorityTest t1 = new ThreadPriorityTest();
        ThreadPriorityTest t2 = new ThreadPriorityTest();

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}
