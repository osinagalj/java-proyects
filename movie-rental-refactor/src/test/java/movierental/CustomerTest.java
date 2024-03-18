package movierental;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import movierental.model.price.PriceType;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    public static final String CUSTOMER_NAME = "Martin";
    public static final String REGULAR_MOVIE_NAME = "Ran";
    public static final String NEW_RELEASE_MOVIE_NAME = "Trois Couleurs";
    public static final String CHILDREN_MOVIE = "Bambi";

    private Customer customer;

    @Before
    public void before() {
        customer = new Customer(CUSTOMER_NAME);
        customer.addRental(new Rental(new Movie(REGULAR_MOVIE_NAME, PriceType.REGULAR), 1));
        customer.addRental(new Rental(new Movie(NEW_RELEASE_MOVIE_NAME, PriceType.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie(CHILDREN_MOVIE, PriceType.CHILDREN), 1));
    }

    @Test
    public void testTotalCharge() {
        double expectedCharge = 9.5;
        assertEquals(expectedCharge,  customer.totalCharge());
    }
    @Test
    public void testTotalPoints() {
        int expectedPoints = 4;
        assertEquals(expectedPoints,  customer.totalPoints());
    }


}

