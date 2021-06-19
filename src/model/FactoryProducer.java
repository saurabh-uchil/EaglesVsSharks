package model;

public class FactoryProducer {
	public static AbstractFactory getFactory(boolean isEagle) {
		if (isEagle) {
			return new EagleFactory();
		} else {
			return new SharkFactory();
		}
	}
}
