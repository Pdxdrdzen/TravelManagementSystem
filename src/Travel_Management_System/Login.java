package Travel_Management_System;
import javax.swing.*;
import java.awt.*;

public class Login extends javax.swing.JFrame {
    Login(){
        setSize(900,400);
        setLocation(400,200);
        setLayout(null);

        getContentPane().setBackground(Color.white);


        JPanel pl=new JPanel();
        pl.setBackground(Color.WHITE);
        pl.setBounds(0,0,400,400);
        //setResizable(true);
        add(pl);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/ikona_tms.png"));
        Image i2=i1.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,400,400);
        pl.add(image);

        JPanel p2=new JPanel();
        p2.setLayout(null);
        p2.setBounds(400,30,450,300);
        add(p2);

        JLabel username=new JLabel("Nazwa uzytkownika: ");
        username.setBounds(60,20,200,25);
        username.setFont(new Font("SAN_SERIE",Font.PLAIN,20));
        p2.add(username);

        JTextField tfusername=new JTextField();
        tfusername.setBounds(60,40,200,25);
        p2.add(tfusername);

        JLabel password=new JLabel("Haslo: ");
        password.setBounds(60,60,200,25);
        password.setFont(new Font("SAN_SERIE",Font.PLAIN,20));
        p2.add(password);

        JPasswordField tfpassword=new JPasswordField();
        tfpassword.setBounds(60,80,200,25);
        p2.add(tfpassword);



        setVisible(true);
    }
    public static void main(String[] args) {
        new Login();

    }
}
