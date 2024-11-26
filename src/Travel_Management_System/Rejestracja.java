package Travel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class Rejestracja extends JFrame implements ActionListener {

    JButton create, back;
    JTextField tfname, tfusername;
    JPasswordField tfpassword, tfpassword2;
    Rejestracja() {
        setBounds(350,200,900,360);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(133,193,233));
        p1.setBounds(0,0,500,400);
        p1.setLayout(null);
        add(p1);

        JLabel username=new JLabel("Podaj nazwe uzytkownika:");
        username.setFont(new Font("Tahoma",Font.BOLD,14));
        username.setBounds(50,20,200,25);
        p1.add(username);

        tfusername=new JTextField();
        tfusername.setBounds(250,20,180,25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfusername);

        JLabel name=new JLabel("Podaj swoje imie:");
        name.setFont(new Font("Tahoma",Font.BOLD,14));
        name.setBounds(50,60,200,25);
        p1.add(name);

        tfname=new JTextField();
        tfname.setBounds(250,60,180,25);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        JLabel password=new JLabel("Podaj swoje haslo:");
        password.setFont(new Font("Tahoma",Font.BOLD,14));
        password.setBounds(50,100,200,25);
        p1.add(password);

        tfpassword=new JPasswordField();
        tfpassword.setBounds(250,100,180,25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfpassword);

        JLabel password2=new JLabel("Powtorz swoje haslo:");
        password2.setFont(new Font("Tahoma",Font.BOLD,14));
        password2.setBounds(50,140,200,25);
        p1.add(password2);

        tfpassword2=new JPasswordField();
        tfpassword2.setBounds(250,140,180,25);
        tfpassword2.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfpassword2);

        create = new JButton("Zarejestruj sie");
        create.setBackground(new Color(133,193,233));
        create.setForeground(Color.white);
        create.setFont(new Font("Tahoma",Font.BOLD,14));
        create.setBounds(300,200,180,25);
        create.addActionListener(this);
        p1.add(create);

        create.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent E){
                String username=tfusername.getText().trim();
                String name=tfname.getText().trim();
                String password1=new String(tfpassword.getPassword());
                String password2=new String(tfpassword2.getPassword());
                if(username.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Wprowadz nazwe uzytkownika!","Blad!",JOptionPane.WARNING_MESSAGE);
                }
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Wprowadz swoje imie!","Blad!",JOptionPane.WARNING_MESSAGE);
                }
                if(password1.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Wprowadz swoje haslo!","Blad!",JOptionPane.WARNING_MESSAGE);
                }
                if(password2.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Powtorz swoje haslo!","Blad!",JOptionPane.WARNING_MESSAGE);
                }
                if(!password1.equals(password2)){
                    JOptionPane.showMessageDialog(null,"Hasla nie zgadzaja sie!","Blad",JOptionPane.ERROR_MESSAGE);
                }if(password1.equals(password2)&&!username.isEmpty()&&!name.isEmpty()&&!password1.isEmpty()&&password2.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Rejestracja udana!","Sukces",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        back = new JButton("Wroc");
        back.setBackground(new Color(133,193,233));
        back.setForeground(Color.white);
        back.setFont(new Font("Tahoma",Font.BOLD,14));
        back.setBounds(50,200,180,25);
        back.addActionListener(this);
        p1.add(back);

       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/ikona_tms.png"));
       Image i2= i1.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
       ImageIcon i3=new ImageIcon(i2);
       JLabel image= new JLabel(i3);
       image.setBounds(580,50,250,250);
       add(image);



        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
if(e.getSource()==create){
    String username=tfusername.getText();
    String name=tfname.getText();
    String password1=new String(tfpassword.getPassword());
    String password2=new String(tfpassword2.getPassword());

    String query = "insert into users(name,username,password) values ('"+name+"','"+username+"','"+password1+"')";
    try {
        Connect c=new Connect();
        c.s.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Konto utworzone z sukcesem!");
    }catch(Exception e1){
        e1.printStackTrace();
    }

}else if(e.getSource()==back){
setVisible(false);
new Login();
}
    }
    public static void main(String[] args) {
        new Rejestracja();
    }
}
