package movierental.service;


import movierental.Customer;
import movierental.Rental;

import java.util.ArrayList;

public class CustomerService {

    private static CustomerService instance;

    /**
     * Private constructor to avoid direct instantiation
     */
    private CustomerService() {}

    /**
     * Static method to get the Singleton instance
     */
    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    /**
     * Generates a string with all the points earned for each movie for the customer
     * and also calculates the total charge and frequent rental points earned by a customer.
     * @return Return a string with all the rental information of a Customer
     */
    public String statement(Customer customer) {
        StringBuilder result = new StringBuilder("");
        result.append("Rental Record for ").append(customer.getName()).append("\n");
        for (Rental rental : customer.getRentals()) {
            result
                    .append("\t").append(rental.getMovie().getTitle())
                    .append("\t").append(rental.amountFor())
                    .append("\n");
        }
        result.append("Amount owed is ").append(customer.totalCharge()).append("\n")
                .append("You earned ").append(customer.totalPoints()).append(" frequent rental points");
        return result.toString();
    }

}
