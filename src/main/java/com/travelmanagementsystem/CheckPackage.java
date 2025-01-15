package com.example.TravelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class CheckPackage extends JFrame  {
    private JLabel countdown;
    private Timer timer;

    CheckPackage(){

        String[] package1={"ZŁOTA OFERTA", " 14 DNI NA TENERYFIE", "MIEJSCE W BIZNES KLASIE","WYCIECZKI MIEJSKIE Z PRZEWODNIKIEM NA ŻYCZENIE","CODZIENNIE PYSZNE EGZOTYCZNE ŚNIADANIA","(oraz jeszcze pyszniejsze obiado-kolacje..)","W CENIE 3 DNIOWY REJS PROMEM PO OCEANIE ATLANTYCKIM","ALKOHOLE/PRZEKĄSKI UNLIMITED 24/7","CODZIENNE ANIMACJE DLA DZIECI","REZERWUJ TERAZ","OFERTA LIMITOWANA CZASOWO","TYLKO TERAZ!","2200 ZŁ/OS","05:00:00", "ikony/package1.jpg"};
        String[] package2={"SREBRNA OFERTA", " 10 DNI NA MADERZE", "MIEJSCE W 1 KLASIE","WYCIECZKI MIEJSKIE Z PRZEWODNIKIEM 3 RAZY","CODZIENNIE PYSZNE EGZOTYCZNE ŚNIADANIA","(oraz jeszcze pyszniejsze obiado-kolacje..)","W CENIE 3 DNIOWY WYJAZD NA GÓRE TUTURURTU","ALKOHOLE/PRZEKĄSKI UNLIMITED DO GODZINY 22","CODZIENNE ANIMACJE DLA DZIECI","REZERWUJ TERAZ","OFERTA LIMITOWANA CZASOWO","TYLKO TERAZ!","2400 ZŁ/OS","09:00:00","ikony/package2.jpg"};
        String[] package3={"BRĄZOWA OFERTA"," 7 DNI NA GRAN CANARII", "MIEJSCE W KLASIE EKONOMICZNEJ","WYCIECZKA MIEJSKA Z PRZEWODNIKIEM", "CODZIENNIE PYSZNE EGZOTYCZNE ŚNIADANIA","OBIAD/KOLACJA WE WŁASNYM ZAKRESIE","W CENIE SPA I MASAŻE","ALKOHOLE/PRZEKĄSKI DO ZAKUPIENIA W BARZE","CODZIENNE ANIMACJE DLA DZIECI","REZERWUJ TERAZ","OFERTA LIMITOWANA CZASOWO","TYLKO TERAZ!","2000 ZŁ/OS","09:00:00","ikony/package3.jpg" };

        setBounds(450,200,900,600);
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel p1= createPackage(package1);
        tabbedPane.addTab("Oferta 1",null,p1);

        JPanel p2= createPackage(package2);
        tabbedPane.addTab("Oferta 2",null,p2);

        JPanel p3 = createPackage(package3);
        tabbedPane.addTab("Oferta 3",null,p3);

        add(tabbedPane);


        setVisible(true);

    }
    public JPanel createPackage(String[] packages){
        JPanel p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.WHITE);

        JLabel l1=new JLabel(packages[0]);
        l1.setBounds(50,25,330,30);
        l1.setForeground(Color.yellow);
        l1.setFont(new Font("Montserrat",Font.BOLD,30));
        p1.add(l1);

        JLabel l2 =new JLabel(packages[1]);
        l2.setBounds(30,65,500,30);
        l2.setForeground(Color.blue);
        l2.setFont(new Font("Montserrat",Font.BOLD,20));
        p1.add(l2);

        JLabel l3 =new JLabel(packages[2]);
        l3.setBounds(30,105,300,30);
        l3.setForeground(Color.black);
        l3.setFont(new Font("Montserrat",Font.BOLD,15));
        p1.add(l3);

        JLabel l4 =new JLabel(packages[3]);
        l4.setBounds(30,145,600,30);
        l4.setForeground(Color.green);
        l4.setFont(new Font("Montserrat",Font.BOLD,15));
        p1.add(l4);

        JLabel l5 =new JLabel(packages[4]);
        l5.setBounds(30,185,600,30);
        l5.setForeground(Color.red);
        l5.setFont(new Font("Montserrat",Font.BOLD,15));
        p1.add(l5);

        JLabel l6 =new JLabel(packages[5]);
        l6.setBounds(30,200,600,30);
        l6.setForeground(Color.lightGray);
        l6.setFont(new Font("Montserrat",Font.BOLD,10));
        p1.add(l6);

        JLabel l7 =new JLabel(packages[6]);
        l7.setBounds(30,240,800,30);
        l7.setForeground(Color.blue);
        l7.setFont(new Font("Montserrat",Font.BOLD,15));
        p1.add(l7);

        JLabel l8 =new JLabel(packages[7]);
        l8.setBounds(30,280,800,30);
        l8.setForeground(Color.orange);
        l8.setFont(new Font("Montserrat",Font.BOLD,15));
        p1.add(l8);

        JLabel l9 =new JLabel(packages[8]);
        l9.setBounds(30,320,800,30);
        l9.setForeground(Color.pink);
        l9.setFont(new Font("Montserrat",Font.BOLD,15));
        p1.add(l9);

        JLabel l10 =new JLabel(packages[9]);
        l10.setBounds(30,360,800,30);
        l10.setForeground(Color.black);
        l10.setFont(new Font("Montserrat",Font.BOLD,20));
        p1.add(l10);

        JLabel l11 =new JLabel(packages[10]);
        l11.setBounds(30,400,800,30);
        l11.setForeground(Color.black);
        l11.setFont(new Font("Montserrat",Font.BOLD,20));
        p1.add(l11);

        JLabel l12 =new JLabel(packages[11]);
        l12.setBounds(600,400,800,30);
        l12.setForeground(Color.black);
        l12.setFont(new Font("Montserrat",Font.BOLD,20));
        p1.add(l12);

        JLabel l13 =new JLabel(packages[12]);
        l13.setBounds(600,440,800,30);
        l13.setForeground(Color.black);
        l13.setFont(new Font("Montserrat",Font.BOLD,20));
        p1.add(l13);

        countdown=new JLabel(packages[13],SwingConstants.LEFT);
        countdown.setBounds(30,440,300,30);
        countdown.setFont(new Font("Montserrat",Font.BOLD,30));
        countdown.setForeground(Color.blue);
        p1.add(countdown);
        startCountdown();

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemClassLoader().getResource(packages[14]));
        Image i2=i1.getImage().getScaledInstance(500,300,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l14=new JLabel(i3);
        l14.setBounds(450,20,450,300);
        p1.add(l14);

       return p1;


    }
    private void startCountdown() {
        LocalTime startTime = LocalTime.of(9, 0, 0);
        long initialDelay = startTime.toSecondOfDay();

        timer = new Timer(1000, new ActionListener() {
            private long remainingSeconds = initialDelay;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingSeconds <= 0) {
                    timer.stop();
                    countdown.setText("Czas minął!");
                    return;
                }

                LocalTime currentTime = LocalTime.ofSecondOfDay(remainingSeconds);
                countdown.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                remainingSeconds--;
            }
        });

        timer.start();
    }
    public static void main(String[] args) {
        new CheckPackage();
    }
}
