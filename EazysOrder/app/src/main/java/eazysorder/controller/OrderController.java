package eazysorder.controller;

import eazysorder.model.Order;
import eazysorder.config.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    public void addOrder(Order order) {
        String sql = "INSERT INTO orders(food_id, customer_name, quantity) VALUES(?,?,?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getFoodId());
            pstmt.setString(2, order.getCustomerName());
            pstmt.setInt(3, order.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.id, orders.customer_name, orders.quantity, food.name as food_name " +
                     "FROM orders JOIN food ON orders.food_id = food.id";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setQuantity(rs.getInt("quantity"));
                order.setFoodName(rs.getString("food_name"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orders;
    }
}
