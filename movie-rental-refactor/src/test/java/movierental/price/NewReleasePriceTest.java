package movierental.price;

import movierental.Customer;
import movierental.Movie;
import movierental.Rental;
import movierental.model.price.PriceType;
import movierental.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NewReleasePriceTest {

    public static final String CUSTOMER_NAME = "Martin";
    public static final String NEW_RELEASE_MOVIE_NAME = "Trois Couleurs";
    private Customer customer;
    private Movie newRealeseMovie;

    @Before
    public void before() {
        newRealeseMovie = new Movie(NEW_RELEASE_MOVIE_NAME, PriceType.NEW_RELEASE);
    }

    private String statementTemplate(String movieName, double totalAmount, int expectedPoints) {
        return "Rental Record for " + CUSTOMER_NAME + "\n" +
                "\t" + movieName + "\t" + totalAmount + "\n" +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + expectedPoints + " frequent rental points";
    }


    @Test
    public void statement_for_new_release_movie_and_1_day_test() {
        customer = new Customer(CUSTOMER_NAME);
        Rental rental = new Rental(newRealeseMovie, 1);
        customer.addRental(rental);

        double expectedTotalAmount = 3.0;
        int expectedPoints = 1;
        assertEquals(CustomerService.getInstance().statement(customer), statementTemplate(NEW_RELEASE_MOVIE_NAME, expectedTotalAmount, expectedPoints));
    }

    @Test
    public void statement_for_new_release_movie_and_3_days_test() {
        customer = new Customer(CUSTOMER_NAME);
        Rental rental = new Rental(newRealeseMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 9.0;
        int expectedPoints = 2;
        assertEquals(CustomerService.getInstance().statement(customer), statementTemplate(NEW_RELEASE_MOVIE_NAME, expectedTotalAmount, expectedPoints));
    }


}