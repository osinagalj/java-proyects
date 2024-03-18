package movierental.model.price;

public enum PriceType {
    REGULAR(0), NEW_RELEASE(1), CHILDREN(2);

    private final int value;

    PriceType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
