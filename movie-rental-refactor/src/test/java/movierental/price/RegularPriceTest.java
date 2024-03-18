package movierental.price;

import movierental.Customer;
import movierental.Movie;
import movierental.Rental;
import movierental.model.price.PriceType;
import movierental.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RegularPriceTest {

    public static final String CUSTOMER_NAME = "Martin";
    public static final String REGULAR_MOVIE_NAME = "Ran";
    private Movie regularMovie;
    private Customer customer;

    @Before
    public void before() {
        regularMovie = new Movie(REGULAR_MOVIE_NAME, PriceType.REGULAR);
    }

    @Test
    public void statement_for_regular_movie_and_2_days_test() {
        customer = new Customer(CUSTOMER_NAME);
        Rental rental = new Rental(regularMovie, 2);
        customer.addRental(rental);

        double expectedTotalAmount = 2.0;
        int expectedPoints = 1;
        assertEquals(CustomerService.getInstance().statement(customer), statementTemplate(REGULAR_MOVIE_NAME, expectedTotalAmount, expectedPoints));
    }

    @Test
    public void statement_for_regular_movie_and_3_days_test() {
        customer = new Customer(CUSTOMER_NAME);
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 3.5;
        int expectedPoints = 1;
        assertEquals(CustomerService.getInstance().statement(customer), statementTemplate(REGULAR_MOVIE_NAME, expectedTotalAmount, expectedPoints));
    }

    private String statementTemplate(String movieName, double totalAmount, int expectedPoints) {
        return "Rental Record for " + CUSTOMER_NAME + "\n" +
                "\t" + movieName + "\t" + totalAmount + "\n" +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + expectedPoints + " frequent rental points";
    }
}