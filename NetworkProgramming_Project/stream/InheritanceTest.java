package stream;

public class InheritanceTest {
    public static void print(Parent2 p){
        System.out.println(p.i);
        System.out.println(p.get());
    }
    public static void main(String[] args){
        Parent2 p = new Parent2();
        System.out.println("--------------1---------------");
        System.out.println(p.i);
        System.out.println(p.get());

        Child2 c = new Child2();
        System.out.println("--------------2--------------");
        System.out.println(c.i);
        System.out.println(c.get());

        Parent2 p2 = new Child2();
        System.out.println("---------- ----3---------------");
        System.out.println(p2.i);
        System.out.println(p2.get());

        System.out.println("--------------4--------------");
        print(c);
        print(p2);

        stream.FirstChild fc = new stream.FirstChild();
        System.out.println(fc.read());

        stream.SecondChild sc = new stream.SecondChild();
        System.out.println(fc.read());

        stream.ThirdChild tc1 = new stream.ThirdChild(fc);
        System.out.println(tc1.read());

        stream.ThirdChild tc2 = new stream.ThirdChild(sc);
        System.out.println(tc2.read());
    }
}

class Parent{
    public String read(){
        return "stream.Parent 입니다";
    }
}

class FirstChild extends Parent{
    public String read(){
        return super.read()+" firstChild";
    }
}

class SecondChild extends Parent{
    public String read(){
        return super.read()+" secondChild";
    }
}

class ThirdChild extends Parent{
    Parent p;

    public ThirdChild(Parent p){
        this.p = p;
    }

    public String read(){
        return p.read()+": thirdChild";
    }
}

class Parent2{
    int i=7;
    public int get(){
        return i;
    }
}

class Child2 extends Parent2{
    int i=5;
    public int get(){
        return i;
    }
}