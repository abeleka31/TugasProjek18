package eazysorder.model;

import eazysorder.config.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Order extends AbstractOrder {

    // Constructor for creating a new Order object without ID
    public Order(String customerName, double totalPrice, List<String> orderDetails) {
        super(customerName, totalPrice, orderDetails);
    }

    // Constructor for creating an Order object from a database query result
    public Order(int id, String customerName, double totalPrice, List<String> orderDetails) {
        super(id, customerName, totalPrice, orderDetails);
    }

    // Override the saveOrder method to provide the specific implementation for saving an order to the database
    @Override
    public void saveOrder() {
        try {
            Connection connection = DatabaseConnector.getInstance().getConnection();
            String insertOrderSQL = "INSERT INTO orders (customer_name, total_price, order_details) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customerName);
            preparedStatement.setDouble(2, totalPrice);
            preparedStatement.setString(3, getOrderDetailsAsString());
            preparedStatement.executeUpdate();

            // Retrieve the generated ID from the database
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            System.out.println("Order saved to database.");
        } catch (SQLException e) {
            System.out.println("Failed to save order to database.");
            e.printStackTrace();
        }
    }

    // Optionally, you can override other methods if specific functionality needs to be added or modified
}
