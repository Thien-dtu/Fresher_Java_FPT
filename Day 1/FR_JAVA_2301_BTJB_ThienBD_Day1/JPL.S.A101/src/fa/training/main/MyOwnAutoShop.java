package fa.training.main;

import fa.training.entities.Ford;
import fa.training.entities.Sedan;
import fa.training.entities.Truck;

public class MyOwnAutoShop {

	public static void main(String[] args) {
		// Creating instances of Sedan class
        Sedan sedan1 = new Sedan(200, 50000, "Red", 25);
        Sedan sedan2 = new Sedan(250, 60000, "Blue", 19);
        
        // Creating instances of Ford class
        Ford ford1 = new Ford(230, 75000, "White", 2020, 15000);
        Ford ford2 = new Ford(400, 80000, "Black", 2022, 10000);
        
        // Creating instances of Truck class
        Truck truck1 = new Truck(300, 50000, "Silver", 3000);
        Truck truck2 = new Truck(400, 60000, "Gold", 1000);
        
        // Printing the sale price of all the vehicles
        System.out.println("Sedan1 sale price: " + sedan1.getSalePrice());
        System.out.println("Sedan2 sale price: " + sedan2.getSalePrice());
        System.out.println("Ford1 sale price: " + ford1.getSalePrice());
        System.out.println("Ford2 sale price: " + ford2.getSalePrice());
        System.out.println("Truck1 sale price: " + truck1.getSalePrice());
        System.out.println("Truck2 sale price: " + truck2.getSalePrice());
	}

}
