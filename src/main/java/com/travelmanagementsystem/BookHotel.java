package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.*;

public class BookHotel extends JFrame implements ActionListener {

    Choice cpackage, dpackage,cfood;
    JTextField tfpeople,tfnights;
    String username;
    JLabel labelusername,labelid,labelnumber,labelphone, labelprice,labelnights, labelfood;
    JButton checkprice, bookhotel, back;
    BookHotel(String username) {
        this.username=username;
        setBounds(350,200,1100,500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("ZAREZERWUJ HOTEL");
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

        JLabel lbldestination =new JLabel("Wybierz kierunek ");
        lbldestination.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbldestination.setBounds(40,110,150,25);
        add(lbldestination);

        dpackage=new Choice();
        dpackage.add("Hurghada, Egipt");
        dpackage.add("Portomaso, Malta");
        dpackage.add("Alanya, Turcja");
        dpackage.add("Las Palmas, Hiszpania");
        dpackage.add("Adeje, Teneryfa");
        dpackage.add("Gran Canaria, Hiszpania");
        dpackage.add("Cabanas, Portugalia");
        dpackage.add("Gdańsk, Polska");
        dpackage.add("Amsterdam, Holandia");
        dpackage.add("Wyspa Stella, Grecja");
        dpackage.setBounds(250,110,200,20);
        add(dpackage);



        JLabel lblchoose =new JLabel("Wybierz hotel: ");
        lblchoose.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblchoose.setBounds(40,150,120,20);
        add(lblchoose);

        cpackage=new Choice();
        cpackage.setBounds(250, 150, 200, 20);
        add(cpackage);

        dpackage.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateHotels();
                }
            }
        });
        try{
            Connect c=new Connect();
            ResultSet rs= c.s.executeQuery("select * from hotel");
            while(rs.next()){
                cpackage.add(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        JLabel lblnights =new JLabel("Liczba nocy ");
        lblnights.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblnights.setBounds(40,190,150,20);
        add(lblnights);

        tfnights=new JTextField("1");
        tfnights.setBounds(250,190,200,20);
        add(tfnights);

        JLabel lblpeople =new JLabel("Liczba osob: ");
        lblpeople.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblpeople.setBounds(40,230,150,20);
        add(lblpeople);

        tfpeople=new JTextField("1");
        tfpeople.setBounds(250,230,200,20);
        add(tfpeople);


        JLabel lblfood =new JLabel("Pełne wyżywienie:");
        lblfood.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblfood.setBounds(40,270,150,25);
        add(lblfood);

        cfood=new Choice();
        cfood.add("Tak");
        cfood.add("Nie");
        cfood.setBounds(250,270,200,20);
        add(cfood);

        JLabel lblphone =new JLabel("Numer kontaktowy: ");
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblphone.setBounds(40,310,150,20);
        add(lblphone);

        labelphone=new JLabel();
        labelphone.setFont(new Font("Tahoma",Font.PLAIN,16));
        labelphone.setBounds(250,310,200,20);
        add(labelphone);



        JLabel lbprice =new JLabel("Koszt: ");
        lbprice.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbprice.setBounds(40,350,150,20);
        add(lbprice);

        labelprice =new JLabel();
        labelprice.setBounds(250,350,200,25);
        add(labelprice);

        try{
            Connect con=new Connect();
            String query="select*from customer where username='"+username+"'";
            ResultSet rs=con.s.executeQuery(query);
            while(rs.next()){
                labelusername.setText(rs.getString("username"));
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

        bookhotel =new JButton("Rezerwuj teraz");
        bookhotel.setBackground(Color.BLACK);
        bookhotel.setForeground(Color.WHITE);
        bookhotel.setBounds(200,380,120,25);
        bookhotel.addActionListener(this);
        add(bookhotel);

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
    public void updateHotels(){
        cpackage.removeAll();

        String selectedDestination=dpackage.getSelectedItem();

        switch(selectedDestination) {
            case "Hurghada, Egipt":
                cpackage.add("The V Luxury Resort");
                break;

            case "Portomaso, Malta":
                cpackage.add("Hilton St. Julian's");
                break;

            case "Alanya, Turcja":
                cpackage.add("Alan Xafira Deluxe Resort");
                break;

            case "Las Palmas, Hiszpania":
                cpackage.add("Barcelo Lanzarote Active Resort");
                break;

            case "Adeje, Teneryfa":
                cpackage.add("Hard Rock Hotel");
                break;

            case "Gran Canaria, Hiszpania":
                cpackage.add("Fuerteventura Princess Hotel");
                break;

            case "Cabanas, Portugalia":
                cpackage.add("AP Cabanas Beach & Nature");
                        break;
            case "Gdańsk, Polska":
                cpackage.add("Hilton Hotel");
                break;

            case "Amsterdam, Holandia":
                cpackage.add("De L'Europe Hotel");
                break;

            case "Wyspa Stella, Grecja":
                cpackage.add("Stella Island Luxury Hotel");
                break;

        }

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==checkprice) {
            try{
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select*from hotel where name='" + cpackage.getSelectedItem() + "'");
            while(rs.next()) {
                 int cost=Integer.parseInt(rs.getString("costperperson"));
                 int food=Integer.parseInt(rs.getString("food"));
                 int people=Integer.parseInt(tfpeople.getText());
                 int nights=Integer.parseInt(tfnights.getText());
                 String foodselected=cfood.getSelectedItem();

                 if(people*nights>0){
                     int total=0;
                     total+=foodselected.equals("Tak") ? food:0;
                     total+=cost;
                     total=total*people*nights;
                     labelprice.setText(total+" Zł");
                 }else{
                     JOptionPane.showMessageDialog(null,"Prosze wprowadz poprawna liczbe");
                 }
            }




        }catch(Exception e){
            e.printStackTrace();}
        }
            else if(ae.getSource()== bookhotel){
            try{
                Connect con=new Connect();
                con.s.executeUpdate("insert into bookhotel values('"+labelusername.getText()+"','"+dpackage.getSelectedItem()+"','"+cpackage.getSelectedItem()+"','"+tfpeople.getText()+"','"+cfood.getSelectedItem()+"','"+labelphone.getText()+"','"+labelprice.getText()+"')");

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
        new BookHotel("");
    }
}