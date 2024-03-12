import flight.reservation.Airport;
import flight.reservation.flight.Schedule;
import flight.reservation.flight.Flight;
// import flight.reservation.plane.Helicopter;
// import flight.reservation.plane.PassengerDrone;
// import flight.reservation.plane.PassengerPlane;
import flight.reservation.plane_factory.AircraftFactory;
import flight.reservation.plane.Aircraft;
import flight.reservation.plane_factory.HelicopterFactory;
import flight.reservation.plane_factory.PassengerDroneFactory;
import flight.reservation.plane_factory.PassengerPlaneFactory;

import java.util.Arrays;
import java.util.List;

public class Runner {
    static List<Airport> airports = Arrays.asList(
            new Airport("Berlin Airport", "BER", "Berlin, Berlin"),
            new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse"),
            new Airport("Madrid Barajas Airport", "MAD", "Barajas, Madrid"),
            new Airport("Guarulhos International Airport", "GRU", "Guarulhos (São Paulo)"),
            new Airport("John F. Kennedy International Airport", "JFK", "Queens, New York, New York"),
            new Airport("Istanbul Airport", "IST", "Arnavutköy, Istanbul"),
            new Airport("Dubai International Airport", "DXB", "Garhoud, Dubai"),
            new Airport("Chengdu Shuangliu International Airport", "CTU", "Shuangliu-Wuhou, Chengdu, Sichuan")
    );

    private AircraftFactory passengerPlaneFactory = new PassengerPlaneFactory();
    private AircraftFactory helicopterFactory = new HelicopterFactory();
    private AircraftFactory passengerDroneFactory = new PassengerDroneFactory();


    Aircraft a = passengerPlaneFactory.createAircraft("A380");
    Aircraft b = passengerPlaneFactory.createAircraft("A350");
    Aircraft c = passengerPlaneFactory.createAircraft("Embraer 190");
    Aircraft d = passengerPlaneFactory.createAircraft("Antonov AN2");
    Aircraft e = helicopterFactory.createAircraft("H1");
    Aircraft f = passengerDroneFactory.createAircraft("HypaHype");

    List<Aircraft> aircrafts = Arrays.asList(a, b, c, d, e, f);




    //     static List<Object> aircrafts = Arrays.asList(
    //             new PassengerPlane("A380"),
    //             new PassengerPlane("A350"),
    //             new PassengerPlane("Embraer 190"),
    //             new PassengerPlane("Antonov AN2"),
    //             new Helicopter("H1"),
    //             new PassengerDrone("HypaHype")
    //     );

    List<Flight> flights = Arrays.asList(
                new Flight(1, airports.get(0), airports.get(1), aircrafts.get(0)),
                new Flight(2, airports.get(1), airports.get(2), aircrafts.get(1)),
                new Flight(3, airports.get(2), airports.get(4), aircrafts.get(2)),
                new Flight(4, airports.get(3), airports.get(2), aircrafts.get(3)),
                new Flight(5, airports.get(4), airports.get(2), aircrafts.get(4)),
                new Flight(6, airports.get(5), airports.get(7), aircrafts.get(5))
        );

    //     static List<Flight> flights = Arrays.asList(
    //             new Flight(1, airports.get(0), airports.get(1), aircrafts.get(0)),
    //             new Flight(2, airports.get(1), airports.get(2), aircrafts.get(1)),
    //             new Flight(3, airports.get(2), airports.get(4), aircrafts.get(2)),
    //             new Flight(4, airports.get(3), airports.get(2), aircrafts.get(3)),
    //             new Flight(5, airports.get(4), airports.get(2), aircrafts.get(4)),
    //             new Flight(6, airports.get(5), airports.get(7), aircrafts.get(5))
    //     );

    static Schedule schedule;

    public static void main(String[] args) {
    }
}
