package Travel_Management_System;

import java.awt.*;
import javax.swing.*;
public class Splash extends JFrame {

    Splash() {
      //  setSize(1200,600);
        //setLocation(200,100);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ikony/tlo_tms.jpg"));
        Image i2 =i1.getImage().getScaledInstance(1200,600,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add (image);
        setVisible(true);

    }
    public static void main(String[] args) {

        Splash frame= new Splash();
        int x=1;
        for(int i=1;i<=500;x+=7,i+=6){
            frame.setLocation(750-(x+1)/2,400-(i/2));
            frame.setSize(i*2,i);
            try{
                Thread.sleep(10);
            } catch(Exception e){}
        }
    }
}
