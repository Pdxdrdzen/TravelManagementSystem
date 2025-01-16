package com.example.TravelManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookPackage extends JFrame implements ActionListener {

    Choice cpackage;
    JTextField tfpeople;
    String username;
    JLabel labelusername,labelid,labelnumber,labelphone, labelprice;
    JButton checkprice, bookpackage, back;
    BookPackage(String username) {
        this.username=username;
        setBounds(350,200,1100,500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("ZAREZERWUJ PAKIET");
        text.setBounds(100,10,300,30);
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        add(text);

        JLabel lblusername=new JLabel("Nazwa użytkownika");
        lblusername.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblusername.setBounds(40,70,150,20);
        add(lblusername);

        labelusername=new JLabel();
        labelusername.setFont(new Font("Tahoma",Font.PLAIN,16));
        labelusername.setBounds(250,70,100,20);
        add(labelusername);

        JLabel lblchoose =new JLabel("Wybierz oferte: ");
        lblchoose.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblchoose.setBounds(40,110,120,20);
        add(lblchoose);

        cpackage=new Choice();
        cpackage.add("Złota oferta");
        cpackage.add("Srebrna oferta");
        cpackage.add("Brązowa oferta");
        cpackage.setBounds(250,110,100,20);
        add(cpackage);

        JLabel lblpeople =new JLabel("Liczba osob");
        lblpeople.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblpeople.setBounds(40,150,150,25);
        add(lblpeople);

        tfpeople=new JTextField("1");
        tfpeople.setBounds(250,150,200,20);
        add(tfpeople);

        JLabel lblid =new JLabel("Rodzaj dokumentu ");
        lblid.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblid.setBounds(40,190,150,20);
        add(lblid);

        labelid =new JLabel();
        labelid.setBounds(250,190,200,25);
        add(labelid);

        JLabel lblpesel =new JLabel("Pesel: ");
        lblpesel.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblpesel.setBounds(40,230,150,20);
        add(lblpesel);

        labelnumber =new JLabel();
        labelnumber.setBounds(250,230,200,25);
        add(labelnumber);


        JLabel lblphone =new JLabel("Numer kontaktowy");
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblphone.setBounds(40,270,150,25);
        add(lblphone);

        labelphone =new JLabel();
        labelphone.setBounds(250,270,200,25);
        add(labelphone);

        JLabel lbprice =new JLabel("Koszt: ");
        lbprice.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbprice.setBounds(40,310,150,20);
        add(lbprice);

        labelprice =new JLabel();
        labelprice.setBounds(250,310,200,25);
        add(labelprice);

        try{
            Connect con=new Connect();
            String query="select*from customer where username='"+username+"'";
            ResultSet rs=con.s.executeQuery(query);
            while(rs.next()){
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelphone.setText(rs.getString("phone"));


            }

        }catch(Exception e){
            e.printStackTrace();
        }
        checkprice=new JButton("Sprawdz cene");
        checkprice.setBackground(Color.BLACK);
        checkprice.setForeground(Color.WHITE);
        checkprice.setBounds(60,380,120,25);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackage=new JButton("Rezerwuj teraz");
        bookpackage.setBackground(Color.BLACK);
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setBounds(200,380,120,25);
        bookpackage.addActionListener(this);
        add(bookpackage);

        back=new JButton("Powrot");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(340,380,120,25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/bookpackage.jpg"));
        Image i2=i1.getImage().getScaledInstance(500,300,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l14=new JLabel(i3);
        l14.setBounds(550,50,500,300);
        add(l14);






        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==checkprice){
            String pack=cpackage.getSelectedItem();
            int cost=0;
            if(pack.equals("Złota oferta")){
                cost+=2200;

            }else if(pack.equals("Srebrna oferta")){
                cost+=2400;

            }else if(pack.equals("Brązowa oferta")){
                cost+=2000;
            }
            int people=Integer.parseInt(tfpeople.getText());
            cost*=people;
            labelprice.setText(String.valueOf(cost)+" zł");



        }else if(ae.getSource()==bookpackage){
            try{
                Connect con=new Connect();
                con.s.executeUpdate("insert into bookpackage values('"+labelusername.getText()+"','"+cpackage.getSelectedItem()+"','"+tfpeople.getText()+"','"+labelid.getText()+"','"+labelnumber.getText()+"','"+labelphone.getText()+"','"+labelprice.getText()+"')");

                JOptionPane.showMessageDialog(null,"Oferta zarezerwowana z sukcesem!");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else {

            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new BookPackage("");
    }
}