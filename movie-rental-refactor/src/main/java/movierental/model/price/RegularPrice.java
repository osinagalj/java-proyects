package movierental.model.price;

public class RegularPrice extends Price {
    static final double BASE_CHARGE = 2;
    static final int MINIMUM_DAYS = 2;
    @Override
    public double amountFor(int daysRented) {
        return (daysRented > MINIMUM_DAYS) ? BASE_CHARGE + (daysRented - MINIMUM_DAYS) * 1.5 : BASE_CHARGE;
    }
}
