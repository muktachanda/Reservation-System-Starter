package flight.reservation.payment;

public interface PaymentInterface {
    public boolean processOrder(double amount);
    public boolean pay(double amount);
}
