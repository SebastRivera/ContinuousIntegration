import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Meal bolon = new Meal(1, "Bolon", 5.00f, false, true);
    Meal tigrillo = new Meal(2, "Tigrillo", 5.00f, false, true); // special
    Meal churrasco = new Meal(3, "Churrasco", 8.00f, false, false); // unavailable
    Meal parrillada = new Meal(4, "Parrillada Premium", 30.00f, true, true); // special
    Meal chicken = new Meal(5, "Chicken Parm", 18.50f, true, true); // special
    ArrayList<Meal> meals = new ArrayList<Meal>();
    meals.add(bolon);
    meals.add(tigrillo);
    meals.add(churrasco);
    meals.add(parrillada);
    meals.add(chicken);

    System.out.println("Dining Experience Manager");
    Menu menu = new Menu(meals);
    menu.displayMenu();
    boolean ask = true;
    Order order = new Order();
    Scanner sc = new Scanner(System.in);
    String confirmation = "Y";

    do {
      Meal meal = Main.askForMeal(menu);
      int quantity = Main.askForQuantity(meal);
      meal.setQuantity(quantity);
      meal.checkSpecialMeals();
      order.addMeal(meal);

      if (!order.checkMaxQuantity()) {
        Main.reEnterQuantities(order);
      }

      System.out.println("Would you like to add another meal? Y/N");
      confirmation = sc.nextLine();

      int orderValue = 0;

      while (!confirmation.equals("Y")) {
        orderValue = Main.confirmOrder(order);
        if (orderValue != 1) {
          break;
        }
        System.out.println("Would you like to add another meal? Y/N");
        confirmation = sc.nextLine();
      }
    } while (confirmation.equals("Y"));
  }

  public static int confirmOrder(Order order) {
    System.out.println("Your order summary:");
    order.displayOrder();

    System.out.println("Do you want to confirm the order? Y/N");
    Scanner sc = new Scanner(System.in);
    String confirmation = sc.nextLine();

    if (confirmation.equals("Y")) {
      order.applyQuantityDiscount();
      order.applySpecialOfferDiscount();
      order.displayFinalOrder();
    } else {
      System.out.println("Order canceled. Do you want to make changes to your selections? Y/N");
      confirmation = sc.nextLine();
      if (confirmation.equals("Y")) {
        Main.reEnterQuantities(order);
        System.out.println("Selections changed Succesfully.");
        return 1;
      } else {
        System.out.println("Order canceled Succesfully.");
        return -1;
      }
    }

    return 0;
  }

  public static Meal askForMeal(Menu menu) {
    System.out.print("Enter the number of the Meal to be added: ");
    Scanner sc = new Scanner(System.in);
    int idMeal = sc.nextInt();
    Meal meal;
    boolean av = true;
    while (idMeal > menu.getMeals().size() || idMeal <= 0 || !av) {
      System.out.println("**Invalid number of Meal");
      System.out.print("Enter the number of the Meal to be added: ");
      idMeal = sc.nextInt();
      if (idMeal > 0 && idMeal < menu.getMeals().size()) {
        meal = menu.getMeals().get(idMeal - 1);
        av = meal.checkAvailability();
      }
    }
    meal = menu.getMeals().get(idMeal - 1);
    av = meal.checkAvailability();
    if (!av) {
      meal = askForMeal(menu);
    }
    return meal;
  }

  public static int askForQuantity(Meal meal) {
    System.out.print("How many would you like? (number only): ");
    Scanner sc = new Scanner(System.in);
    int quantity = sc.nextInt();
    while (!meal.validateQuantity(quantity)) {
      System.out.print("How many would you like? (number only): ");
      quantity = sc.nextInt();
    }
    return quantity;
  }

  public static void reEnterQuantities(Order order) {
    for (Meal m : order.getMeals()) {
      System.out.print("Re enter quantity for " + m.getName());
      int quantity = askForQuantity(m);
      m.setQuantity(quantity);
    }
  }
}
