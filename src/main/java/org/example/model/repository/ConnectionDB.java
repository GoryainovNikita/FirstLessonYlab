package org.example.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/ylab?currentSchema=ylab";
    private static final String USER_NAME = "user";
    private static final String PASSWORD = "root";

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
