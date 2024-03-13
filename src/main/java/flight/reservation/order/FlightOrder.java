package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.payment.PaymentInterface;

import java.util.Arrays;
import java.util.List;

public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;
    static List<String> noFlyList = Arrays.asList("Peter", "Johannes");
    private PaymentInterface paymentInterface;

    public FlightOrder(List<ScheduledFlight> flights) {
        this.flights = flights;
        setPaymentInterface(null);
    }

    public void setPaymentInterface(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    public static List<String> getNoFlyList() {
        return noFlyList;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    // Updated method using the chain of responsibility pattern
    private boolean isOrderValid(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
        // Initialize chain
        OrderCheckHandler chain = setupValidationChain();
        
        return chain.check(customer, passengerNames, flights);
    }

    private OrderCheckHandler setupValidationChain() {
        OrderCheckHandler noFlyListCustomerCheck = new NoFlyListCustomerCheck();
        OrderCheckHandler noFlyListPassengerCheck = new NoFlyListPassengerCheck();
        OrderCheckHandler flightCapacityCheck = new FlightCapacityCheck();

        noFlyListCustomerCheck.setNextHandler(noFlyListPassengerCheck);
        noFlyListPassengerCheck.setNextHandler(flightCapacityCheck);

        return noFlyListCustomerCheck;
    }

    public boolean processOrder() {
        if (isClosed()) {
            return true;
        }
        
        boolean isPaid = paymentInterface.processOrder(this.getPrice());
        if (isPaid) {
            this.setClosed();
        }
        return isPaid;
    }

    // Abstract Handler
    abstract class OrderCheckHandler {
        protected OrderCheckHandler nextHandler;

        public void setNextHandler(OrderCheckHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        public abstract boolean check(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights);
    }

    // Concrete Handlers
    class NoFlyListCustomerCheck extends OrderCheckHandler {
        @Override
        public boolean check(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
            if (noFlyList.contains(customer.getName())) {
                return false;
            }
            return nextHandler == null || nextHandler.check(customer, passengerNames, flights);
        }
    }

    class NoFlyListPassengerCheck extends OrderCheckHandler {
        @Override
        public boolean check(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
            if (passengerNames.stream().anyMatch(passenger -> noFlyList.contains(passenger))) {
                return false;
            }
            return nextHandler == null || nextHandler.check(customer, passengerNames, flights);
        }
    }

    class FlightCapacityCheck extends OrderCheckHandler {
        @Override
        public boolean check(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
            for (ScheduledFlight scheduledFlight : flights) {
                try {
                    if (scheduledFlight.getAvailableCapacity() < passengerNames.size()) {
                        return false;
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return nextHandler == null || nextHandler.check(customer, passengerNames, flights);
        }
    }
}

