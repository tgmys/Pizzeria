/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pizza;

/**
 *
 * @author Tomasz
 */

import static com.example.Pizza.Ingredient.*;
import java.util.Arrays;
import java.util.List;


public enum Pizza {
MARGHERITA("Marrgherita", THIN_CRUST, TOMATO_SUACE, CHEESE),
CAPRI("Capri", THIN_CRUST, TOMATO_SUACE, CHEESE, HAM,
MUSHROOMS),
HAVAI("Havai", THIN_CRUST, TOMATO_SUACE, CHEESE, HAM,
PINEAPPLE),
CARUSO("Caruso", THIN_CRUST, TOMATO_SUACE, SUASAGE,
PEPERONI),
MAMA_MIA("Mama Mia", THIN_CRUST, TOMATO_SUACE, CHEESE, ONION,
MUSHROOMS, BECON),
SOPRANO("Soprano", THICK_CRUST, TOMATO_SUACE, CHEESE, HAM,
MUSHROOMS, ONION, BECON, PEPPER),
CALABRESE("Calabrese", THICK_CRUST, TOMATO_SUACE, CHEESE,
HAM, MUSHROOMS, SUASAGE, ONION, OLIVES),
VEGETARIANA("Vegetariana", THIN_CRUST, TOMATO_SUACE, CHEESE,
ONION, BEAN, CORN, BROCCOLI, ARUGULA),
CAPRESE("Caprese", THICK_CRUST, TOMATO_SUACE, MOZARELLA,
FETA, TOMATO, BASIL),
PASCETORE("Pascetore", THIN_CRUST, TOMATO_SUACE, CHEESE,
TUNA, ONION),
FOUR_CHEESE("Cztery sery", THIN_CRUST, TOMATO_SUACE, CHEESE,
MOZARELLA, FETA, BLUE_CHEESE),
TABASCO("Tabasco", THICK_CRUST, TOMATO_SUACE, CHEESE, HAM,
SALAMI, PEPERONI, CORN, TABASCO_SUACE),
AMORE("Amore", THIN_CRUST, TOMATO_SUACE, CHEESE, CHICKEN,
TOMATO),
FARMER("Farmerska", THICK_CRUST, TOMATO_SUACE, CHEESE,
CHICKEN, BECON, ONION, CORN);
private final String name;
private final List<Ingredient> ingredients;
private Pizza(String name, Ingredient... ingredients) {
this.name = name;
this.ingredients = Arrays.asList(ingredients);
}
public String getName() {
return name;
}
public List<Ingredient> getIngredients() {
return ingredients;
}
@Override
public String toString() {
return name;
}
}


