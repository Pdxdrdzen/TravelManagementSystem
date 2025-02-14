package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Klasa UpdateCustomer - służy do aktualizacji danych użytkownika na jego życzenie poprzez Dashboard
 */
public class UpdateCustomer extends JFrame implements ActionListener {

    JLabel labelusername;
    JLabel labelname;
    JTextField tfusername;
    JTextField tfName;
    JTextField tfNumber;
    JTextField tfCountry;
    JTextField tfAddress;
    JTextField tfPhone;
    JTextField tfEmail;
    JTextField tfID;
    JTextField tfGender;
    JButton back;
    JButton update;

    /**
     * Konstruktor klasy UpdateCustomer
     * Inicjalizujemy tu parametry okna, pola tekstowe, przyciski oraz pobieramy dane z bazy na temat uzytkownika
     * ktory wywolal ta klase
     * @param username
     */
    UpdateCustomer(String username){
        setTitle("Aktualizuj dane klienta");
        setBounds(500,200,850,550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text=new JLabel("Aktualizuj dane Klienta");
        text.setBounds(50,0,300,25);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);

        JLabel lblUserName = new JLabel("Nazwa uzytkownika");
        lblUserName.setBounds(30,50,150,25);
        add(lblUserName);

        labelusername = new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);

        JLabel customerId = new JLabel("ID");
        customerId.setBounds(30,90,150,25);
        add(customerId);

        tfID = new JTextField();
        tfID.setBounds(220,90,150,25);
        add(tfID);

        JLabel lbNumber = new JLabel("Numer");
        lbNumber.setBounds(30,130,150,25);
        add(lbNumber);

        tfNumber = new JTextField();
        tfNumber.setBounds(220,130,150,25);
        add(tfNumber);

        JLabel lblName = new JLabel("Imie klienta");
        lblName.setBounds(30,170,150,25);
        add(lblName);

        labelname = new JLabel();
        labelname.setBounds(220,170,150,25);
        add(labelname);

        JLabel lblGender = new JLabel("Płeć");
        lblGender.setBounds(30,210,150,25);
        add(lblGender);


        tfGender = new JTextField();
        tfGender.setBounds(220,210,150,25);
        add(tfGender);


        JLabel lbCountry = new JLabel("Kraj");
        lbCountry.setBounds(30,250,150,25);
        add(lbCountry);

        tfCountry = new JTextField();
        tfCountry.setBounds(220,250,150,25);
        add(tfCountry);

        JLabel lbAddress = new JLabel("Adres");
        lbAddress.setBounds(30,290,150,25);
        add(lbAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(220,290,150,25);
        add(tfAddress);

        JLabel lbPhone = new JLabel("Numer telefonu");
        lbPhone.setBounds(30,330,150,25);
        add(lbPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(220,330,150,25);
        add(tfPhone);

        JLabel lbEmail = new JLabel("Adres Email");
        lbEmail.setBounds(30,370,150,25);
        add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(220,370,150,25);
        add(tfEmail);

        update = new JButton("Zaktualizuj dane");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70,420,150,25);
        update.addActionListener(this);
        add(update);

        back=new JButton("Wstecz");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250,420,150,25);
        back.addActionListener(this);
        add(back);

        String query = "SELECT * FROM customer WHERE username = ?";

        try (Connect conn = new Connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
                tfID.setText(rs.getString("id"));
                tfNumber.setText(rs.getString("number"));
                tfGender.setText(rs.getString("gender"));
                tfCountry.setText(rs.getString("country"));
                tfAddress.setText(rs.getString("address"));
                tfPhone.setText(rs.getString("phone"));
                tfEmail.setText(rs.getString("email"));
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }




        setVisible(true);



    }

    /**
     * Ta funkcja actionPerformed odpowiada za zamiane w bazie danych starych danych użytkownika,
     * z nowymi, które w tej klasie zostały podane
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==update){
            String username=labelusername.getText();
            String customerId= tfID.getText();
            String number=tfNumber.getText();
            String name=labelname.getText();
            String gender= tfGender.getText();
            String country=tfCountry.getText();
            String address=tfAddress.getText();
            String phone=tfPhone.getText();
            String email=tfEmail.getText();

            try (Connect conn = new Connect()) {
                String query = "UPDATE customer SET username = ?, id = ?, number = ?, name = ?, gender = ?, country = ?, address = ?, phone = ?, email = ? WHERE id = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.setString(2, customerId);
                    stmt.setString(3, number);
                    stmt.setString(4, name);
                    stmt.setString(5, gender);
                    stmt.setString(6, country);
                    stmt.setString(7, address);
                    stmt.setString(8, phone);
                    stmt.setString(9, email);
                    stmt.setString(10, customerId); // Assuming the WHERE condition is based on customerId

                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Dane klienta zaktualizowane poprawnie");
                    setVisible(false);
                }
            } catch (Exception e) {
                throw new RuntimeException("Błąd", e);
            }




        }else{
            setVisible(false);


        }
    }

}
