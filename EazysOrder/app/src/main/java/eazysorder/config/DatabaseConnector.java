package eazysorder.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:sqlite:database.sqlite";
    private static DatabaseConnector instance;
    private Connection connection;

    // Private constructor untuk mencegah pembuatan instans langsung
    public DatabaseConnector() {
        connect();
        createTables();
    }

    // Metode untuk mendapatkan instans tunggal dari DatabaseConnector
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            // Buat instans jika belum ada
            instance = new DatabaseConnector();
        }
        return instance;
    }

    // Metode untuk membuat koneksi ke database
    private void connect() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Database connected.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Metode untuk mendapatkan koneksi
    public Connection getConnection() {
        return connection;
    }

    // Metode untuk membuat tabel jika belum ada
    private void createTables() {
        createFoodsTable();
        createOrdersTable();
    }

    // Metode untuk membuat tabel foods
    private void createFoodsTable() {
        String createFoodsTableSQL = "CREATE TABLE IF NOT EXISTS foods (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price REAL NOT NULL," +
                "image_path TEXT" +
                ")";

        try {
            connection.createStatement().executeUpdate(createFoodsTableSQL);
            System.out.println("Foods table created.");
        } catch (SQLException e) {
            System.out.println("Failed to create Foods table.");
            e.printStackTrace();
        }
    }

    // Metode untuk membuat tabel orders
    private void createOrdersTable() {
        String createOrdersTableSQL = "CREATE TABLE IF NOT EXISTS orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "customer_name TEXT NOT NULL," +
                "total_price REAL NOT NULL," +
                "order_details TEXT NOT NULL" +
                ")";

        try {
            connection.createStatement().executeUpdate(createOrdersTableSQL);
            System.out.println("Orders table created.");
        } catch (SQLException e) {
            System.out.println("Failed to create Orders table.");
            e.printStackTrace();
        }
    }

    // Metode untuk menutup koneksi
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close database connection.");
                e.printStackTrace();
            }
        }
    }
}
