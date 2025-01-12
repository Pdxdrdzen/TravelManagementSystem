package com.example.TravelManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UpdateCustomer extends JFrame implements ActionListener {

    JComboBox comboID;
    JLabel labelusername,labelname;
    JTextField tfusername,tfName, tfNumber, tfCountry,tfAddress,tfPhone,tfEmail, tfID, tfGender;
    JRadioButton rmale,rfemale,rother;
    JButton add,back,update;
    UpdateCustomer(String username){
        setTitle("Aktualizuj dane klienta");
        setBounds(500,200,850,550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text=new JLabel("Aktualizuj dane Klienta");
        text.setBounds(50,0,300,25);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);

        JLabel lblUserName = new JLabel("Nazwa uzytkownika");
        lblUserName.setBounds(30,50,150,25);
        add(lblUserName);

        labelusername = new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);

        JLabel customerId = new JLabel("ID");
        customerId.setBounds(30,90,150,25);
        add(customerId);

        tfID = new JTextField();
        tfID.setBounds(220,90,150,25);
        add(tfID);

        JLabel lbNumber = new JLabel("Numer");
        lbNumber.setBounds(30,130,150,25);
        add(lbNumber);

        tfNumber = new JTextField();
        tfNumber.setBounds(220,130,150,25);
        add(tfNumber);

        JLabel lblName = new JLabel("Imie klienta");
        lblName.setBounds(30,170,150,25);
        add(lblName);

        labelname = new JLabel();
        labelname.setBounds(220,170,150,25);
        add(labelname);

        JLabel lblGender = new JLabel("Płeć");
        lblGender.setBounds(30,210,150,25);
        add(lblGender);


        tfGender = new JTextField();
        tfGender.setBounds(220,210,150,25);
        add(tfGender);


        JLabel lbCountry = new JLabel("Kraj");
        lbCountry.setBounds(30,250,150,25);
        add(lbCountry);

        tfCountry = new JTextField();
        tfCountry.setBounds(220,250,150,25);
        add(tfCountry);

        JLabel lbAddress = new JLabel("Adres");
        lbAddress.setBounds(30,290,150,25);
        add(lbAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(220,290,150,25);
        add(tfAddress);

        JLabel lbPhone = new JLabel("Numer telefonu");
        lbPhone.setBounds(30,330,150,25);
        add(lbPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(220,330,150,25);
        add(tfPhone);

        JLabel lbEmail = new JLabel("Adres Email");
        lbEmail.setBounds(30,370,150,25);
        add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(220,370,150,25);
        add(tfEmail);

        update = new JButton("Zaktualizuj dane");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70,420,150,25);
        update.addActionListener(this);
        add(update);

        back=new JButton("Wstecz");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250,420,150,25);
        back.addActionListener(this);
        add(back);

        try{
            Connect con = new Connect();
            ResultSet rs= con.s.executeQuery("select * from customer where username = '"+username+"' ");
            while(rs.next()){
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
                tfID.setText(rs.getString("id"));
                tfNumber.setText(rs.getString("number"));
                tfGender.setText(rs.getString("gender"));
                tfCountry.setText(rs.getString("country"));
                tfAddress.setText(rs.getString("address"));
                tfPhone.setText(rs.getString("phone"));
                tfEmail.setText(rs.getString("email"));

            }


        }catch(Exception e){
            e.printStackTrace();
        }



        setVisible(true);



    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==update){
            String username=labelusername.getText();
            String customerId= tfID.getText();
            String number=tfNumber.getText();
            String name=labelname.getText();
            String gender= tfGender.getText();
            String country=tfCountry.getText();
            String address=tfAddress.getText();
            String phone=tfPhone.getText();
            String email=tfEmail.getText();

            try{
                Connect con = new Connect();
                String query="update customer set username= '"+username+"', id = '"+customerId+"', number = '"+number+ "', name ='"+name+"', gender ='"+gender+"', country ='"+country+"', address ='"+address+"', phone ='"+phone+"', email ='"+email+"'";
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Dane klienta zaktualizowane poprawnie");
                setVisible(false);

            }catch(Exception e){
                e.printStackTrace();
            }



        }else{
            setVisible(false);


        }
    }
    public static void main(String[] args) {
        new UpdateCustomer("pdrdzen");
    }

}
