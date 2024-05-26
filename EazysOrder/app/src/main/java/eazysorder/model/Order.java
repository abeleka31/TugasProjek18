package eazysorder.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty foodId = new SimpleIntegerProperty();
    private final StringProperty customerName = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final StringProperty foodName = new SimpleStringProperty();
    public Order(){

    }
    public Order(int foodId, String customerName, int quantity){
        this.foodId.set(foodId);
        this.customerName.set(customerName);
        this.quantity.set(quantity);
    }

  // id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // foodId
    public int getFoodId() {
        return foodId.get();
    }

    public void setFoodId(int foodId) {
        this.foodId.set(foodId);
    }

    public IntegerProperty foodIdProperty() {
        return foodId;
    }

    // customerName
    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    // quantity
    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    // foodName
    public String getFoodName() {
        return foodName.get();
    }

    public void setFoodName(String foodName) {
        this.foodName.set(foodName);
    }

    public StringProperty foodNameProperty() {
        return foodName;
    }
}


