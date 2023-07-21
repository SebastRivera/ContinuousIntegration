package com.g6.continuous_integration;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class OrderTest {
    private Order order;

    @Before
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testAddMeal() {
        Meal meal1 = new Meal(1, "Burger", 5.0f, 2, true, true);
        Meal meal2 = new Meal(2, "Pizza", 10.0f, 3, false, true);

        order.addMeal(meal1);
        order.addMeal(meal2);

        ArrayList<Meal> meals = order.getMeals();
        assertEquals(2, meals.size());

        Meal addedMeal1 = meals.get(0);
        assertEquals(1, addedMeal1.getId());
        assertEquals("Burger", addedMeal1.getName());
        assertEquals(5.0f, addedMeal1.getPrice(), 0.001f);
        assertEquals(2, addedMeal1.getQuantity());
        assertTrue(addedMeal1.isChefSpecial());
        assertTrue(addedMeal1.getAvailability());

        Meal addedMeal2 = meals.get(1);
        assertEquals(2, addedMeal2.getId());
        assertEquals("Pizza", addedMeal2.getName());
        assertEquals(10.0f, addedMeal2.getPrice(), 0.001f);
        assertEquals(3, addedMeal2.getQuantity());
        assertFalse(addedMeal2.isChefSpecial());
        assertTrue(addedMeal2.getAvailability());

        assertEquals(2 * 5.0f + 3 * 10.0f, order.getTotalCost(), 0.001f);
        assertEquals(2 + 3, order.getQuantity());
    }

    @Test
    public void testApplyQuantityDiscount() {
        // No discount for quantity less than or equal to 5
        order.setQuantity(3);
        order.setTotalCost(50.0f);
        order.applyQuantityDiscount();
        assertEquals(50.0f, order.getTotalCost(), 0.001f);

        // 10% discount for quantity between 6 and 10 (inclusive)
        order.setQuantity(7);
        order.setTotalCost(70.0f);
        order.applyQuantityDiscount();
        assertEquals(70.0f * 0.9f, order.getTotalCost(), 0.001f);

        // 20% discount for quantity greater than 10
        order.setQuantity(15);
        order.setTotalCost(200.0f);
        order.applyQuantityDiscount();
        assertEquals(200.0f * 0.8f, order.getTotalCost(), 0.001f);
    }

    @Test
    public void testApplySpecialOfferDiscount() {
        // No discount for total cost less than or equal to $50
        order.setTotalCost(50.0f);
        order.applySpecialOfferDiscount();
        assertEquals(50.0f, order.getTotalCost(), 0.001f);

        // $10 discount for total cost between $51 and $100 (inclusive)
        order.setTotalCost(75.0f);
        order.applySpecialOfferDiscount();
        assertEquals(75.0f - 10.0f, order.getTotalCost(), 0.001f);

        // $25 discount for total cost greater than $100
        order.setTotalCost(150.0f);
        order.applySpecialOfferDiscount();
        assertEquals(150.0f - 25.0f, order.getTotalCost(), 0.001f);
    }

    @Test
    public void testCheckMaxQuantity() {
        // Quantity less than or equal to 100, should return true
        order.setQuantity(50);
        assertTrue(order.checkMaxQuantity());

        // Quantity greater than 100, should return false
        order.setQuantity(150);
        assertFalse(order.checkMaxQuantity());
    }
}