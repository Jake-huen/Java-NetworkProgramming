package GUI;

import java.awt.*;

public class Gui3AwtLogin extends Frame {
    public Gui3AwtLogin(){
        super("AWT 기본예제");
        setLayout(new BorderLayout());
        // component 만들기
        Label t1 = new Label("ID");
        Label t2 = new Label("Passwd");
        TextField id = new TextField(10);
        TextField pwd = new TextField(10);
        Button btn = new Button("Login");

        Panel p1 = new Panel();
        p1.add(t1);
        p1.add(id);

        Panel p2 = new Panel();
        p2.add(t2);
        p2.add(pwd);
        p2.add(btn);

        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.SOUTH);

        setSize(250,100);
        setVisible(true);

    }

    public static void main(String[] args){
        Gui3AwtLogin app = new Gui3AwtLogin();
    }

}
