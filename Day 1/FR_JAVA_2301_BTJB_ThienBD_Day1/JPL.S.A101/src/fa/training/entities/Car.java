/**
 * 
 */
package fa.training.entities;

/**
 * @author ThienBD
 *
 */
public abstract class Car {
	int speed;
	double regularPrice = 3000;
	String color;
	

    // Constructor
    public Car(int speed, double regularPrice, String color) {
        this.speed = speed;
        this.regularPrice = regularPrice;
        this.color = color;
    }

    // Getter methods
    public int getSpeed() {
        return speed;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public String getColor() {
        return color;
    }

    // Setter methods
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void setColor(String color) {
        this.color = color;
    }
	
	abstract double getSalePrice();
}
