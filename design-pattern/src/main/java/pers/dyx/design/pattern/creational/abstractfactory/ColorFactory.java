package pers.dyx.design.pattern.creational.abstractfactory;

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
