package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Przelewy24 extends JFrame implements ActionListener {
    JButton back;
    Przelewy24(){
        setBounds(500,200,800,600);
        JEditorPane pane=new JEditorPane();
        pane.setEditable(false);
        try{
            pane.setPage("https://www.przelewy24.pl/");

        }catch (Exception e){
            pane.setContentType("text/html");
            pane.setText("<html>Blad ladowania, Error 404</html>");
        }

        JScrollPane sp=new JScrollPane(pane);
        getContentPane().add(sp);

        back=new JButton("Powrót");
        back.setBounds(610,20,80,40);
        back.addActionListener(this);
        pane.add(back);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        setVisible(false);
        new Payment();

    }
}
