package eazysorder.controller;

import eazysorder.config.DatabaseConnector;
import eazysorder.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodController {

    public void addFood(String name, double price) {
        String sql = "INSERT INTO Food(name, price) VALUES(?,?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Food> getAllFood() {
        String sql = "SELECT id, name, price FROM Food";
        List<Food> foodList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Food food = new Food(0, rs.getString("name"), rs.getDouble("price"));
                foodList.add(food);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return foodList;
    }
}
