package flight.reservation.payment;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class Paypal {
    public static final Map<String, String> DATA_BASE = new HashMap<>();

    static {
        DATA_BASE.put("amanda1985", "amanda@ya.com");
        DATA_BASE.put("qwerty", "john@amazon.eu");
    }

    public JSONObject getAmount() {
        // assuming this function interacts with a paypal api which returns a json object
        JSONObject paypalResponse = new JSONObject();
        paypalResponse.put("amount", 100);
        paypalResponse.put("state", "approved");

        return paypalResponse;
    }
}
