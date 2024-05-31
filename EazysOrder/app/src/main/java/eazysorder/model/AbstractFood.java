package eazysorder.model;

import javafx.beans.property.*;

public abstract class AbstractFood {
    protected IntegerProperty id;
    protected StringProperty name;
    protected DoubleProperty price;
    protected StringProperty imagePath;
    protected IntegerProperty quantity;

    public AbstractFood(int id, String name, double price, String imagePath) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.imagePath = new SimpleStringProperty(imagePath);
        this.quantity = new SimpleIntegerProperty(0); // Menginisialisasi quantity dengan 0
    }

    // Getters and setters for id
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    // Getters and setters for name
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Getters and setters for price
    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    // Getters and setters for imagePath
    public String getImagePath() {
        return imagePath.get();
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }

    // Getters and setters for quantity
    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
}
