package pers.dyx.abstractfactory.factory;

import pers.dyx.abstractfactory.product.Green;
import pers.dyx.abstractfactory.product.Red;
import pers.dyx.abstractfactory.product.Shape;
import pers.dyx.abstractfactory.product.Blue;
import pers.dyx.abstractfactory.product.Color;

public class ColorFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shapeType) {
		return null;
	}

	@Override
	public Color getColor(String color) {
		if (color == null) {
			return null;
		}
		if ("RED".equalsIgnoreCase(color)) {
			return new Red();
		} else if ("GREEN".equalsIgnoreCase(color)) {
			return new Green();
		} else if ("BLUE".equalsIgnoreCase(color)) {
			return new Blue();
		}
		return null;
	}
}
