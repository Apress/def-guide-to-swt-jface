package examples.ch14;

import java.util.*;

/**
 * This class contains all the foods on the "grocery list"
 */
public class GroceryList {
  // Holds the foods
  private List foods;

  /**
   * Constructs a grocery list
   */
  public GroceryList() {
    foods = new ArrayList();

    // Add some foods
    foods.add(new Food("Broccoli", true));
    foods.add(new Food("Bundt Cake", false));
    foods.add(new Food("Cabbage", true));
    foods.add(new Food("Candy Canes", false));
    foods.add(new Food("Eggs", true));
    foods.add(new Food("Potato Chips", false));
    foods.add(new Food("Milk", true));
    foods.add(new Food("Soda", false));
    foods.add(new Food("Chicken", true));
    foods.add(new Food("Cinnamon Rolls", false));
  }

  /**
   * Returns the foods in this grocery list
   * 
   * @return List
   */
  public List getFoods() {
    return Collections.unmodifiableList(foods);
  }
}