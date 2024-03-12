package flight.reservation.payment;

import java.util.Date;

public class CreditCardStrategy implements PaymentInterface {
    private CreditCard card;

    public CreditCardStrategy(String name, Date expirationDate, String cvv) {
        this.card = new CreditCard(name, expirationDate, cvv);
    }

    public CreditCardStrategy(CreditCard card) {
        this.card = card;
    }

    @Override 
    public boolean processOrder(double amount) throws IllegalStateException {
        // validate payment information
        if (!cardIsPresentAndValid()) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = pay(amount);
        return isPaid;
    }

    private boolean cardIsPresentAndValid() {
        return this.card != null && this.card.isValid();
    }

    @Override 
    public boolean pay(double amount) throws IllegalStateException {
        if (cardIsPresentAndValid()) {
            System.out.println("Paying " + amount + " using Credit Card.");
            double remainingAmount = this.card.getAmount() - amount;
            if (remainingAmount < 0) {
                System.out.printf("Card limit reached - Balance: %f%n", remainingAmount);
                throw new IllegalStateException("Card limit reached");
            }
            this.card.setAmount(remainingAmount);
            return true;
        } else {
            return false;
        }
    }
    
}
