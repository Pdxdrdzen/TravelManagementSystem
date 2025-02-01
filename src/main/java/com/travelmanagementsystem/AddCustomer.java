package com.travelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    private final JComboBox<String> comboID;
    private final JLabel labelusername;
    private final JLabel labelname;

    private final JTextField tfNumber;
    private final JTextField tfCountry;
    private final JTextField tfAddress;
    private final JTextField tfPhone;
    private final JTextField tfEmail;

    private final JRadioButton rmale;
    private final JRadioButton rfemale;
    private final JRadioButton rother;

    private final JButton add;
    private final JButton back;

    String username;

    public AddCustomer(String username) {
        this.username=username;
        setTitle("Dodaj klienta");
        setBounds(450, 200, 850, 550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblUserName = new JLabel("Nazwa uzytkownika");
        lblUserName.setBounds(30, 50, 150, 25);
        add(lblUserName);

        labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        JLabel customerId = new JLabel("ID");
        customerId.setBounds(30, 90, 150, 25);
        add(customerId);

        comboID = new JComboBox<>(new String[]{"Paszport", "Dowód osobisty", "Karta pobytu", "Wiza"});
        comboID.setBounds(220, 90, 150, 25);
        comboID.setBackground(Color.WHITE);
        add(comboID);

        JLabel lbNumber = new JLabel("Numer");
        lbNumber.setBounds(30, 130, 150, 25);
        add(lbNumber);

        tfNumber = new JTextField();
        tfNumber.setBounds(220, 130, 150, 25);
        add(tfNumber);

        JLabel lblName = new JLabel("Imie klienta");
        lblName.setBounds(30, 170, 150, 25);
        add(lblName);

        labelname = new JLabel();
        labelname.setBounds(220, 170, 150, 25);
        add(labelname);

        JLabel lblGender = new JLabel("Płeć");
        lblGender.setBounds(30, 210, 150, 25);
        add(lblGender);

        rmale = new JRadioButton("Mężczyzna");
        rmale.setBounds(220, 210, 90, 25);
        rmale.setBackground(Color.WHITE);
        add(rmale);

        rfemale = new JRadioButton("Kobieta");
        rfemale.setBounds(320, 210, 90, 25);
        rfemale.setBackground(Color.WHITE);
        add(rfemale);

        rother = new JRadioButton("Inna");
        rother.setBounds(410, 210, 90, 25);
        rother.setBackground(Color.WHITE);
        add(rother);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rmale);
        bg.add(rfemale);
        bg.add(rother);

        JLabel lbCountry = new JLabel("Kraj");
        lbCountry.setBounds(30, 250, 150, 25);
        add(lbCountry);

        tfCountry = new JTextField();
        tfCountry.setBounds(220, 250, 150, 25);
        add(tfCountry);

        JLabel lbAddress = new JLabel("Adres");
        lbAddress.setBounds(30, 290, 150, 25);
        add(lbAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(220, 290, 150, 25);
        add(tfAddress);

        JLabel lbPhone = new JLabel("Numer telefonu");
        lbPhone.setBounds(30, 330, 150, 25);
        add(lbPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(220, 330, 150, 25);
        add(tfPhone);

        JLabel lbEmail = new JLabel("Adres Email");
        lbEmail.setBounds(30, 370, 150, 25);
        add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(220, 370, 150, 25);
        add(tfEmail);

        add = new JButton("Dodaj uzytkownika");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(70, 420, 150, 25);
        add.addActionListener(this);
        add(add);

        back = new JButton("Wstecz");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250, 420, 150, 25);
        back.addActionListener(this);
        add(back);

        loadUserData();
        setVisible(true);
    }

    private void loadUserData() {
        ResultSet rs = null;
        try(Connect con=new Connect()) {
            rs = con.s.executeQuery("SELECT username, name FROM users WHERE username = '"+username+"'");
            if (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Blad wczytywania danych klienta " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            saveCustomerData();
        } else {
            setVisible(false);
        }
    }

    private void saveCustomerData() {
        String gender;
        if (rmale.isSelected()) {
            gender = "Male";
        } else if (rfemale.isSelected()) {
            gender = "Female";
        } else {
            gender = "Other";
        }


        PreparedStatement pstmt = null;
        try(Connect con = new Connect()) {
            String query = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conn = con.s.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, labelusername.getText());
            pstmt.setString(2, (String) comboID.getSelectedItem());
            pstmt.setString(3, tfNumber.getText());
            pstmt.setString(4, labelname.getText());
            pstmt.setString(5, gender);
            pstmt.setString(6, tfCountry.getText());
            pstmt.setString(7, tfAddress.getText());
            pstmt.setString(8, tfPhone.getText());
            pstmt.setString(9, tfEmail.getText());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Dane klienta dodane poprawnie");
            setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Blad zapisywania danych klienta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }

}