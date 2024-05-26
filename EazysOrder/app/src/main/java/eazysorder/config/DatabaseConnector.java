package eazysorder.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
  private static final String URL = "jdbc:sqlite:dataBase.sqlite";

  public static Connection connect() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(URL);
      System.out.println("Berhasil connect");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }
}
