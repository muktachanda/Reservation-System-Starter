package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.payment.CreditCard;
import flight.reservation.payment.Paypal;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;
    static List<String> noFlyList = Arrays.asList("Peter", "Johannes");

    public FlightOrder(List<ScheduledFlight> flights) {
        this.flights = flights;
    }

    public static List<String> getNoFlyList() {
        return noFlyList;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    private boolean isOrderValid(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
        boolean valid = true;
        valid = valid && !noFlyList.contains(customer.getName());
        valid = valid && passengerNames.stream().noneMatch(passenger -> noFlyList.contains(passenger));
        valid = valid && flights.stream().allMatch(scheduledFlight -> {
            try {
                return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        });
        return valid;
    }

    public boolean processOrderWithCreditCardDetail(String number, Date expirationDate, String cvv) throws IllegalStateException {
        CreditCard creditCard = new CreditCard(number, expirationDate, cvv);
        return processOrderWithCreditCard(creditCard);
    }

    public boolean processOrderWithCreditCard(CreditCard creditCard) throws IllegalStateException {
        if (isClosed()) {
            // Payment is already proceeded
            return true;
        }
        // validate payment information
        if (!cardIsPresentAndValid(creditCard)) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = payWithCreditCard(creditCard, this.getPrice());
        if (isPaid) {
            this.setClosed();
        }
        return isPaid;
    }

    private boolean cardIsPresentAndValid(CreditCard card) {
        return card != null && card.isValid();
    }

    public boolean processOrderWithPayPal(String email, String password) throws IllegalStateException {
        if (isClosed()) {
            // Payment is already proceeded
            return true;
        }
        // validate payment information
        if (email == null || password == null || !email.equals(Paypal.DATA_BASE.get(password))) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = payWithPayPal(email, password, this.getPrice());
        if (isPaid) {
            this.setClosed();
        }
        return isPaid;
    }

    public boolean payWithCreditCard(CreditCard card, double amount) throws IllegalStateException {
        if (cardIsPresentAndValid(card)) {
            System.out.println("Paying " + getPrice() + " using Credit Card.");
            double remainingAmount = card.getAmount() - getPrice();
            if (remainingAmount < 0) {
                System.out.printf("Card limit reached - Balance: %f%n", remainingAmount);
                throw new IllegalStateException("Card limit reached");
            }
            card.setAmount(remainingAmount);
            return true;
        } else {
            return false;
        }
    }

    public boolean payWithPayPal(String email, String password, double amount) throws IllegalStateException {
        if (email.equals(Paypal.DATA_BASE.get(password))) {
            System.out.println("Paying " + getPrice() + " using PayPal.");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setClosed() {
        super.setClosed();
    }
}
