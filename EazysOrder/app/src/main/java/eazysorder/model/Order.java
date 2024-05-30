package eazysorder.model;

import eazysorder.config.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Order {
    private int id;
    private String customerName;
    private double totalPrice;
    private List<String> orderDetails;

    // Constructor untuk membuat objek Order baru tanpa ID
    public Order(String customerName, double totalPrice, List<String> orderDetails) {
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }

    // Constructor untuk membuat objek Order dari hasil kueri database
    public Order(int id, String customerName, double totalPrice, List<String> orderDetails) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }

    // Method untuk menyimpan order ke database
    public void saveOrder() {
        try {
            Connection connection = DatabaseConnector.getInstance().getConnection();
            String insertOrderSQL = "INSERT INTO orders (customer_name, total_price, order_details) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customerName);
            preparedStatement.setDouble(2, totalPrice);
            preparedStatement.setString(3, getOrderDetailsAsString());
            preparedStatement.executeUpdate();

            // Mengambil ID yang di-generate oleh database
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

    // Method untuk mengubah daftar orderDetails menjadi satu string dengan newline sebagai pemisah
    public String getOrderDetailsAsString() {
        return String.join("\n", orderDetails);
    }

    // Getter dan setter untuk semua field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<String> orderDetails) {
        this.orderDetails = orderDetails;
    }

    // Method untuk membuat objek Order dari ResultSet yang dihasilkan dari kueri database
    public static Order fromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String customerName = rs.getString("customer_name");
        double totalPrice = rs.getDouble("total_price");
        String orderDetails = rs.getString("order_details");
        String[] detailsArray = orderDetails.split("\n");
        return new Order(id, customerName, totalPrice, List.of(detailsArray));
    }
}
