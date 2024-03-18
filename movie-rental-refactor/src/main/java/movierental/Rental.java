package movierental;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The rental class represents a customer renting a movie.
 */
@Getter
@AllArgsConstructor
public class Rental {
    private Movie movie;
    private int daysRented;
    public double amountFor() {
        return movie.amountFor(this.daysRented);
    }

    public int pointsFor(){
        return movie.pointsFor(this.daysRented);
    }
}
