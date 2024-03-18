package movierental.service;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import movierental.Customer;
import movierental.Movie;
import movierental.Rental;
import movierental.model.price.PriceType;
import org.junit.Test;

public class CustomerServiceTest {

    public static final String CUSTOMER_NAME = "Martin";
    public static final String REGULAR_MOVIE_NAME = "Ran";
    public static final String NEW_RELEASE_MOVIE_NAME = "Trois Couleurs";

    private Customer customer;

    @Test
    public void originalTest() {
        Customer customer = new Customer("Bob");
        customer.addRental(new Rental(new Movie("Jaws", PriceType.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Golden Eye", PriceType.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Short New", PriceType.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Long New", PriceType.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Bambi", PriceType.CHILDREN), 3));
        customer.addRental(new Rental(new Movie("Toy Story", PriceType.CHILDREN), 4));


        String expected = "" +
                "Rental Record for Bob\n" +
                "\tJaws\t2.0\n" +
                "\tGolden Eye\t3.5\n" +
                "\tShort New\t3.0\n" +
                "\tLong New\t6.0\n" +
                "\tBambi\t1.5\n" +
                "\tToy Story\t3.0\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent rental points";

        assertEquals(expected, CustomerService.getInstance().statement(customer));
    }

    @Test
    public void statement_from_example_from_readme_test() {
        customer = new Customer(CUSTOMER_NAME);
        customer.addRental(new Rental(new Movie("Ran", PriceType.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Trois Couleurs", PriceType.REGULAR), 1));

        final String EXPECTED_RESULT = "Rental Record for Martin\n\t" +
                REGULAR_MOVIE_NAME + "\t3.5\n" +
                "\t" + NEW_RELEASE_MOVIE_NAME +"\t2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent rental points";

        assertEquals(EXPECTED_RESULT, CustomerService.getInstance().statement(customer));
    }

}