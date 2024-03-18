package movierental.model.price;

public abstract class Price {

    protected static final int STANDARD_POINTS = 1;

    public static Price of(PriceType priceCodeType) {
        return switch (priceCodeType) {
            case REGULAR -> new RegularPrice();
            case NEW_RELEASE -> new NewReleasePrice();
            case CHILDREN -> new ChildrenPrice();
        };
    }

    public abstract double amountFor(int daysRented);

    /**
     * Calculates the number of frequent rental points a customer earns for renting a movie for a specific number of days.
     * @param daysRented the name of the secret
     * @return the secret's arn
     */
    public int points(int daysRented) {
        return STANDARD_POINTS;
    }
}