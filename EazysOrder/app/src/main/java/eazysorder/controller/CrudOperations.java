package eazysorder.controller;

import eazysorder.model.Food;

import java.util.List;

public interface CrudOperations {
    Food addFood(String name, double price, String imagePath);

    Food getFoodById(int id);

    List<Food> getAllFood();

    boolean updateFood(Food food);

    boolean deleteFood(Food food);
}
