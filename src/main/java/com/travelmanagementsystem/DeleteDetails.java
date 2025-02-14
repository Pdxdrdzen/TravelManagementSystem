package com.travelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa DeleteDetails- służy do usunięcia danych zapisanych w bazie danych użytkownika
 */
public class DeleteDetails extends JFrame implements ActionListener {

    JButton deleteButton;
    String username;

    /**
     * Konstruktor klasy DeleteDetails, zawiera pola inicjalizacji ekranu, inicjalizacji okienek tekstowych oraz przycisków, inicjalizacja połączenia z bazą danych w celu uzyskania informacji o użytkowniku
     * @param username
     */
    DeleteDetails(String username) {
        this.username = username;
        setBounds(450, 180, 870, 625);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Nazwa użytkownika");
        lblusername.setBounds(30, 50, 150, 25);
        add(lblusername);

        JLabel labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        JLabel lblid = new JLabel("ID");
        lblid.setBounds(30, 110, 150, 25);
        add(lblid);

        JLabel labelid = new JLabel();
        labelid.setBounds(220, 110, 150, 25);
        add(labelid);

        JLabel lblnumber = new JLabel("Numer telefonu");
        lblnumber.setBounds(30, 170, 150, 25);
        add(lblnumber);

        JLabel labelnumber = new JLabel();
        labelnumber.setBounds(220, 170, 150, 25);
        add(labelnumber);

        JLabel lblname = new JLabel("Imię");
        lblname.setBounds(30, 230, 150, 25);
        add(lblname);

        JLabel labelname = new JLabel();
        labelname.setBounds(220, 230, 150, 25);
        add(labelname);

        JLabel lblgender = new JLabel("Płeć");
        lblgender.setBounds(30, 290, 150, 25);
        add(lblgender);

        JLabel labelgender = new JLabel();
        labelgender.setBounds(220, 290, 150, 25);
        add(labelgender);

        JLabel lblcountry = new JLabel("Kraj");
        lblcountry.setBounds(500, 50, 150, 25);
        add(lblcountry);

        JLabel labelcountry = new JLabel();
        labelcountry.setBounds(690, 50, 150, 25);
        add(labelcountry);

        JLabel lbladdress = new JLabel("Adres");
        lbladdress.setBounds(500, 110, 150, 25);
        add(lbladdress);

        JLabel labeladdress = new JLabel();
        labeladdress.setBounds(690, 110, 150, 25);
        add(labeladdress);

        JLabel lblphone = new JLabel("Numer telefonu");
        lblphone.setBounds(500, 170, 150, 25);
        add(lblphone);

        JLabel labelphone = new JLabel();
        labelphone.setBounds(690, 170, 150, 25);
        add(labelphone);

        JLabel lblemail = new JLabel("Adres email");
        lblemail.setBounds(500, 230, 150, 25);
        add(lblemail);

        JLabel labelemail = new JLabel();
        labelemail.setBounds(690, 230, 150, 25);
        add(labelemail);

        deleteButton = new JButton("Usuń dane");
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBounds(350, 350, 100, 25);
        deleteButton.addActionListener(this);
        add(deleteButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ikony/ViewCustomer.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(120, 400, 600, 200);
        add(image);

        try (Connect con = new Connect();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM customer WHERE username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelname.setText(rs.getString("name"));
                labelgender.setText(rs.getString("gender"));
                labelcountry.setText(rs.getString("country"));
                labeladdress.setText(rs.getString("address"));
                labelphone.setText(rs.getString("phone"));
                labelemail.setText(rs.getString("email"));
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }


        setVisible(true);

    }

    /**
     * Obsługa zdarzeń przy naciśnieciu przycisku.
     * W tym przypadku po nacisnieciu przycisku dane uzytkownika zostana usuniete z bazy danych
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae) {
        try (Connect con = new Connect()) {

            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM users WHERE username = ?")) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM customer WHERE username = ?")) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM bookpackage WHERE username = ?")) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM bookhotel WHERE username = ?")) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Dane usunięte z sukcesem");
            setVisible(false);
            System.exit(0);

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
