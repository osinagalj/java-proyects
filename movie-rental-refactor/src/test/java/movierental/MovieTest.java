package movierental;

import movierental.model.price.Price;
import movierental.model.price.PriceType;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;
public class MovieTest {

    public static final String CHILDREN_MOVIE = "Bambi";

    private Movie childrenMovie;

    @Before
    public void before() {
        childrenMovie = new Movie(CHILDREN_MOVIE, PriceType.CHILDREN);
    }

    @Test
    public void testMovie() {
        try {
            var movie = new Movie(null, null);
            fail("The method was expected to throw an exception.");
        } catch (Exception e) {
            assertEquals("Cannot invoke \"movierental.model.price.PriceType.ordinal()\" because \"priceCodeType\" is null", e.getMessage());
        }

    }

    @Test
    public void testAmountFor() {
        double expected = 1.5;
        assertEquals(expected, childrenMovie.amountFor(1));
    }

    @Test
    public void testPointsFor() {
        int expected = 1;
        assertEquals(childrenMovie.pointsFor(1), expected);
    }

    @Test
    public void testGetTitle() {
        assertEquals(childrenMovie.getTitle(), "Bambi");
    }

    @Test
    public void testGetPrice() {
        assertEquals(childrenMovie.getPrice().getClass(), Price.of(PriceType.CHILDREN).getClass());
    }

}