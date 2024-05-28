package eazysorder.controller;

import eazysorder.config.DatabaseConnector;
import eazysorder.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodController {

    public Food addFood(String name, double price, String imagePath) {
        String sql = "INSERT INTO food (name, price, imagePath) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, imagePath);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new Food(id, name, price, imagePath);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM food";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String imagePath = rs.getString("imagePath");
                foodList.add(new Food(id, name, price, imagePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodList;
    }

    public boolean updateFood(Food food) {
        String sql = "UPDATE food SET name = ?, price = ?, imagePath = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, food.getName());
            pstmt.setDouble(2, food.getPrice());
            pstmt.setString(3, food.getImagePath());
            pstmt.setInt(4, food.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFood(int id) {
        String sql = "DELETE FROM food WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
