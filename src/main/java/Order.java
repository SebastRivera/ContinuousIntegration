import java.util.ArrayList;
import java.util.Scanner; 

public class Order {
  private ArrayList<Meal> meals;
  private int quantity;
  private float totalCost;

  public Order(){
    this.meals = new ArrayList<Meal>();
    this.quantity = 0;
    this.totalCost = 0;
  }
  
  public ArrayList<Meal> getMeals() {
    return meals;
  }

  public void setMeals(ArrayList<Meal> meals) {
    this.meals = meals;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public float getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(float totalCost) {
    this.totalCost = totalCost;
  }

  public void applyQuantityDiscount() {
    if (this.quantity > 5 && this.quantity <= 10) {
      this.totalCost *= 0.9;
      System.out.println("10% DISCOUNT APPLIED FOR QUANTITY MORE THAN 5");
    } else if (this.quantity > 10) {
      this.totalCost *= 0.8;
      System.out.println("20% DISCOUNT APPLIED FOR QUANTITY MORE THAN 10");
    }
  }

  public void applySpecialOfferDiscount() {
    if (this.totalCost > 50 && this.totalCost <= 100) {
      this.totalCost -= 10;
      System.out.println("$10 DISCOUNT APPLIED FOR TOTAL COST MORE THAN $50");
    } else if (this.totalCost > 100) {
      this.totalCost -= 25;
      System.out.println("$25 DISCOUNT APPLIED FOR TOTAL COST MORE THAN $100");
    }
  }

  public boolean checkMaxQuantity() {
    if (this.quantity > 100) {
      System.out.println("**CAN NOT ORDER MORE THAN 100 MEALS");
      return false;
    }
    return true;
  }

  public void displayOrder() {
    System.out.println("Name      Quantity    Cost");
    for (Meal m : this.meals) {
      System.out.println(m.getName() + "    " + m.getQuantity() + "    " + "$" + m.getPrice() * m.getQuantity());
    }
    System.out.println("TOTAL COST: $" + this.totalCost);
  }

  public void addMeal(Meal meal){
    this.meals.add(meal);
    this.totalCost+=(meal.getPrice()*meal.getQuantity());
    this.quantity+=meal.getQuantity();
  }

  public void displayFinalOrder() {
    System.out.println("FINAL ORDER:");
    displayOrder();
  }
}

