package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Klasa BookPackage - służy do zarezerwowania oferty przez użytkownika
 * i zapisaniu informacji o niej w bazie danych
 */
public class BookPackage extends JFrame implements ActionListener {
    private static final String FONT_NAME = "Tahoma";


    Choice cpackage;
    JTextField tfpeople;
    String username;

    private JLabel labelusername;
    private JLabel labelid;
    private JLabel labelnumber;
    private JLabel labelphone;
    private JLabel labelprice;
    private JButton checkprice;
    private JButton bookpackageButton;
    private JButton back;

    /**
     * Konstruktor klasy BookPackage inicjalizujacy ustawienia okna, ustawienia pol tekstowych oraz polaczenie z baza danych.
     *
     * @param username
     */
    BookPackage(String username) {
        this.username=username;
        setBounds(350,200,1100,500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("ZAREZERWUJ PAKIET");
        text.setBounds(100,10,300,30);
        text.setFont(new Font(FONT_NAME,Font.BOLD,20));
        add(text);

        JLabel lblusername=new JLabel("Nazwa użytkownika");
        lblusername.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lblusername.setBounds(40,70,150,20);
        add(lblusername);

        labelusername=new JLabel();
        labelusername.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        labelusername.setBounds(250,70,100,20);
        add(labelusername);

        JLabel lblchoose =new JLabel("Wybierz oferte: ");
        lblchoose.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lblchoose.setBounds(40,110,120,20);
        add(lblchoose);

        cpackage=new Choice();
        cpackage.add("Złota oferta");
        cpackage.add("Srebrna oferta");
        cpackage.add("Brązowa oferta");
        cpackage.setBounds(250,110,100,20);
        add(cpackage);

        JLabel lblpeople =new JLabel("Liczba osob");
        lblpeople.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lblpeople.setBounds(40,150,150,25);
        add(lblpeople);

        tfpeople=new JTextField("1");
        tfpeople.setBounds(250,150,200,20);
        add(tfpeople);

        JLabel lblid =new JLabel("Rodzaj dokumentu ");
        lblid.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lblid.setBounds(40,190,150,20);
        add(lblid);

        labelid =new JLabel();
        labelid.setBounds(250,190,200,25);
        add(labelid);

        JLabel lblpesel =new JLabel("Pesel: ");
        lblpesel.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lblpesel.setBounds(40,230,150,20);
        add(lblpesel);

        labelnumber =new JLabel();
        labelnumber.setBounds(250,230,200,25);
        add(labelnumber);


        JLabel lblphone =new JLabel("Numer kontaktowy");
        lblphone.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lblphone.setBounds(40,270,150,25);
        add(lblphone);

        labelphone =new JLabel();
        labelphone.setBounds(250,270,200,25);
        add(labelphone);

        JLabel lbprice =new JLabel("Koszt: ");
        lbprice.setFont(new Font(FONT_NAME,Font.PLAIN,16));
        lbprice.setBounds(40,310,150,20);
        add(lbprice);

        labelprice =new JLabel();
        labelprice.setBounds(250,310,200,25);
        add(labelprice);

        try (Connect conn = new Connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelphone.setText(rs.getString("phone"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd wczytywania danych klienta " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        checkprice=new JButton("Sprawdz cene");
        checkprice.setBackground(Color.BLACK);
        checkprice.setForeground(Color.WHITE);
        checkprice.setBounds(60,380,120,25);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackageButton =new JButton("Rezerwuj teraz");
        bookpackageButton.setBackground(Color.BLACK);
        bookpackageButton.setForeground(Color.WHITE);
        bookpackageButton.setBounds(200,380,120,25);
        bookpackageButton.addActionListener(this);
        add(bookpackageButton);

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

    /**
     * Funkcja służąca do przypisania działania do konkretnych czynności, w tym przypadku do naciśnięcia przycisku
     * @param ae the event to be processed
     */
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



        }else if(ae.getSource()== bookpackageButton){
            try (Connect conn = new Connect()) {
                String sql = "INSERT INTO bookpackage VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, labelusername.getText());
                pstmt.setString(2, cpackage.getSelectedItem());
                pstmt.setString(3, tfpeople.getText());
                pstmt.setString(4, labelid.getText());
                pstmt.setString(5, labelnumber.getText());
                pstmt.setString(6, labelphone.getText());
                pstmt.setString(7, labelprice.getText());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Oferta zarezerwowana z sukcesem!");
                setVisible(false);
            } catch (Exception e) {
                throw new RuntimeException();
            }

        }else {

            setVisible(false);
        }
    }
}