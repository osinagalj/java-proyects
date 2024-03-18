package movierental;

import movierental.model.price.Price;
import movierental.model.price.PriceType;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;
public class RentalTest {

    public static final String CHILDREN_MOVIE = "Bambi";

    private Movie childrenMovie;

    private Rental rental;
    @Before
    public void before() {
        childrenMovie = new Movie(CHILDREN_MOVIE, PriceType.CHILDREN);
        rental = new Rental(childrenMovie, 1);
    }

    @Test
    public void testAmountFor() {
        double expected = 1.5;
        assertEquals(expected, rental.amountFor());
    }

    @Test
    public void testPointsFor() {
        int expected = 1;
        assertEquals(rental.pointsFor(), expected);
    }
    @Test
    public void testGetMovie() {
        assertEquals("Bambi", rental.getMovie().getTitle());
    }
    @Test
    public void testGetDaysRented() {
        assertEquals(1, rental.getDaysRented());
    }

}