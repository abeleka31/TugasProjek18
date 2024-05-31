package eazysorder.model;

public class Food extends AbstractFood {

    public Food(int id, String name, double price, String imagePath) {
        super(id, name, price, imagePath);
    }

    // Override methods if specific functionality needs to be added or modified

    @Override
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        super.setQuantity(quantity);
    }
}
