package eazysorder.controller;

import eazysorder.config.DatabaseConnector;
import eazysorder.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public void createOrder(String customerName, double totalPrice, List<String> orderDetails) {
        Order order = new Order(customerName, totalPrice, orderDetails);
        order.saveOrder();
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.getInstance().getConnection();
            String selectAllOrdersSQL = "SELECT * FROM orders";
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllOrdersSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = (Order) Order.fromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch orders from database.");
            e.printStackTrace();
        }
        return orders;
    }
}
