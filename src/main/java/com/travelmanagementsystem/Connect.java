package com.travelmanagementsystem;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class Connect implements AutoCloseable {
    Connection c;
    Statement s;

    Connect() {
        try {
            Properties props = new Properties();
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                props.load(in);
            }

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url, user, password);
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