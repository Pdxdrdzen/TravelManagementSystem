package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;

public class CheckHotels extends JFrame implements Runnable {

    private transient Thread t1;
    private JLabel[] label;
    private JLabel caption;
    private JLabel country;

    /**
     * String zawierający wszystkie dostępne hotele
     */
    private final String[] text = {
            "The V Luxury Resort", "Hilton St. Julian's", "Alan Xafira Deluxe Resort",
            "Barcelo Lanzarote Active Resort", "Hard Rock Hotel", "Fuerteventura Princess Hotel",
            "AP Cabanas Beach & Nature", "Hilton Hotel", "De L'Europe Hotel", "Stella Island Luxury Hotel"
    };
    /**
     * String zawierający wszystkie dostępne kierunki podróży dla użytkownika
     */
    private final String[] text2 = {
            "Hurghada, Egipt", "Portomaso, Malta", "Alanya, Turcja",
            "Las Palmas, Hiszpania", "Adeje, Teneryfa", "Gran Canaria, Hiszpania",
            "Cabanas, Portugalia", "Gdańsk, Polska", "Amsterdam, Holandia", "Wyspa Stella, Grecja"
    };

    /**
     * Funkcja służąca do obsługi "slideshow" hoteli na ekranie, wymagane do obsługi wątków
     */
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                label[i].setVisible(true);
                caption.setText(text[i]);
                country.setText(text2[i]);
                Thread.sleep(2500);
                label[i].setVisible(false);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Ponowne ustawienie flagi przerwania
            e.printStackTrace();
        }
    }

    /**
     * Konstruktor klasy CheckHotels
     */
    CheckHotels() {
        setBounds(500, 200, 800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        caption = new JLabel();
        caption.setBounds(50, 500, 1000, 70);
        caption.setFont(new Font("Tahoma", Font.BOLD, 40));
        caption.setForeground(Color.WHITE);
        add(caption);

        country = new JLabel();
        country.setBounds(50, 450, 1000, 70);
        country.setFont(new Font("Tahoma", Font.BOLD, 40));
        country.setForeground(Color.WHITE);
        add(country);

        label = new JLabel[10];
        ImageIcon[] image = new ImageIcon[10];
        Image[] jimage = new Image[10];
        ImageIcon[] kimage = new ImageIcon[10];

        for (int i = 0; i < 10; i++) {
            image[i] = new ImageIcon(ClassLoader.getSystemResource("ikony/hotel" + (i + 1) + ".jpg"));
            jimage[i] = image[i].getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
            kimage[i] = new ImageIcon(jimage[i]);
            label[i] = new JLabel(kimage[i]);
            label[i].setBounds(0, 0, 800, 600);
            add(label[i]);
        }

        t1 = new Thread(this);
        t1.start();

        setVisible(true);
    }

}
