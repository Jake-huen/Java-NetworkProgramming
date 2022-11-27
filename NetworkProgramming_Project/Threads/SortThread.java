package Threads;
// Sleep Sort
// 배열의 크기가 아니라 숫자의 크기에 따라 runtime이 증가한다.
public class SortThread implements Runnable{
    private int num;

    public SortThread(int num){this.num = num;}
    @Override
    public void run(){
        try{
            Thread.sleep(num*50); // 쉬는시간이 짧으면 순서 뒤집힐수도 있어서
            System.out.println(num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        int[] numArray = {1,8,2,5,3,5,4,10};

        for(int num:numArray){
            SortThread st = new SortThread(num);
            Thread th = new Thread(st);
            th.start();
        }

    }
}
