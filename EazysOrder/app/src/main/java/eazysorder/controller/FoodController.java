package eazysorder.controller;


import eazysorder.config.*;
import eazysorder.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodController {
  public void addFood(Food food) {
    String sql = "INSERT INTO foods(name, price) VALUES(?,?)";

    try (Connection conn = DatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, food.getName());
      pstmt.setDouble(2, food.getPrice());
      pstmt.executeUpdate();
      System.out.println("Food added successfully");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Food> getAllFoods() {
    List<Food> foods = new ArrayList<>();
    String sql = "SELECT * FROM foods";

    try (Connection conn = DatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        Food food = new Food(rs.getString("name"), rs.getDouble("price"));
        food.setId(rs.getInt("id"));
        foods.add(food);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return foods;
  }

  public void updateFood(Food food) {
    String sql = "UPDATE foods SET name = ?, price = ? WHERE id = ?";

    try (Connection conn = DatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, food.getName());
      pstmt.setDouble(2, food.getPrice());
      pstmt.setInt(3, food.getId());
      pstmt.executeUpdate();
      System.out.println("Food updated successfully");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteFood(int id) {
    String sql = "DELETE FROM foods WHERE id = ?";

    try (Connection conn = DatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      System.out.println("Food deleted successfully");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
