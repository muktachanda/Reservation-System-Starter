package flight_reservation;

import flight.reservation.flight.Flight;
import flight.reservation.flight.Schedule;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.order.FlightOrder;
import flight.reservation.payment.CreditCard;
import flight.reservation.payment.PaymentInterface;
import flight.reservation.payment.PaypalStrategy;
import flight.reservation.payment.CreditCardStrategy;
// import flight.reservation.plane.Helicopter;
import flight.reservation.plane.Aircraft;
import flight.reservation.plane_factory.AircraftFactory;
import flight.reservation.plane_factory.HelicopterFactory;
import flight.reservation.plane_factory.PassengerPlaneFactory;
import flight.reservation.Airport;
import flight.reservation.Customer;
import flight.reservation.Passenger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Scenario Tests")
public class ScenarioTest {


    private Schedule schedule;
    private Customer customer;
    private Airport startAirport;
    private Airport destinationAirport;
    private Flight flight;

    @BeforeEach
    public void initSchedule() {
        schedule = new Schedule();
        schedule.clear();
    }

    @Nested
    @DisplayName("Scenario 1: A customer books 2 passengers on a scheduled flight from John F. Kennedy International Airport to Berlin (flight 5) on a helicopter and pays it with pay pal")
    class CustomerTwoPassengersPaypalPayment {

        @BeforeEach
        public void init() {
            customer = new Customer("Max Mustermann", "amanda@ya.com");
        }

        @Nested
        @DisplayName("and Frankfurt cannot accompany Helicopters")
        class NoHelicoptersInFrankfurt {

            @BeforeEach
            public void initFlights() {
                startAirport = new Airport("John F. Kennedy International Airport", "JFK", "Queens, New York, New York");
                destinationAirport = new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse", new String[]{"A380", "A350"});
            }

            @Test
            @DisplayName("then the flight should not be available")
            void thenFlightNotAvailable() {
                AircraftFactory helicopterFactory = new HelicopterFactory();
                Aircraft a = helicopterFactory.createAircraft("H1");
                assertThrows(IllegalArgumentException.class, () -> new Flight(1, startAirport, destinationAirport, a));
            }

        }

        @Nested
        @DisplayName("and the flight is theoretically possible")
        class FlightIsPossible {

            @BeforeEach
            public void initFlights() {
                startAirport = new Airport("John F. Kennedy International Airport", "JFK", "Queens, New York, New York");
                destinationAirport = new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse");
                AircraftFactory helicopterFactory = new HelicopterFactory();
                Aircraft a = helicopterFactory.createAircraft("H1");
                flight = new Flight(1, startAirport, destinationAirport, a);
                Date departure = TestUtil.addDays(Date.from(Instant.now()), 3);
                schedule.scheduleFlight(flight, departure);
            }


            @Nested
            @DisplayName("and the H1 is fully booked")
            class H1FullyBooked {

                @BeforeEach
                public void initPassengers() {
                    ScheduledFlight scheduledFlight = schedule.searchScheduledFlight(flight.getNumber());
                    Passenger[] passengers = new Passenger[3];
                    for (int i = 0; i < passengers.length; i++) {
                        passengers[i] = new Passenger("P" + i);
                    }
                    scheduledFlight.addPassengers(Arrays.asList(passengers));
                }

                @Test
                @DisplayName("then the booking should be stopped and the payment should not proceed and the capacity should be unchanged")
                void thenTheBookingShouldBeStopped() throws NoSuchFieldException {
                    ScheduledFlight scheduledFlight = schedule.searchScheduledFlight(flight.getNumber());
                    assertThrows(IllegalStateException.class, () -> customer.createOrder(Arrays.asList("Amanda", "Max"), Arrays.asList(scheduledFlight), 180));
                    assertEquals(3, scheduledFlight.getPassengers().size());
                    assertEquals(4, scheduledFlight.getCapacity());
                    assertEquals(1, scheduledFlight.getAvailableCapacity());
                    assertTrue(scheduledFlight.getPassengers().stream().noneMatch(passenger -> passenger.getName().equals("Max")));
                    assertTrue(scheduledFlight.getPassengers().stream().noneMatch(passenger -> passenger.getName().equals("Amanda")));
                }
            }

            @Nested
            @DisplayName("and the H1 is empty")
            class H1Empty {
                @Test
                @DisplayName("then the booking should succeed")
                void thenTheBookingShouldSucceed() throws NoSuchFieldException {
                    ScheduledFlight scheduledFlight = schedule.searchScheduledFlight(flight.getNumber());
                    FlightOrder order = customer.createOrder(Arrays.asList("Amanda", "Max"), Arrays.asList(scheduledFlight), 180);
                    PaymentInterface paymentInterface = new PaypalStrategy(customer.getEmail(), "amanda1985");
                    order.setPaymentInterface(paymentInterface);

                    assertEquals(2, scheduledFlight.getPassengers().size());
                    assertEquals(4, scheduledFlight.getCapacity());
                    assertEquals(2, scheduledFlight.getAvailableCapacity());
                    assertTrue(scheduledFlight.getPassengers().stream().anyMatch(passenger -> passenger.getName().equals("Max")));
                    assertTrue(scheduledFlight.getPassengers().stream().anyMatch(passenger -> passenger.getName().equals("Amanda")));
                    assertFalse(order.isClosed());
                    assertEquals(order, customer.getOrders().get(0));


                    boolean isProcessed = order.processOrder();
                    assertTrue(isProcessed);
                    assertTrue(order.isClosed());
                }
            }
        }
    }

    @Nested
    @DisplayName("Scenario 2: A customer books 1 passenger on a a scheduled flight from Berlin to Frankfurt (flight 0) and pays it with credit card")
    class CustomerOnePassengerCreditCardPayment {
        private CreditCard creditCard;

        @BeforeEach
        public void init() {
            // flights
            startAirport = new Airport("Berlin Airport", "BER", "Berlin, Berlin");
            destinationAirport = new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse");
            AircraftFactory passengerPlaneFactory = new PassengerPlaneFactory();
            Aircraft a = passengerPlaneFactory.createAircraft("A380");
            flight = new Flight(1, startAirport, destinationAirport, a);
            Date departure = TestUtil.addDays(Date.from(Instant.now()), 3);
            schedule.scheduleFlight(flight, departure);
            // customer
            customer = new Customer("Max Mustermann", "amanda@ya.com");
            creditCard = Mockito.mock(CreditCard.class, Mockito.CALLS_REAL_METHODS);
        }

        @Nested
        @DisplayName("and credit card limit is reached")
        class CreditCardLimitReached {

            @BeforeEach
            public void initCreditCard() {
                Mockito.when(creditCard.isValid()).thenReturn(true);
                Mockito.when(creditCard.getAmount()).thenReturn(10.0);
            }

            @Test
            @DisplayName("then the payment should not succeed and the booking should not be closed/payed")
            void thenThePaymentAndBookingShouldNotSucceed() {
                ScheduledFlight scheduledFlight = schedule.searchScheduledFlight(flight.getNumber());
                FlightOrder order = customer.createOrder(Arrays.asList("Max"), Arrays.asList(scheduledFlight), 100);
                PaymentInterface paymentInterface = new CreditCardStrategy(creditCard);
                order.setPaymentInterface(paymentInterface);
                assertThrows(IllegalStateException.class, () -> order.processOrder());
                assertFalse(order.isClosed());
            }
        }

        @Nested
        @DisplayName("and credit card is not valid")
        class CreditCardNotValid {

            @BeforeEach
            public void initCreditCard() {
                Mockito.when(creditCard.isValid()).thenReturn(false);
            }

            @Test
            @DisplayName("then the booking should not be closed/payed")
            void thenTheBookingShouldNotSucceed() {
                ScheduledFlight scheduledFlight = schedule.searchScheduledFlight(flight.getNumber());
                FlightOrder order = customer.createOrder(Arrays.asList("Max"), Arrays.asList(scheduledFlight), 100);
                PaymentInterface paymentInterface = new CreditCardStrategy(creditCard);
                order.setPaymentInterface(paymentInterface);
                assertThrows(IllegalStateException.class, () -> order.processOrder());
                assertFalse(order.isClosed());
            }
        }

        @Nested
        @DisplayName("and credit card is valid")
        class CreditCardValid {

            @BeforeEach
            public void initCreditCard() {
                Mockito.when(creditCard.isValid()).thenReturn(true);
                creditCard.setAmount(1000.0);
            }

            @Test
            @DisplayName("then the booking should succeed")
            void thenTheBookingShouldSucceed() throws NoSuchFieldException {
                ScheduledFlight scheduledFlight = schedule.searchScheduledFlight(flight.getNumber());
                FlightOrder order = customer.createOrder(Arrays.asList("Max"), Arrays.asList(scheduledFlight), 100);
                PaymentInterface paymentInterface = new CreditCardStrategy(creditCard);
                order.setPaymentInterface(paymentInterface);
                boolean processed = order.processOrder();
                assertTrue(processed);
                assertTrue(order.isClosed());
                assertEquals(order, customer.getOrders().get(0));
                assertEquals(900, creditCard.getAmount());
                assertEquals(1, scheduledFlight.getPassengers().size());
                assertEquals("Max", scheduledFlight.getPassengers().get(0).getName());
                assertEquals(500, scheduledFlight.getCapacity());
                assertEquals(499, scheduledFlight.getAvailableCapacity());

            }
        }
    }


}
