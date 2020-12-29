package pers.dyx.design.pattern.creational.abstractfactory;

public class ShapeFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if ("CIRCLE".equalsIgnoreCase(shapeType)) {
			return new Circle();
		} else if ("RECTANGLE".equalsIgnoreCase(shapeType)) {
			return new Rectangle();
		} else if ("SQUARE".equalsIgnoreCase(shapeType)) {
			return new Square();
		}
		return null;
	}

	@Override
	public Color getColor(String color) {
		return null;
	}
}
