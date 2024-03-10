package flight.reservation.order;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderManager {
    private Map<String, Set<OrderListener>> listeners = new HashMap<>();

    void subscribe(String eventType, OrderListener listener) {
        listeners.computeIfAbsent(eventType, k -> new HashSet<>()).add(listener);
    }

    void unsubscribe(String eventType, OrderListener listener) {
        listeners.getOrDefault(eventType, new HashSet<>()).remove(listener);
    }

    void notify(String eventType, Order order) {
        listeners.getOrDefault(eventType, new HashSet<>()).forEach(listener -> listener.update(order));
    }
}
