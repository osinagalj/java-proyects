package movierental.model.price;

public class NewReleasePrice extends Price {
    static final int BASE_CHARGE = 3;
    static final int MINIMUM_DAYS = 1;

    @Override
    public double amountFor(int daysRented) {
        return daysRented * BASE_CHARGE;
    }

    /**
     * For NewReleasePrice priced movies, double the amount of points earned if rented for 2 days or more.
     * @param daysRented Number of days the movie has been rented.
     * @return If it has been rented for 2 or more days, 2 points are returned, otherwise the standard amount is returned.
     */
    @Override
    public int points(int daysRented) {
        return (daysRented > MINIMUM_DAYS) ? (STANDARD_POINTS + 1) : STANDARD_POINTS;
    }
}

