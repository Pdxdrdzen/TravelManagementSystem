package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class About extends JFrame implements ActionListener {
    JButton back;

    String message= """
         **O nas**

           Witaj w Travel Management System! Tworzymy wyjątkowe podróże dla tych, którzy chcą odkrywać świat w niepowtarzalny sposób. Nasza pasja do podróży i wieloletnie doświadczenie pozwalają nam oferować dopracowane wyjazdy – od egzotycznych wakacji, przez city breaki, po aktywne przygody.

           Dlaczego to robimy? Bo wierzymy, że podróże to coś więcej niż zwiedzanie – to emocje, wspomnienia i nowe perspektywy. Dbamy o każdy detal, abyś mógł cieszyć się wyprawą bez stresu.

           Oferujemy szeroką gamę wyjazdów dostosowanych do Twoich marzeń – indywidualne plany, wycieczki grupowe i ekskluzywne wyprawy. Z nami podróżowanie staje się proste i pełne wrażeń. Wyrusz z nami w niezapomnianą podróż! 🌍✈️
""";

    /**
     * Konstruktor klasy About, zawierający inicjalizacje wszelakopojęte, takie jak ustawienia okna, ustawienia pól tekstowych itd
     */
    About(){
        setBounds(600,200,500,550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        JLabel l1=new JLabel("O NAS");
        l1.setBounds(150,10,100,40);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(l1);

        TextArea area=new TextArea(message,10,40, Scrollbar.VERTICAL);
        area.setEditable(false);
        area.setBounds(20,100,450,300);
        add(area);

        back=new JButton("Powrót");
        back.setBounds(200,420,100,25);
        back.addActionListener(this);
        add(back);

        setVisible(true);



    }

    /**
     * Wymagana do przesłonięcia funkcja, która przypisuje konkretnie działanie do naciśniętego przycisku przez użytkownika
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
        }
    }
}
