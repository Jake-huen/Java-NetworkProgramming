package GUI;

import javax.swing.*;
import java.awt.*;

public class Gui2SwingBasic extends JFrame {
    public Gui2SwingBasic(){
        super("Hello World"); // JFrame 생성
        getContentPane().setLayout(new FlowLayout());
        JLabel label = new JLabel("Welcome to Swing");
        getContentPane().add(label);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //exit 버튼 누르면 닫히게하기
        setSize(200,200);
        setVisible(true);
    }

    public static void main(String[] args){
        Gui2SwingBasic app = new Gui2SwingBasic();
    }
}
