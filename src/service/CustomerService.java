package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static final CustomerService INSTANCE = new CustomerService();
    private static final Map<String, Customer> customers = new HashMap<>();

    public static CustomerService getInstance() {
        return INSTANCE;
    }

    public void addCustomer(String email, String firstName, String lastName){
        customers.put(email, new Customer(firstName, lastName, email));
    };
    public Customer getCustomer(String email){
        return customers.get(email);
    };
    public Collection<Customer> getAllCustomers(){

        return customers.values();
    }
}
