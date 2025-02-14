package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Klasa ForgetPasword - obsługuje opcje ,, Zapomniałem hasła"przy logowaniu, możliwość pozostawienia tego samego konta,
 * ze zmianą tylko hasła do jego obsługi po dodaniu imienia i nazwy uzytkownika
 */
public class ForgetPassword extends JFrame implements ActionListener {

    JTextField tfusername, tfname;
    JPasswordField tfpassword, tfpassword2;
    JButton forget, back;

    /**
     * Konstruktor klasy ForgetPassword - implementacja parametrów okienka, wgrywanie zdjęcia w tle,
     * inicjalizacja pól tekstowych oraz przycisku do wykonania operacji zmiany hasła
     */
    ForgetPassword(){
        setBounds(350,200,850,380);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ikony/ikona_tms.png"));
        Image i2=i1.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(580,70,200,200);
        add(image);
        JPanel p1=new JPanel();
        p1.setLayout(null);
        p1.setBounds(30,30,500,280);
        add(p1);

        JLabel lbusername =new JLabel("Nazwa uzytkownika");
        lbusername.setBounds(40,20,150,25);
        lbusername.setFont(new Font("Arial",Font.BOLD,14));
        p1.add(lbusername);

        tfusername = new JTextField();
        tfusername.setBounds(220,20,150,25);
        p1.add(tfusername);

        JLabel lbname=new JLabel("Imie");
        lbname.setBounds(40,50,150,25);
        lbname.setFont(new Font("Arial",Font.BOLD,14));
        p1.add(lbname);

        tfname = new JTextField();
        tfname.setBounds(220,50,150,25);
        p1.add(tfname);

        JLabel lbpassword1=new JLabel("Wprowadz nowe haslo");
        lbpassword1.setBounds(40,80,170,25);
        lbpassword1.setFont(new Font("Arial",Font.BOLD,14));
        p1.add(lbpassword1);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(220,80,150,25);
        p1.add(tfpassword);

        JLabel lbpassword2=new JLabel("Powtorz nowe haslo");
        lbpassword2.setBounds(40,110,150,25);
        lbpassword2.setFont(new Font("Arial",Font.BOLD,14));
        p1.add(lbpassword2);

        tfpassword2 = new JPasswordField();
        tfpassword2.setBounds(220,110,150,25);
        p1.add(tfpassword2);

        forget = new JButton("Zresetuj haslo");
        forget.setBackground(new Color(133,193,233));
        forget.setForeground(Color.white);
        forget.setFont(new Font("Tahoma",Font.BOLD,14));
        forget.setBounds(300,200,180,25);
        forget.addActionListener(this);
        p1.add(forget);




        back = new JButton("Powrot");
        back.setBounds(40,200,150,25);
        back.setBackground(new Color(133,193,233));
        back.setForeground(Color.white);
        back.setFont(new Font("Tahoma",Font.BOLD,14));
        back.addActionListener(this);
        p1.add(back);





        setVisible(true);

    }

    /**
     * W tym wypadku metoda actionPerformed służy do określenia,
     * że jeśli użytkownik naciśnie przycisk, to jego stare hasło
     * w bazie danych zostanie zmienione na takie,
     * jakie wpisał do pól tekstowych
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == forget) {
            String username = tfusername.getText();
            String newPassword = tfpassword.getText();

            if (username.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nazwa uzytkownika lub hasla sa puste!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connect c = new Connect();
                 PreparedStatement selectStmt = c.c.prepareStatement("SELECT * FROM users WHERE username = ?");
                 PreparedStatement updateStmt = c.c.prepareStatement("UPDATE users SET password = ? WHERE username = ?")) {

                // Sprawdzanie czy użytkownik istnieje
                selectStmt.setString(1, username);
                ResultSet rs = selectStmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "Nie znaleziono nazwy uzytkownika!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, username);

                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Haslo zaktualizowane z sukcesem!");
                } else {
                    JOptionPane.showMessageDialog(null, "Zaktualizowanie hasla nie powiodlo sie!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                throw new RuntimeException();
            }
        } else {
            setVisible(false);
            new Login();
        }
    }
   }

