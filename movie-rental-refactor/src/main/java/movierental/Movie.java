package movierental;

import lombok.Getter;
import lombok.Setter;
import movierental.model.price.Price;
import movierental.model.price.PriceType;

@Getter
public class Movie {
    private String title;
    private Price price;

    public Movie(String title, PriceType priceType) {
        this.title = title;
        this.price = Price.of(priceType);
    }

    public double amountFor(int daysRented) {
        return price.amountFor(daysRented);
    }

    public int pointsFor(int daysRented) {
        return price.points(daysRented);
    }
}
