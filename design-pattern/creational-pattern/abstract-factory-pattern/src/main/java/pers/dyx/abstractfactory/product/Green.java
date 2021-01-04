package pers.dyx.abstractfactory.product;

import pers.dyx.abstractfactory.product.Color;

public class Green implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}
}
