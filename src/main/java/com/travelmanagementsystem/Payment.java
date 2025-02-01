package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Payment extends JFrame implements ActionListener {
    JButton pay;
    JButton back;
    Payment(){
        setBounds(500,200,800,600);
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/payment.jpg"));
        Image i2=i1.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(0,0,800,600);
        add(l1);
        setVisible(true);

        pay=new JButton("Opłać teraz");
        pay.setBounds(300,0,100,40);
        pay.addActionListener(this);
        l1.add(pay);

        back=new JButton("Powrót");
        back.setBounds(420,0,100,40);
        back.addActionListener(this);
        l1.add(back);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==pay){
            new Przelewy24();

        }else if(ae.getSource()==back){
            this.setVisible(false);
        }
    }
}
