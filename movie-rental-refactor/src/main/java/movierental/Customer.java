package movierental;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }


    /**
     * Calculates the total charge for all rentals for the Customer.
     * @return the sum of all charges
     */
    public double totalCharge() {
        return rentals.stream()
                .mapToDouble(Rental::amountFor)
                .sum();
    }

    /**
     * Calculates the total points for all rentals for the Customer.
     * @return the sum of all points
     */
    public int totalPoints() {
        return rentals.stream()
                .mapToInt(Rental::pointsFor)
                .sum();
    }
}
