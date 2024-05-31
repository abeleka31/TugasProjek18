package eazysorder.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractOrder {
    protected int id;
    protected String customerName;
    protected double totalPrice;
    protected List<String> orderDetails;

    // Constructor for creating a new Order object without ID
    public AbstractOrder(String customerName, double totalPrice, List<String> orderDetails) {
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }

    // Constructor for creating an Order object from a database query result
    public AbstractOrder(int id, String customerName, double totalPrice, List<String> orderDetails) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }

    // Abstract method for saving an order to the database
    public abstract void saveOrder();

    // Method to convert the orderDetails list to a single string with newline as a separator
    public String getOrderDetailsAsString() {
        return String.join("\n", orderDetails);
    }

    // Getters and setters for all fields
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

    // Method to create an Order object from a ResultSet produced by a database query
    public static AbstractOrder fromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String customerName = rs.getString("customer_name");
        double totalPrice = rs.getDouble("total_price");
        String orderDetails = rs.getString("order_details");
        String[] detailsArray = orderDetails.split("\n");
        return new Order(id, customerName, totalPrice, List.of(detailsArray));
    }
}
