package flight.reservation.payment;

public class PaypalStrategy implements PaymentInterface{
    private String email;
    private String password;

    public PaypalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean processOrder(double amount) {
        // validate payment information
        if (this.email == null || this.password == null || !this.email.equals(Paypal.DATA_BASE.get(this.password))) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = pay(amount);
        return isPaid;
    }

    @Override
    public boolean pay(double amount) {
        if (this.email.equals(Paypal.DATA_BASE.get(this.password))) {
            System.out.println("Paying " + amount + " using PayPal.");
            return true;
        } else {
            return false;
        }
    }
}
