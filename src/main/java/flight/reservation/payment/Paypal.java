package flight.reservation.payment;

import java.util.HashMap;
import java.util.Map;

public class Paypal {
    public static final Map<String, String> DATA_BASE = new HashMap<>();

    static {
        DATA_BASE.put("amanda1985", "amanda@ya.com");
        DATA_BASE.put("qwerty", "john@amazon.eu");
    }
}
