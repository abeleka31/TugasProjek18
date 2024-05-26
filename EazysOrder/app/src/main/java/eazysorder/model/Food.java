package eazysorder.model;

import javafx.beans.property.*;

public class Food {
  private final IntegerProperty id;
  private final StringProperty name;
  private final DoubleProperty price;

  public Food(String name, double price) {
    this.id = new SimpleIntegerProperty();
    this.name = new SimpleStringProperty(name);
    this.price = new SimpleDoubleProperty(price);
  }

  public int getId() {
    return id.get();
  }

  public void setId(int id) {
    this.id.set(id);
  }

  public IntegerProperty idProperty() {
    return id;
  }

  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public double getPrice() {
    return price.get();
  }

  public void setPrice(double price) {
    this.price.set(price);
  }

  public DoubleProperty priceProperty() {
    return price;
  }
}
