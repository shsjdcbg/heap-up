package pers.dyx.abstractfactory.product;

import pers.dyx.abstractfactory.product.Color;

public class Red implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Red::fill() method.");
	}
}
