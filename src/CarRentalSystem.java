import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rentals> rentals;
    public CarRentalSystem(){
        this.cars = new ArrayList<Car>();
        this.customers = new ArrayList<Customer>();
        this.rentals = new ArrayList<Rentals>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int days){
        if(car.isAvailable()) {
             car.rent();
             rentals.add(new Rentals(car, customer, days));
        } else{
            System.out.println("Car is Not available for rent");
        }
    }
    public void returnCar(Car car){
        Rentals rentalToRemove = null;
        for(Rentals rental: rentals){
            if(rental.getCar()== car){
                rentalToRemove = rental;
            }
        }
        if(rentalToRemove!=null){
            rentals.remove(rentalToRemove);
            car.returnCar();
        }
        else{
            System.out.println("The car ID entered is wrong");
        }
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("\n== Rent a Car ===");
                    System.out.println("Enter your name: ");
                    String customerName = scanner.nextLine();
                    System.out.println("\n Available cars: ");
                    for(Car car: cars){
                        if(car.isAvailable()){
                            System.out.println(car.getBrand() + car.getModel() + " ID is "+ car.getCarId());
                        }
                    }
                    System.out.println("\nEnter the car ID you want to rent: ");
                    String carId = scanner.nextLine();

                    System.out.println("Enter the number of days for rental");
                    int rentalDays = scanner.nextInt();
                    scanner.nextLine();
                    Customer newCustomer = new Customer("CUS"+(customers.size()+1), customerName);
                    addCustomer(newCustomer);
                    Car selectedCar = null;
                    for(Car car: cars){
                        if(Objects.equals(car.getCarId(), carId)) {
                            selectedCar = car;
                        }
                    }
                    if(selectedCar!=null){
                        double totalPrice = selectedCar.calculatePrice(rentalDays);
                        System.out.println("\n== Rental Information ==\n");
                        System.out.println("Customer ID: " + newCustomer.getId());
                        System.out.println("Customer Name: " + newCustomer.getName());
                        System.out.println("Car: "+ selectedCar.getBrand() + " " + selectedCar.getModel());
                        System.out.println("Rental Days: " + rentalDays);
                        System.out.printf("Total Price: $%.2f%n", totalPrice);
                        System.out.println("\nConfirm rental (Y/N): ");
                        String confirm = scanner.nextLine();
                        if(confirm.equalsIgnoreCase("Y")){
                            rentCar(selectedCar, newCustomer, rentalDays);
                            System.out.println("\n Car rented Successfully \n");
                        }else{
                            System.out.println("\n Rental Cancelled");
                        }
                    }
                    else {
                        System.out.println("Enter a valid Car ID");
                    }
                    break;
                case 2:
                    System.out.println("\n Return a Car==\n");
                    System.out.println("Enter the Car ID rented: ");
                    String carId2 = scanner.nextLine();
                    Car carToReturn = null;
                    for(Car car: cars){
                        if(car.getCarId().equals(carId2) && !car.isAvailable()){
                            carToReturn = car;
                            break;
                        }
                    }
                    if(carToReturn!=null){
                        Customer customer = null;
                        for(Rentals rental: rentals){
                            if(rental.getCar() == carToReturn){
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if(customer!=null){
                            returnCar(carToReturn);
                            System.out.println("Car returned successfully by :"+ customer.getName());
                        }
                        else{
                            System.out.println("Wrong rental information");
                        }
                    }
                    else{
                        System.out.println("Please enter a Valid Car ID");
                    }
                    break;
                case 3: flag = false;
                default:
                    System.out.println("Please enter a valid option 1-3");
            }
        }
        System.out.println("Thank you for using our Car rental system");
    }
}
