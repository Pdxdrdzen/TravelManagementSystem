package com.travelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewCustomer extends JFrame implements ActionListener {
    private JButton back;
    private final JLabel[] valueLabels = new JLabel[9];

    private JLabel createAndAddLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        add(label);
        return label;
    }

    ViewCustomer(String username) {
        setBounds(450, 180, 870, 625);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Lewa kolumna
        String[] leftLabels = {"Nazwa użytkownika", "ID", "Numer telefonu", "Imię", "Płeć"};
        int[] leftY = {50, 110, 170, 230, 290};

        // Prawa kolumna
        String[] rightLabels = {"Kraj", "Adres", "Numer telefonu", "Adres email"};
        int[] rightY = {50, 110, 170, 230};

        // Tworzenie etykiet
        for (int i = 0; i < leftLabels.length; i++) {
            createAndAddLabel(leftLabels[i], 30, leftY[i]);
            valueLabels[i] = createAndAddLabel("", 220, leftY[i]);
        }

        for (int i = 0; i < rightLabels.length; i++) {
            createAndAddLabel(rightLabels[i], 500, rightY[i]);
            valueLabels[i + 5] = createAndAddLabel("", 690, rightY[i]);
        }

        // Przycisk powrotu
        back = new JButton("Wróć");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(350, 350, 100, 25);
        back.addActionListener(this);
        add(back);

        // Dodawanie obrazu
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ikony/ViewCustomer.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(120, 400, 600, 200);
        add(image);

        loadCustomerData(username);
        setVisible(true);
    }

    private void loadCustomerData(String username) {
        try (Connect con = new Connect()) {
            String query = "SELECT * FROM customer WHERE username = ?";
            try (PreparedStatement stmt = con.getConnection().prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String[] columns = {"username", "id", "number", "name", "gender",
                            "country", "address", "phone", "email"};
                    for (int i = 0; i < columns.length; i++) {
                        valueLabels[i].setText(rs.getString(columns[i]));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Błąd", e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}