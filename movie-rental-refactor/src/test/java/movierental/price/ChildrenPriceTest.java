package movierental.price;

import movierental.Customer;
import movierental.Movie;
import movierental.Rental;
import movierental.model.price.PriceType;
import movierental.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ChildrenPriceTest {

    public static final String CUSTOMER_NAME = "Martin";
    public static final String CHILDREN_MOVIE = "Bambi";
    private Customer customer;
    private Movie childrenMovie;

    @Before
    public void before() {
        childrenMovie = new Movie(CHILDREN_MOVIE, PriceType.CHILDREN);
    }

    private String statementTemplate(String movieName, double totalAmount, int expectedPoints) {
        return "Rental Record for " + CUSTOMER_NAME + "\n" +
                "\t" + movieName + "\t" + totalAmount + "\n" +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + expectedPoints + " frequent rental points";
    }

    @Test
    public void statement_for_children_movie_and_3_days_test() {
        customer = new Customer(CUSTOMER_NAME);
        Rental rental = new Rental(childrenMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 1.5;
        int expectedPoints = 1;
        assertEquals(CustomerService.getInstance().statement(customer), statementTemplate(CHILDREN_MOVIE, expectedTotalAmount, expectedPoints));
    }

    @Test
    public void statement_for_children_movie_and_4_days_test() {
        customer = new Customer(CUSTOMER_NAME);
        Rental rental = new Rental(childrenMovie, 4);
        customer.addRental(rental);

        double expectedTotalAmount = 3.0;
        int expectedPoints = 1;
        assertEquals(CustomerService.getInstance().statement(customer), statementTemplate(CHILDREN_MOVIE, expectedTotalAmount, expectedPoints));
    }

}