package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    String URI = "jdbc:mysql://localhost:3306/bank_jdbc";
    String USER = "root";
    String PASSWORD = "test";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URI, USER, PASSWORD);
    }
}
