package eazysorder.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnector {
    private static final String url = "jdbc:sqlite:dataBase.sqlite";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void insertImage(int id, byte[] image) {
        String sql = "INSERT INTO food(id, image) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setBytes(2, image);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static byte[] getImageById(int id) {
        String sql = "SELECT image FROM food WHERE id = ?";
        byte[] imageBytes = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                imageBytes = rs.getBytes("image");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return imageBytes;
    }
}
