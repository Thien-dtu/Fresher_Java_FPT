package fa.training.entities;

public class Sedan extends Car {
	
private int length;
    
    public Sedan(int speed, double regularPrice, String color, int length) {
        super(speed, regularPrice, color);
        this.length = length;
    }
    
    @Override
    public double getSalePrice() {
        double discount = 0.0;
        if (length > 20) {
            discount = 0.05;
        } else {
            discount = 0.1;
        }
        return regularPrice - (regularPrice * discount);
    }
}