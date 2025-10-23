public class Main {
    public static void main(String[] args) {
        // Create the system and add some sample cars
        CarRentalSystem rentalSystem = new CarRentalSystem();
        Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "BE 6", 100.0);
        Car car4 = new Car("C004", "Ford", "Mustang", 180.0);
        Car car5 = new Car("C005", "Tata", "Safari", 150.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new CarRentalGUI(rentalSystem);
        });
    }
}
