package com.travelmanagementsystem;

import java.sql.*;

public class Connect implements AutoCloseable {
    Connection c;
    Statement s;

    Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency", "root", "Kochamkk123.");
            s = c.createStatement();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return c.prepareStatement(query);
    }

    @Override
    public void close() throws SQLException {
        if (s != null) s.close();
        if (c != null) c.close();
    }
}