package eazysorder.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Ganti dengan path yang benar ke database Anda
            String url = "jdbc:sqlite:dataBase.sqlite";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
