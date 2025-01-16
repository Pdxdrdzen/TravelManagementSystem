package com.example.TravelManagementSystem;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    String username;
    JButton addPersonalDetails, viewPersonalDetails, updatePersonalDetails, deletePersonalDetails, checkpackages, bookpackages,viewpackages;
    Dashboard(String username){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0,0,102));
        p1.setBounds(0,0,1600,65);
        add(p1);

        ImageIcon icon= new ImageIcon(ClassLoader.getSystemResource("ikony/ikona_tms.png"));
        Image i2=icon.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon icon2=new ImageIcon(i2);
        JLabel l1=new JLabel(icon2);
        l1.setBounds(5,0,70,70);
        p1.add(l1);

        JLabel heading=new JLabel("Panel Biura Podrozy");
        heading.setBounds(80,10,300,40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial",Font.BOLD,30));
        p1.add(heading);

        JPanel p2=new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(0,0,102));
        p2.setBounds(0,65,300,900);
        add(p2);

        addPersonalDetails = new JButton("Dodaj dane osobowe");
        addPersonalDetails.setBounds(0,0,300,50);
        addPersonalDetails.setBackground(new Color(0,0,102));
        addPersonalDetails.setForeground(Color.WHITE);
        addPersonalDetails.setFont(new Font("Tahoma",Font.PLAIN,20));
        addPersonalDetails.setMargin(new Insets(0,0,0,50));
        addPersonalDetails.addActionListener(this);
        p2.add(addPersonalDetails);

        updatePersonalDetails = new JButton("Aktualizuj dane osobowe");
        updatePersonalDetails.setBounds(0,50,300,50);
        updatePersonalDetails.setBackground(new Color(0,0,102));
        updatePersonalDetails.setForeground(Color.WHITE);
        updatePersonalDetails.setFont(new Font("Tahoma",Font.PLAIN,20));
        updatePersonalDetails.setMargin(new Insets(0,0,0,20));
        updatePersonalDetails.addActionListener(this);
        p2.add(updatePersonalDetails);

        viewPersonalDetails = new JButton("Wyswietl dane osobowe");
        viewPersonalDetails.setBounds(0,100,300,50);
        viewPersonalDetails.setBackground(new Color(0,0,102));
        viewPersonalDetails.setForeground(Color.WHITE);
        viewPersonalDetails.setFont(new Font("Tahoma",Font.PLAIN,20));
        viewPersonalDetails.setMargin(new Insets(0,0,0,30));
        viewPersonalDetails.addActionListener(this);
        p2.add(viewPersonalDetails);

        JButton deletePersonalDetails = new JButton("Usun dane osobowe");
        deletePersonalDetails.setBounds(0,150,300,50);
        deletePersonalDetails.setBackground(new Color(0,0,102));
        deletePersonalDetails.setForeground(Color.WHITE);
        deletePersonalDetails.setFont(new Font("Tahoma",Font.PLAIN,20));
        deletePersonalDetails.setMargin(new Insets(0,0,0,60));
        p2.add(deletePersonalDetails);

        checkpackages = new JButton("Sprawdz dostepne wycieczki");
        checkpackages.setBounds(0,200,300,50);
        checkpackages.setBackground(new Color(0,0,102));
        checkpackages.setForeground(Color.WHITE);
        checkpackages.setFont(new Font("Tahoma",Font.PLAIN,20));
        checkpackages.setMargin(new Insets(0,0,0,0));
        checkpackages.addActionListener(this);
        p2.add(checkpackages);

        bookpackages = new JButton("Zarezerwuj wycieczke");
        bookpackages.setBounds(0,250,350,50);
        bookpackages.setBackground(new Color(0,0,102));
        bookpackages.setForeground(Color.WHITE);
        bookpackages.setFont(new Font("Tahoma",Font.PLAIN,20));
        bookpackages.setMargin(new Insets(0,0,0,110));
        bookpackages.addActionListener(this);
        p2.add(bookpackages);

        viewpackages = new JButton("Wyswietl swoje wycieczki");
        viewpackages.setBounds(0,300,350,50);
        viewpackages.setBackground(new Color(0,0,102));
        viewpackages.setForeground(Color.WHITE);
        viewpackages.setFont(new Font("Tahoma",Font.PLAIN,20));
        viewpackages.setMargin(new Insets(0,0,0,70));
        viewpackages.addActionListener(this);
        p2.add(viewpackages);

        JButton viewhotels = new JButton("Wyswietl dostepne noclegi");
        viewhotels.setBounds(0,350,350,50);
        viewhotels.setBackground(new Color(0,0,102));
        viewhotels.setForeground(Color.WHITE);
        viewhotels.setFont(new Font("Tahoma",Font.PLAIN,20));
        viewhotels.setMargin(new Insets(0,0,0,60));
        p2.add(viewhotels);

        JButton bookhotels = new JButton("Zarezerwuj nocleg");
        bookhotels.setBounds(0,400,350,50);
        bookhotels.setBackground(new Color(0,0,102));
        bookhotels.setForeground(Color.WHITE);
        bookhotels.setFont(new Font("Tahoma",Font.PLAIN,20));
        bookhotels.setMargin(new Insets(0,0,0,120));
        p2.add(bookhotels);

        JButton viewBookedHotels = new JButton("Wyswietl swoje rezerwacje ");
        viewBookedHotels.setBounds(0,450,350,50);
        viewBookedHotels.setBackground(new Color(0,0,102));
        viewBookedHotels.setForeground(Color.WHITE);
        viewBookedHotels.setFont(new Font("Tahoma",Font.PLAIN,20));
        viewBookedHotels.setMargin(new Insets(0,0,0,40));
        p2.add(viewBookedHotels);

        JButton destinations = new JButton("Wyswietl dostepne kierunki");
        destinations.setBounds(0,500,350,50);
        destinations.setBackground(new Color(0,0,102));
        destinations.setForeground(Color.WHITE);
        destinations.setFont(new Font("Tahoma",Font.PLAIN,20));
        destinations.setMargin(new Insets(0,0,0,40));
        p2.add(destinations);

        JButton payments = new JButton("Twoje platnosci");
        payments.setBounds(0,550,350,50);
        payments.setBackground(new Color(0,0,102));
        payments.setForeground(Color.WHITE);
        payments.setFont(new Font("Tahoma",Font.PLAIN,20));
        payments.setMargin(new Insets(0,0,0,140));
        p2.add(payments);

        JButton calculators = new JButton("Kalkulator");
        calculators.setBounds(0,600,350,50);
        calculators.setBackground(new Color(0,0,102));
        calculators.setForeground(Color.WHITE);
        calculators.setFont(new Font("Tahoma",Font.PLAIN,20));
        calculators.setMargin(new Insets(0,0,0,180));
        p2.add(calculators);

        JButton notepad = new JButton("Twoje notatki");
        notepad.setBounds(0,650,350,50);
        notepad.setBackground(new Color(0,0,102));
        notepad.setForeground(Color.WHITE);
        notepad.setFont(new Font("Tahoma",Font.PLAIN,20));
        notepad.setMargin(new Insets(0,0,0,160));
        p2.add(notepad);

        JButton wiecej = new JButton("Wiecej");
        wiecej.setBounds(0,700,350,50);
        wiecej.setBackground(new Color(0,0,102));
        wiecej.setForeground(Color.WHITE);
        wiecej.setFont(new Font("Tahoma",Font.PLAIN,20));
        wiecej.setMargin(new Insets(0,0,0,160));
        p2.add(wiecej);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("ikony/home_tms.jpg"));
        Image i5=i4.getImage().getScaledInstance(1650,1000,Image.SCALE_DEFAULT);
        ImageIcon i6 =new ImageIcon(i5);
        JLabel image=new JLabel(i6);
        image.setBounds(0,0,1650,1000);
        add(image);











        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addPersonalDetails){
            new AddCustomer(username);
        }else if(e.getSource()==viewPersonalDetails){
            new ViewCustomer(username);
        }else if(e.getSource()==updatePersonalDetails){
            new UpdateCustomer(username);
        }else if(e.getSource()==checkpackages){
            new CheckPackage();
        }else if(e.getSource()==bookpackages) {
            new BookPackage(username);

        }else if(e.getSource()==viewpackages) {
            new ViewBookedPackage(username);
        }
    }

    public static void main(String[] args){
        new Dashboard("");
    }


}
