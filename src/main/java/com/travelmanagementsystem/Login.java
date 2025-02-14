package com.travelmanagementsystem;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa Login - klasa traktowana jako ,,Main" gdyż dzięki niej mamy dostęp do całej reszty programu.
 * Obsługuje otrzymanie od użytkownika loginu i hasła w celu udzielenia dostępu do "Dashboard"
 */
public class Login extends javax.swing.JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton signup, ForgotPassword, login;

    Login() {
        setSize(900, 400);
        setLocation(400, 200);
        setLayout(null);

        getContentPane().setBackground(Color.white);


        JPanel pl = new JPanel();
        pl.setBackground(Color.WHITE);
        pl.setBounds(0, 0, 400, 400);
        //setResizable(true);
        add(pl);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ikony/tlo_tms.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 400, 400);
        pl.add(image);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 30, 450, 300);
        add(p2);

        JLabel username = new JLabel("Nazwa uzytkownika: ");
        username.setBounds(60, 10, 200, 25);
        username.setFont(new Font("SAN_SERIE", Font.PLAIN, 20));
        p2.add(username);

        tfusername = new JTextField();
        tfusername.setBounds(60, 60, 200, 25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfusername);

        JLabel password = new JLabel("Haslo: ");
        password.setBounds(60, 110, 200, 25);
        password.setFont(new Font("SAN_SERIE", Font.PLAIN, 20));
        p2.add(password);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(60, 150, 200, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfpassword);

        login = new JButton("Zaloguj sie");
        login.setBounds(60, 200, 130, 30);
        login.setBackground(new Color(176, 216, 230));
        login.setForeground(Color.white);
        login.setBorder(new LineBorder(Color.BLACK));
        login.addActionListener(this);
        p2.add(login);

        signup = new JButton("Zarejestruj sie");
        signup.setBounds(200, 200, 130, 30);
        signup.setBackground(new Color(176, 216, 230));
        signup.setForeground(Color.white);
        signup.setBorder(new LineBorder(Color.BLACK));
        signup.addActionListener(this);
        p2.add(signup);

        ForgotPassword = new JButton("Zapomnialem hasla");
        ForgotPassword.setBounds(120, 250, 130, 30);
        ForgotPassword.setBackground(new Color(176, 216, 230));
        ForgotPassword.setForeground(Color.white);
        ForgotPassword.setBorder(new LineBorder(Color.BLACK));
        ForgotPassword.addActionListener(this);
        p2.add(ForgotPassword);

        JLabel text = new JLabel("Problem z logowaniem?");
        text.setBounds(300, 250, 150, 20);
        text.setForeground(Color.RED);
        p2.add(text);


        setVisible(true);
    }

    /**
     * W tym wypadku metoda actionPerformed służy do 3 rzeczy:
     * 1: Przy naciśnięciu "Zaloguj się" sprawdza, czy podane login i hasło istnieją w bazie danych
     * jeśli nie - zwraca błąd, jeśli tak - użytkownik przechodzi do Dashboard
     * 2: Przy naciśnięciu "Zarejestruj się" przenosi użytkownika do panelu Rejestracja, w celu założenia konta
     * 3: Przy naciśnięciu "Zapomniałem hasła" przenosi użytkownika do panelu ForgetPassword,
     * w celu ustawienia nowego hasła do swojego konta i aktualizacji go w bazie
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signup) {
            setVisible(false);
            new Rejestracja();
        } else if (ae.getSource() == ForgotPassword) {
            setVisible(false);
            new ForgetPassword();

        } else if (ae.getSource() == login) {
            try (Connect c = new Connect();
                 PreparedStatement stmt = c.prepareStatement(
                         "SELECT * FROM users WHERE username = ? AND password = ?")) {

                String username = tfusername.getText();
                String password = String.valueOf(tfpassword.getPassword());

                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        setVisible(false);
                        new Dashboard(username);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nieprawidlowa nazwa uzytkownika lub haslo");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }


    }

    public static void main(String[] args) {
        new Login();
    }
}
