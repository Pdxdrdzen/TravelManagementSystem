package com.travelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookHotel extends JFrame implements ActionListener {

    private static final String FONT_NAME = "Tahoma";

    Choice destinationPackage;
    Choice hotelPackage;
    Choice foodChoice;

    JTextField peopleTextField;
    JTextField nightsTextField;

    private final String username;
    JLabel usernameLabel;
    JLabel phoneLabel;
    JLabel priceLabel;

    // Initialize buttons as final fields
    private final JButton checkPrice;
    private final JButton bookHotelButton;
    private final JButton back;
    protected Connect createConnect() {
        return new Connect();
    }

    public BookHotel(String username) {
        this.username = username;

        // Initialize all components in constructor
        destinationPackage = new Choice();
        hotelPackage = new Choice();
        foodChoice = new Choice();
        peopleTextField = new JTextField("1");
        nightsTextField = new JTextField("1");
        usernameLabel = new JLabel();
        phoneLabel = new JLabel();
        priceLabel = new JLabel();

        // Initialize buttons
        checkPrice = new JButton("Sprawdz cene");
        bookHotelButton = new JButton("Rezerwuj teraz");
        back = new JButton("Powrot");

        setBounds(350, 200, 1100, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("ZAREZERWUJ HOTEL");
        text.setBounds(100, 10, 300, 30);
        text.setFont(new Font(FONT_NAME, Font.BOLD, 20));
        add(text);

        JLabel lblusername = new JLabel("Nazwa użytkownika");
        lblusername.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lblusername.setBounds(40, 70, 150, 20);
        add(lblusername);

        usernameLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        usernameLabel.setBounds(250, 70, 100, 20);
        add(usernameLabel);

        JLabel lbldestination = new JLabel("Wybierz kierunek ");
        lbldestination.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lbldestination.setBounds(40, 110, 150, 25);
        add(lbldestination);

        setupDestinations(destinationPackage);
        destinationPackage.setBounds(250, 110, 200, 20);
        add(destinationPackage);

        JLabel lblchoose = new JLabel("Wybierz hotel: ");
        lblchoose.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lblchoose.setBounds(40, 150, 120, 20);
        add(lblchoose);

        hotelPackage.setBounds(250, 150, 200, 20);
        add(hotelPackage);

        destinationPackage.addItemListener(e -> updateHotels());

        JLabel lblnights = new JLabel("Liczba nocy ");
        lblnights.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lblnights.setBounds(40, 190, 150, 20);
        add(lblnights);

        nightsTextField.setBounds(250, 190, 200, 20);
        add(nightsTextField);

        JLabel lblpeople = new JLabel("Liczba osob: ");
        lblpeople.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lblpeople.setBounds(40, 230, 150, 20);
        add(lblpeople);

        peopleTextField.setBounds(250, 230, 200, 20);
        add(peopleTextField);

        JLabel lblfood = new JLabel("Pełne wyżywienie:");
        lblfood.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lblfood.setBounds(40, 270, 150, 25);
        add(lblfood);

        foodChoice.add("Tak");
        foodChoice.add("Nie");
        foodChoice.setBounds(250, 270, 200, 20);
        add(foodChoice);

        JLabel lblphone = new JLabel("Numer kontaktowy: ");
        lblphone.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lblphone.setBounds(40, 310, 150, 20);
        add(lblphone);

        phoneLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        phoneLabel.setBounds(250, 310, 200, 20);
        add(phoneLabel);

        JLabel lbprice = new JLabel("Koszt: ");
        lbprice.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        lbprice.setBounds(40, 350, 150, 20);
        add(lbprice);

        priceLabel.setBounds(250, 350, 200, 25);
        add(priceLabel);

        loadCustomerData();
        setupButtons();
        setupImage();
        setVisible(true);
    }

    private void setupDestinations(Choice destinationPackage) {
        destinationPackage.add("Hurghada, Egipt");
        destinationPackage.add("Portomaso, Malta");
        destinationPackage.add("Alanya, Turcja");
        destinationPackage.add("Las Palmas, Hiszpania");
        destinationPackage.add("Adeje, Teneryfa");
        destinationPackage.add("Gran Canaria, Hiszpania");
        destinationPackage.add("Cabanas, Portugalia");
        destinationPackage.add("Gdańsk, Polska");
        destinationPackage.add("Amsterdam, Holandia");
        destinationPackage.add("Wyspa Stella, Grecja");
    }

    private void setupButtons() {
        checkPrice.setBackground(Color.BLACK);
        checkPrice.setForeground(Color.WHITE);
        checkPrice.setBounds(60, 380, 120, 25);
        checkPrice.addActionListener(this);
        add(checkPrice);

        bookHotelButton.setBackground(Color.BLACK);
        bookHotelButton.setForeground(Color.WHITE);
        bookHotelButton.setBounds(200, 380, 120, 25);
        bookHotelButton.addActionListener(this);
        add(bookHotelButton);

        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(340, 380, 120, 25);
        back.addActionListener(this);
        add(back);
    }

    private void setupImage() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ikony/bookpackage.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(550, 50, 500, 300);
        add(l14);
    }

    void loadCustomerData() {
        try (Connect con = new Connect();
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT username, phone FROM customer WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usernameLabel.setText(rs.getString("username"));
                    phoneLabel.setText(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customer data: " + e.getMessage());
        }
    }
    public void updateHotels() {
        hotelPackage.removeAll();
        String selectedDestination = destinationPackage.getSelectedItem();

        switch (selectedDestination) {
            case "Hurghada, Egipt":
                hotelPackage.add("The V Luxury Resort");
                break;
            case "Portomaso, Malta":
                hotelPackage.add("Hilton St. Julian's");
                break;
            case "Alanya, Turcja":
                hotelPackage.add("Alan Xafira Deluxe Resort");
                break;
            case "Las Palmas, Hiszpania":
                hotelPackage.add("Barcelo Lanzarote Active Resort");
                break;
            case "Adeje, Teneryfa":
                hotelPackage.add("Hard Rock Hotel");
                break;
            case "Gran Canaria, Hiszpania":
                hotelPackage.add("Fuerteventura Princess Hotel");
                break;
            case "Cabanas, Portugalia":
                hotelPackage.add("AP Cabanas Beach & Nature");
                break;
            case "Gdańsk, Polska":
                hotelPackage.add("Hilton Hotel");
                break;
            case "Amsterdam, Holandia":
                hotelPackage.add("De L'Europe Hotel");
                break;
            case "Wyspa Stella, Grecja":
                hotelPackage.add("Stella Island Luxury Hotel");
                break;
            default:
                hotelPackage.add("Brak dostepnych hoteli");
                break;
        }
    }

    void calculatePrice() {
        try (Connect c = new Connect();
             PreparedStatement stmt = c.prepareStatement(
                     "SELECT costperperson, food FROM hotel WHERE name = ?")) {
            stmt.setString(1, hotelPackage.getSelectedItem());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int cost = Integer.parseInt(rs.getString("costperperson"));
                    int food = Integer.parseInt(rs.getString("food"));
                    int people = Integer.parseInt(peopleTextField.getText());
                    int nights = Integer.parseInt(nightsTextField.getText());
                    String foodselected = foodChoice.getSelectedItem();

                    if (people * nights > 0) {
                        int total = 0;
                        total += "Tak".equals(foodselected) ? food : 0;
                        total += cost;
                        total = total * people * nights;
                        priceLabel.setText(total + " Zł");
                    } else {
                        JOptionPane.showMessageDialog(null, "Prosze wprowadz poprawna liczbe");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Blad przeliczania kwoty: " + e.getMessage());
        }
    }

    void bookHotel() {
        try (Connect con = createConnect();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO bookhotel (username, destination, hotel, people, food, phone, price) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, usernameLabel.getText());
            stmt.setString(2, destinationPackage.getSelectedItem());
            stmt.setString(3, hotelPackage.getSelectedItem());
            stmt.setString(4, peopleTextField.getText());
            stmt.setString(5, foodChoice.getSelectedItem());
            stmt.setString(6, phoneLabel.getText());
            stmt.setString(7, priceLabel.getText());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Oferta zarezerwowana z sukcesem!");
            setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Blad rezerwacji hotelu: " + e.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkPrice) {
            calculatePrice();
        } else if (ae.getSource() == bookHotelButton) {
            bookHotel();
        } else {
            setVisible(false);
        }
    }

    public Choice getDestinationPackage() {
        return destinationPackage;
    }

    public Choice getHotelPackage() {
        return hotelPackage;
    }

    public JTextField getPeopleTextField() {
        return peopleTextField;
    }

    public JTextField getNightsTextField() {
        return nightsTextField;
    }

    public Choice getFoodChoice() {
        return foodChoice;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

}