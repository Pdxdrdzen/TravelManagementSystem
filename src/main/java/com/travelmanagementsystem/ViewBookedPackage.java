package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Klasa ViewBookedPackage - służy do wyświetlenia konkretnej zarezerwowanej oferty przez użytkownika
 */
public class ViewBookedPackage extends JFrame implements ActionListener {

    JButton back;

    /**
     * Konstruktor klasy ViewBookedPackage
     * Inicjalizujemy tu parametry okienka, pola tekstowe, przyciski
     * oraz łączymy się z bazą danych w celu pobrania z niej informacji
     * o zarezerwowanej ofercie przez konkretnego użytkownika
     * @param username
     */
    ViewBookedPackage(String username) {
        setBounds(450,200,900,455);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text=new JLabel("SZCZEGÓŁY ZAREZERWOWANEJ OFERTY");
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        text.setBounds(30,0,500,30);
        add(text);

        JLabel lblusername=new JLabel("Nazwa użytkownika");
        lblusername.setBounds(30,50,150,25);
        add(lblusername);

        JLabel labelusername=new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);

        JLabel lbloffer =new JLabel("Oferta");
        lbloffer.setBounds(30,90,150,25);
        add(lbloffer);

        JLabel labeloffer=new JLabel();
        labeloffer.setBounds(220,90,150,25);
        add(labeloffer);

        JLabel lblpeople =new JLabel("Liczba osob");
        lblpeople.setBounds(30,130,150,25);
        add(lblpeople);

        JLabel labelpeople =new JLabel();
        labelpeople.setBounds(220,130,150,25);
        add(labelpeople);

        JLabel lblid =new JLabel("Rodzaj dokumentu");
        lblid.setBounds(30,170,150,25);
        add(lblid);

        JLabel labelid =new JLabel();
        labelid.setBounds(220,170,150,25);
        add(labelid);

        JLabel lblpesel =new JLabel("Pesel");
        lblpesel.setBounds(30,210,150,25);
        add(lblpesel);

        JLabel labelpesel =new JLabel();
        labelpesel.setBounds(220,210,150,25);
        add(labelpesel);

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

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/BookingDetails.jpg"));
        Image i2= i1.getImage().getScaledInstance(500,400,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(450,20,500,400);
        add(image);

        try (Connect conn = new Connect()) {
            String query = "SELECT * FROM bookpackage WHERE username = ?";

            try (PreparedStatement stmt = conn.getConnection().prepareStatement(query)) {
                stmt.setString(1, username);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    labelusername.setText(rs.getString("username"));
                    labeloffer.setText(rs.getString("package"));
                    labelpeople.setText(rs.getString("people"));
                    labelid.setText(rs.getString("id"));
                    labelpesel.setText(rs.getString("pesel"));
                    labelphone.setText(rs.getString("phone"));
                    labelprice.setText(rs.getString("price"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Błąd", e);
        }







        setVisible(true);

    }

    /**
     * Tutaj actionPerformed wyłącza okno ViewBookedPackage jeśli naciśniemy przycisk powrotu
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }

}
