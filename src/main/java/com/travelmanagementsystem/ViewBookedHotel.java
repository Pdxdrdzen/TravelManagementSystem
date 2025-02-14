package com.travelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Klasa ViewBookedHotel- służy do wyświetlenia użytkownikowi hotelu, który
 * udało mu się zarezerwować, łącznie z jego całkowitą ceną, lokalizacją,
 * wyżywieniem.
 */
public class ViewBookedHotel extends JFrame implements ActionListener {

    JButton back;

    /**
     * Konstruktor klasy ViewBookedHotel
     * Inicjalizujemy tu parametry okienka, pola tekstowe
     * Tutaj również inicjalizujemy połączenie z bazą danych i pobranie
     * od niej danych w celu wyświetlenia konkretnego zarezerwowanego hotelu
     * dla konkretnego użytkownika
     * @param username
     */
    ViewBookedHotel(String username) {
        setBounds(400,200,1000,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text=new JLabel("SZCZEGÓŁY ZAREZERWOWANEGO HOTELU");
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        text.setBounds(30,0,500,30);
        add(text);

        JLabel lblusername=new JLabel("Nazwa użytkownika");
        lblusername.setBounds(30,50,150,25);
        add(lblusername);

        JLabel labelusername=new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);

        JLabel lbldestination =new JLabel("Kierunek: ");
        lbldestination.setBounds(30,90,150,25);
        add(lbldestination);

        JLabel labeldestination =new JLabel();
        labeldestination.setBounds(220,90,150,25);
        add(labeldestination);

        JLabel lblhotel =new JLabel("Nazwa hotelu: ");
        lblhotel.setBounds(30,130,150,25);
        add(lblhotel);

        JLabel labelhotel =new JLabel();
        labelhotel.setBounds(220,130,150,25);
        add(labelhotel);

        JLabel lblpeople =new JLabel("Ilość ludzi: ");
        lblpeople.setBounds(30,170,150,25);
        add(lblpeople);

        JLabel labelpeople =new JLabel();
        labelpeople.setBounds(220,170,150,25);
        add(labelpeople);

        JLabel lblfood =new JLabel("Wyżywienie: ");
        lblfood.setBounds(30,210,150,25);
        add(lblfood);

        JLabel labelfood =new JLabel();
        labelfood.setBounds(220,210,150,25);
        add(labelfood);

        JLabel lblphone =new JLabel("Numer kontaktowy");
        lblphone.setBounds(30,250,150,25);
        add(lblphone);

        JLabel labelphone =new JLabel();
        labelphone.setBounds(220,250,150,25);
        add(labelphone);

        JLabel lblprice =new JLabel("Pelny koszt");
        lblprice.setBounds(30,290,150,25);
        add(lblprice);

        JLabel labelprice =new JLabel();
        labelprice.setBounds(220,290,150,25);
        add(labelprice);


        back=new JButton("Powrót");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(130,360,100,25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/ViewBookedPackage.jpg"));
        Image i2= i1.getImage().getScaledInstance(1000,600,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1000,600);
        add(image);

        try (Connect con = new Connect()) {
            String query = "SELECT * FROM bookhotel WHERE username = ?";

            try (PreparedStatement stmt = con.getConnection().prepareStatement(query)) {
                stmt.setString(1, username);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    labelusername.setText(rs.getString("username"));
                    labeldestination.setText(rs.getString("destination"));
                    labelhotel.setText(rs.getString("name"));
                    labelpeople.setText(rs.getString("people"));
                    labelfood.setText(rs.getString("food"));
                    labelphone.setText(rs.getString("phone"));
                    labelprice.setText(rs.getString("price"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Błąd ", e);
        }







        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }

}
