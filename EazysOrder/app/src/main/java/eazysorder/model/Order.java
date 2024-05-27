package eazysorder.model;

public class Order {
    private int id;
    private int foodId;
    private int quantity;
    private String customerName;
    private String foodName;

    // Constructors
    public Order(int id, int foodId, int quantity, String customerName) {
        this.id = id;
        this.foodId = foodId;
        this.quantity = quantity;
        this.customerName = customerName;
    }

    public Order(int foodId, int quantity, String customerName) {
        this.foodId = foodId;
        this.quantity = quantity;
        this.customerName = customerName;
    }

    // Default constructor for use in OrderController
    public Order() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                ", customerName='" + customerName + '\'' +
                ", foodName='" + foodName + '\'' +
                '}';
    }
}
