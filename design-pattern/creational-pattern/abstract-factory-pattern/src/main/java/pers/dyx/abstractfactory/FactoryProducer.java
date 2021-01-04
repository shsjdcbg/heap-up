package pers.dyx.abstractfactory;

import pers.dyx.abstractfactory.factory.AbstractFactory;
import pers.dyx.abstractfactory.factory.ColorFactory;
import pers.dyx.abstractfactory.factory.ShapeFactory;

public class FactoryProducer {

    public static AbstractFactory getFactory(String choice) {
        if ("SHAPE".equalsIgnoreCase(choice)) {
            return new ShapeFactory();
        } else if ("COLOR".equalsIgnoreCase(choice)) {
            return new ColorFactory();
        }
        return null;
    }
}
