package com.g6.continuous_integration;
import java.util.ArrayList; 

public class Menu {
    private ArrayList<Meal> meals;

    public Menu(ArrayList<Meal> meals){
      this.meals = meals;
    }
    
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public void displayMenu(){
      System.out.println("Name - Price");
      for (Meal m : this.meals) {
        System.out.printf("%d. %s - %.2f\n", m.getId(), m.getName(), m.getPrice());
        
      }
    }
}
