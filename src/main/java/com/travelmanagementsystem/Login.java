package com.example.TravelManagementSystem;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends javax.swing.JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton signup, ForgotPassword, login;

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

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/tlo_tms.jpg"));
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
        username.setBounds(60,10,200,25);
        username.setFont(new Font("SAN_SERIE",Font.PLAIN,20));
        p2.add(username);

        tfusername=new JTextField();
        tfusername.setBounds(60,60,200,25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfusername);

        JLabel password=new JLabel("Haslo: ");
        password.setBounds(60,110,200,25);
        password.setFont(new Font("SAN_SERIE",Font.PLAIN,20));
        p2.add(password);

        tfpassword=new JPasswordField();
        tfpassword.setBounds(60,150,200,25);
        tfpassword  .setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfpassword);

        login=new JButton("Zaloguj sie");
        login.setBounds(60,200,130,30);
        login.setBackground(new Color(176,216,230));
        login.setForeground(Color.white);
        login.setBorder(new LineBorder(Color.BLACK ));
        login.addActionListener(this);
        p2.add(login);

        signup=new JButton("Zarejestruj sie");
        signup.setBounds(200,200,130,30);
        signup.setBackground(new Color(176,216,230));
        signup.setForeground(Color.white);
        signup.setBorder(new LineBorder(Color.BLACK ));
        signup.addActionListener(this);
        p2.add(signup);

        ForgotPassword=new JButton("Zapomnialem hasla");
        ForgotPassword.setBounds(120,250,130,30);
        ForgotPassword.setBackground(new Color(176,216,230));
        ForgotPassword.setForeground(Color.white);
        ForgotPassword.setBorder(new LineBorder(Color.BLACK ));
        ForgotPassword.addActionListener(this);
        p2.add(ForgotPassword);

        JLabel text=new JLabel("Problem z logowaniem?");
        text.setBounds(300,250,150,20);
        text.setForeground(Color.RED);
        p2.add(text);


        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==signup){
            setVisible(false);
            new Rejestracja();
        }else if(ae.getSource()==ForgotPassword){
            setVisible(false);
            new ForgetPassword();

        }else if(ae.getSource()==login){
            setVisible(false);

        }
    }
    //public static void main(String[] args) {
      //  new Login();

   // }
}
