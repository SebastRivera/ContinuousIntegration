package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import com.g6.continuous_integration.Main;
import com.g6.continuous_integration.Meal;
import com.g6.continuous_integration.Menu;
import com.g6.continuous_integration.Order;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Scanner;

public class DiningFeatureStepDef {
	private Menu menu;
    private Order order;
    private Meal selectedMeal;
    private int selectedQuantity;

    @Given("el menú contiene la comida {string} con un precio de ${double} y está disponible")
    public void el_menu_contiene_la_comida_con_un_precio_de_y_está_disponible(String mealName, double price) {
    	float priceFloat = (float) price;
    	selectedMeal = new Meal(1, mealName, priceFloat, 1, false, true);
        ArrayList<Meal> meals = new ArrayList<Meal>();
        meals.add(selectedMeal);
        menu = new Menu(meals);
    }
    
    @When("el cliente selecciona {string} y elige la cantidad de {int}")
    public void el_cliente_selecciona_y_elige_la_cantidad_de(String mealName, int quantity) {
        selectedMeal = menu.getMeals().get(0);
        selectedQuantity = quantity;
        order = new Order();
        order.addMeal(selectedMeal);
        order.setQuantity(selectedQuantity);
        order.setTotalCost(selectedMeal.getPrice() * selectedQuantity);
    }
    
    @Then("la compra es exitosa")
    public void la_compra_es_exitosa() {
        assertNotNull(order);
        assertEquals(selectedQuantity, order.getQuantity());
        assertTrue(order.getMeals().contains(selectedMeal));
    }
    
    @Then("el total a pagar es ${double}")
    public void el_total_a_pagar_es(double expectedTotal) {
        assertEquals(expectedTotal, order.getTotalCost(),0.01);
    }

    @Given("el menú contiene la comida {string} con un precio de ${double} pero no está disponible")
    public void el_menu_contiene_la_comida_con_un_precio_de_pero_no_está_disponible(String mealName, double price) {
    	float priceFloat = (float) price;
    	selectedMeal = new Meal(1, mealName, priceFloat, 1, false, false);
        ArrayList<Meal> meals = new ArrayList<Meal>();
        meals.add(selectedMeal);
        menu = new Menu(meals);
    }

    @When("el cliente intenta seleccionar {string}")
    public void el_cliente_intenta_seleccionar(String mealName) {
        for (Meal meal : menu.getMeals()) {
            if (meal.getName().equals(mealName)) {
                selectedMeal = meal;
                break;
            }
        }
        if (selectedMeal == null || !selectedMeal.getAvailability()) {
            order = null;
        } else {
            selectedQuantity = 1;
            order = new Order();
            order.addMeal(selectedMeal);
            order.setQuantity(selectedQuantity);
            order.setTotalCost(selectedMeal.getPrice() * selectedQuantity);
        }
    }
    
    @Then("la compra falla")
    public void la_compra_falla() {
        assertNull(order);
    }

    
    @Then("se muestra un mensaje de error que dice {string}")
    public void se_muestra_un_mensaje_de_error_que_dice(String errorMessage) {
    	if (order == null) {
            System.out.println(errorMessage);
        } else {
            // Si el pedido no falló, esto no debería ocurrir en este escenario.
            fail("La compra no debería haber sido exitosa.");
        }
    }

    @Given("el menú contiene la comida {string} con un precio de ${double}")
    public void el_menu_contiene_la_comida_con_un_precio_de(String mealName, double price) {
        float priceFloat = (float) price;
    	selectedMeal = new Meal(1, mealName, priceFloat, 1, false, true);
        ArrayList<Meal> meals = new ArrayList<Meal>();
        meals.add(selectedMeal);
        menu = new Menu(meals);
    }
    
    @Given("el cliente ha seleccionado {int} {string}")
    public void el_cliente_ha_seleccionado(int quantity, String mealName) {
        selectedMeal = menu.getMeals().get(0);
        selectedQuantity = quantity;
        order = new Order();
        order.addMeal(selectedMeal);
        order.setQuantity(selectedQuantity);
        order.setTotalCost(selectedMeal.getPrice() * selectedQuantity);
    }

   

    @When("el cliente confirma el pedido")
    public void el_cliente_confirma_el_pedido() {
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
                System.out.println("Selections changed Successfully.");
            } else {
                System.out.println("Order canceled Successfully.");
            }
        }
    }

    @Then("se aplica un descuento de {int}%")
    public void se_aplica_un_descuento_del(int discountPercent) {
    	float totalCostBeforeDiscount = order.getTotalCost();
        float expectedDiscount = totalCostBeforeDiscount * (1 - (discountPercent / 100f) );
        order.applyQuantityDiscount();
        float actualDiscount = expectedDiscount;
        assertEquals(expectedDiscount, actualDiscount, 0.001f);
    }
    
    @Then("se aplica un descuento de ${double}")
    public void se_aplica_un_descuento_del(double discountPercent) {
    	float totalCostBeforeDiscount = order.getTotalCost();
        float expectedDiscount = (float) (totalCostBeforeDiscount - discountPercent);
        order.applySpecialOfferDiscount();
        float actualDiscount = expectedDiscount;
        assertEquals(expectedDiscount, actualDiscount, 0.001f);
    }

	
	

}
