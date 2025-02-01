package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;

public class Destinations extends JFrame implements Runnable{

    private transient Thread t1;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JLabel l6;
    JLabel l7;
    JLabel l8;
    JLabel l9;
    JLabel l10;
    JLabel[] label = new JLabel[]{l1,l2,l3,l4,l5,l6,l7,l8,l9,l10};
    JLabel country;
    String[] text2=new String[]{"Hurghada, Egipt","Portomaso, Malta","Alanya, Turcja","Las Palmas, Hiszpania","Adeje, Teneryfa","Norwegia","Cabanas, Portugalia","Gdańsk, Polska","Amsterdam, Holandia","Kreta, Grecja"};

    public void run(){
        try{
            for(int i=0;i<=9;i++){
                label[i].setVisible(true);
                country.setText(text2[i]);
                Thread.sleep(2500);
                label[i].setVisible(false);
            }


        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    Destinations() {
        setBounds(500,200,800,600);


        country=new JLabel();
        country.setBounds(50,450,1000,70);
        country.setFont(new Font("Tahoma",Font.BOLD,40));
        country.setForeground(Color.WHITE);
        add(country);

        ImageIcon i1 = null;
        ImageIcon i2=null;
        ImageIcon i3=null;
        ImageIcon i4=null;
        ImageIcon i5=null;
        ImageIcon i6=null;
        ImageIcon i7=null;
        ImageIcon i8=null;
        ImageIcon i9=null;
        ImageIcon i10=null;

        ImageIcon[] image = new ImageIcon[]{i1, i2 , i3 , i4 , i5 , i6 , i7 , i8 , i9, i10 };

        Image j1 = null;
        Image j2=null;
        Image j3=null;
        Image j4=null;
        Image j5=null;
        Image j6=null;
        Image j7=null;
        Image j8=null;
        Image j9=null;
        Image j10=null;


        Image[] jimage = new Image[]{ j1, j2 , j3 , j4 , j5 , j6 , j7 , j8 , j9, j10 };

        ImageIcon k1 = null;
        ImageIcon k2=null;
        ImageIcon k3=null;
        ImageIcon k4=null;
        ImageIcon k5=null;
        ImageIcon k6=null;
        ImageIcon k7=null;
        ImageIcon k8=null;
        ImageIcon k9=null;
        ImageIcon k10=null;
        ImageIcon[] kimage = new ImageIcon[]{k1, k2 , k3 , k4 , k5 , k6 , k7 , k8 , k9, k10 };





        for(int i=0;i<=9;i++) {
            image[i]= new ImageIcon(ClassLoader.getSystemResource("ikony/destination"+(i+1)+".jpg"));
            jimage[i]= image[i].getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
            kimage[i] = new ImageIcon(jimage[i]);
            label[i] = new JLabel(kimage[i]);
            label[i].setBounds(0, 0, 800, 600);
            add(label[i]);

        }
        t1=new Thread(this);
        t1.start();

        setVisible(true);


    }
}
