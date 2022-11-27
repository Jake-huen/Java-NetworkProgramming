package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui5EventHandler extends JFrame implements ActionListener {
    int index =0;
    String[] msgs = {"첫 번째 문장", "두 번째 문장","세 번째 문장"};
    JButton button1 = new JButton("<<");
    JButton button2 = new JButton(">>");
    JButton button3 = new JButton(msgs[0]);

    public Gui5EventHandler() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.setEnabled(false);
        add(button1,BorderLayout.WEST);
        add(button2,BorderLayout.EAST);
        add(button3,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(obj==button1){
            index--;
        }
        else if(obj==button2){
            index++;
        }
        if(index>2){
            index=0;
        }
        else if(index<0){
            index=2;
        }
        button3.setText(msgs[index]);
    }
    public static void main(String[] args){
        Gui5EventHandler app = new Gui5EventHandler();
    }
}
