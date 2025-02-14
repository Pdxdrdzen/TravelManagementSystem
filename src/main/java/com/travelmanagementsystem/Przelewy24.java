package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa Przelewy24 - służy do wyświetlenia kontekstu strony przelewy24.
 * Na potrzeby projektu, nie jest możliwe faktyczne dokonanie płatności przez tę stronę
 * (ponieważ jest to dodatkowo płatne)
 * Natomiast jako przykład działania przenosi użytkownika na strone głowna, w tym wypadku w charakterze html.
 */
public class Przelewy24 extends JFrame implements ActionListener {
    JButton back;
    Przelewy24(){
        setBounds(500,200,800,600);
        JEditorPane pane=new JEditorPane();
        pane.setEditable(false);
        try{
            pane.setPage("https://www.przelewy24.pl/");

        }catch (Exception e){
            pane.setContentType("text/html");
            pane.setText("<html>Blad ladowania, Error 404</html>");
        }

        JScrollPane sp=new JScrollPane(pane);
        getContentPane().add(sp);

        back=new JButton("Powrót");
        back.setBounds(610,20,80,40);
        back.addActionListener(this);
        pane.add(back);

        setVisible(true);
    }

    /**
     * W tym wypadku actionPerformed daje możliwość powrotu do Dashboard po naciśnięciu przycisku
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        setVisible(false);
        new Payment();

    }

}
