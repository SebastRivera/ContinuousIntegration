public class Main {

    public static void main(String[] args) {
        Meal bolon = new Meal(1, "Bolon", 5.00f, false, true); 
        Meal tigrillo = new Meal(2, "Tigrillo", 5.00f, false, true); //special
        Meal churrasco = new Meal(3, "Churrasco", 8.00f, false, false); //unavailable
        Meal parrillada = new Meal(4, "Parrillada Premium", 30.00f, true, true); //special
        Meal chicken = new Meal(5, "Chicken Parm", 18.50f, true, true); //special
        System.out.println("Dining Experience Manager");
        
    }
}
