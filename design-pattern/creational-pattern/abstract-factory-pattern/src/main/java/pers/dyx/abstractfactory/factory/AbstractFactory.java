package pers.dyx.abstractfactory.factory;

import pers.dyx.abstractfactory.product.Color;
import pers.dyx.abstractfactory.product.Shape;

public abstract class AbstractFactory {
	public abstract Color getColor(String color);

	public abstract Shape getShape(String shape);
}
