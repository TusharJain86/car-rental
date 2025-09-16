import java.util.*;

public class Car {
    private String carID;

    private String brand;

    private String model;

    private double basePricePerDay;

    private boolean isOnline;

    //<-------------making a constructor---------->

    public Car(String carID, String brand, String model, double basePricePerDay){ //parameterised constructor
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isOnline = true;
        //these are the details of new car which entered our gargae
    }

    //<------------now making methods------------->

    public String getCarID(){
        return carID;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public double calculatePrice(int rentalDays){
        return basePricePerDay * rentalDays;
    }

    public boolean isOnline(){
        return isOnline;
    }

    public void rent(){
        isOnline = false;
    }

    public void returncar(){
        isOnline = true;
    }
}
