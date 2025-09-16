import java.util.*;

public class Customer {

    private String customerID;
    private String name;

    //constructor

    public Customer(String customerID, String name){
        this.customerID = customerID;
        this.name = name;
    }

    public String getCustomerID(){
        return customerID;
    }

    public String getname(){
        return name;
    }
}
