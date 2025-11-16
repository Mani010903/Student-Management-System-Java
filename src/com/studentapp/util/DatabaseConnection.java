package com.studentapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5433/studentdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234"; // <-- replace this

    static {
        try {
            Class.forName("org.postgresql.Driver"); // ensure driver loaded
        } catch (ClassNotFoundException e) {
            System.err.println("Postgres JDBC driver not found!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
