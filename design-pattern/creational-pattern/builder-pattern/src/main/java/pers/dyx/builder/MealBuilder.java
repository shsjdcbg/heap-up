package pers.dyx.builder;

import pers.dyx.builder.product.ChickenBurger;
import pers.dyx.builder.product.Coke;
import pers.dyx.builder.product.Pepsi;
import pers.dyx.builder.product.VegBurger;

public class MealBuilder {

	public Meal prepareVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new VegBurger());
		meal.addItem(new Coke());
		return meal;
	}

	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new ChickenBurger());
		meal.addItem(new Pepsi());
		return meal;
	}
}
