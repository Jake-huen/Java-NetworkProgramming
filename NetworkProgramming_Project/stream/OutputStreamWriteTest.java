package stream;

public class OutputStreamWriteTest {

    public static void main(String[] args){

        for(int i=33;i<200;i++){
            System.out.write(i);
            System.out.print(i);
            System.out.flush();
        }
    }
}
