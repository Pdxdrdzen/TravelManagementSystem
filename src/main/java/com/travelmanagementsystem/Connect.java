package com.travelmanagementsystem;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

/**
 * Klasa Connect - Klasa służąca do nawiązania połączenia z bazą danych
 */
public class Connect implements AutoCloseable {
    Connection c;
    Statement s;

    /**
     * Konstruktor klasy Connect, sluzacy do zainicjalizowania połączenia z bazą na podstawie danych w database.properties
     */
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
            throw new RuntimeException();
        }
    }
    public Connection getConnection() {
        return c;
    }

    /**
     * Funkcja sluzaca do wystartowania PreparedStatement
     * @param query
     * @return
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return c.prepareStatement(query);
    }

    /**
     * Implementacja close() z klasy AutoCloseable
     * @throws SQLException
     */
    @Override
    public void close() throws SQLException {
        if (s != null) s.close();
        if (c != null) c.close();
    }
}