package pers.dyx.design.pattern.structural.facade;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Circle::draw()");
	}
}
