package GUI;

import java.awt.*;

public class GUI1AwtBasic {

    public static void main(String[] args){
        Frame f = new Frame("Hello World");
        f.setLayout(new FlowLayout());
        Label label = new Label("Welcome to AWT");
        f.add(label);
        f.setSize(200,200);
        f.setVisible(true);
    }
}
