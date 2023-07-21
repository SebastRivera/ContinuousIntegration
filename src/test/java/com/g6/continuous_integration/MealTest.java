package com.g6.continuous_integration;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MealTest {
    private Meal meal;

    @Before
    public void setUp() {
        meal = new Meal(1, "Burger", 5.0f, 2, true, true);
    }

    @Test
    public void testGetters() {
        assertEquals(1, meal.getId());
        assertEquals("Burger", meal.getName());
        assertEquals(5.0f, meal.getPrice(), 0.001f);
        assertEquals(2, meal.getQuantity());
        assertTrue(meal.isChefSpecial());
        assertTrue(meal.getAvailability());
    }

    @Test
    public void testSetters() {
        meal.setId(2);
        meal.setName("Pizza");
        meal.setPrice(10.0f);
        meal.setQuantity(3);
        meal.setChefSpecial(false);
        meal.setAvailability(false);

        assertEquals(2, meal.getId());
        assertEquals("Pizza", meal.getName());
        assertEquals(10.0f, meal.getPrice(), 0.001f);
        assertEquals(3, meal.getQuantity());
        assertFalse(meal.isChefSpecial());
        assertFalse(meal.getAvailability());
    }

    @Test
    public void testQuantityValidation() {
        assertTrue(meal.validateQuantity(2)); // Valid quantity
        assertFalse(meal.validateQuantity(0)); // Invalid quantity (<= 0)
        assertFalse(meal.validateQuantity(101)); // Invalid quantity (> 100)
    }

    @Test
    public void testSpecialMeals() {
        meal.checkSpecialMeals();
        assertEquals(5.0f * 1.05f, meal.getPrice(), 0.001f); // Price should be increased by 5%
    }

    @Test
    public void testAvailabilityCheck() {
        assertTrue(meal.checkAvailability());
        meal.setAvailability(false);
        assertFalse(meal.checkAvailability());
    }
}
