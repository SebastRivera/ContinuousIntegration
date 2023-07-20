
public class Meal {
  private int id;
  private String name;
  private float price;
  private int quantity;
  private boolean chefSpecial;
  private boolean availability;

  public Meal(int id, String name, float price, int quantity, boolean chefSpecial, boolean availability){
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.chefSpecial = chefSpecial;
    this.availability = availability;
  }

  public Meal(int id, String name, int quantity, boolean chefSpecial, boolean availability){
    this.id = id;
    this.name = name;
    this.price = 5;
    this.quantity = quantity;
    this.chefSpecial = chefSpecial;
    this.availability = availability;
  }

  public boolean getAvailability() {
    return availability;
  }

  public void setAvailability(boolean availability) {
    this.availability = availability;
  }

  public boolean isChefSpecial() {
    return chefSpecial;
  }

  public void setChefSpecial(boolean chefSpecial) {
    this.chefSpecial = chefSpecial;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public boolean validateQuantity(int quantity) {
    if (quantity <= 0) {
      System.out.println("The quantity should be more than zero");
      return false;
    }
    return true;
  }

  public void checkSpecialMeals() {
    if (this.chefSpecial) {
      this.price *= 1.05;
    }
  }

  public boolean checkAvailability() {
    if (!this.availability) {
      System.out.println("The selected meal is currently unavailable");
      return false;
    }
    return true;
  }
}
