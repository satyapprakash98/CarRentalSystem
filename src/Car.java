public class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;
    public Car(String id, String brand, String model, float basePricePerDay){
        this.basePricePerDay = basePricePerDay;
        this.carId = id;
        this.brand = brand;
        this.model = model;
        this.isAvailable = true;
    }
    public String getBrand() {
        return brand;
    }

    public String getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice (int rentalDays){
        return basePricePerDay*rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void rent(){
        isAvailable = false;
    }
    public void returnCar(){
        isAvailable = true;
    }

}
