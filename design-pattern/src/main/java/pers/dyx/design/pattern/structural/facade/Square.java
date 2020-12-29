package pers.dyx.design.pattern.structural.facade;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Square::draw()");
	}
}
