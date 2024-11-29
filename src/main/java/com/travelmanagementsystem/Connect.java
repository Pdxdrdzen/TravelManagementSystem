package com.example.TravelManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Connect {
    Connection c;
    Statement s;
    Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");


            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency","root","Kochamkk123.");//seriokochamkamciez
            s=c.createStatement();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return c.prepareStatement(query);
    }
}
