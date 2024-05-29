package eazysorder.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eazysorder.config.DatabaseConnector;
import eazysorder.model.Food;

public class FoodController {
    private DatabaseConnector databaseController;

    public FoodController() {
        databaseController = new DatabaseConnector();
    }

    public Food addFood(String name, double price, String imagePath) {
        String sql = "INSERT INTO foods (name, price, image_path) VALUES (?, ?, ?)";
        try (PreparedStatement statement = databaseController.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, imagePath);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return getFoodById(getLastInsertedFoodId());
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    private int getLastInsertedFoodId() throws SQLException {
        String sql = "SELECT last_insert_rowid()";
        try (Statement statement = databaseController.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        throw new SQLException("Failed to get last inserted food ID.");
    }

    public Food getFoodById(int id) {
        String sql = "SELECT * FROM foods WHERE id = ?";
        try (PreparedStatement statement = databaseController.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String imagePath = resultSet.getString("image_path");
                    return new Food(id, name, price, imagePath);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    public List<Food> getAllFood() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods";
        try (Statement statement = databaseController.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String imagePath = resultSet.getString("image_path");
                foods.add(new Food(id, name, price, imagePath));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return foods;
    }

    private void handleSQLException(SQLException e) {
        // Handle SQLException based on your application's requirements
        e.printStackTrace();
    }
}
