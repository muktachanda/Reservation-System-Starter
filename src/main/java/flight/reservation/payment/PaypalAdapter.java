package flight.reservation.payment;
import org.json.JSONObject;

public class PaypalAdapter implements PaymentMethod{
    private Paypal paypal;

    public PaypalAdapter(Paypal paypal) {
        this.paypal = paypal;
    }

    @Override
    public double getAmount() {
        JSONObject paypalResponse =  paypal.getAmount();
        double amount = paypalResponse.getDouble("amount");
        return amount;
    }
}
