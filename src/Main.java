
public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("C001", "Toyota", "Camry", 50);
        Car car2 = new Car("C002", "Hyundai", "Verna", 60);
        Car car3 = new Car("C003", "Mahindra", "Thar", 70);
        CarRentalSystem rentalSystem = new CarRentalSystem();
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.menu();
    }
}