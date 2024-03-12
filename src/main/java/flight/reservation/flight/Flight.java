package flight.reservation.flight;

import flight.reservation.Airport;
// import flight.reservation.plane.Helicopter;
// import flight.reservation.plane.PassengerDrone;
// import flight.reservation.plane.PassengerPlane;
import flight.reservation.plane.Aircraft;
// import flight.reservation.plane_factory.AircraftFactory;

import java.util.Arrays;

public class Flight {

    private int number;
    private Airport departure;
    private Airport arrival;
    protected Aircraft aircraft;
    // private AircraftFactory aircraftFactory;

    public Flight(int number, Airport departure, Airport arrival, Aircraft aircraft) throws IllegalArgumentException {
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        // this.aircraft = aircraftFactory.createAircraft(model);
        this.aircraft = aircraft;
        // this.aircraftFactory = aircraftFactory;
        checkValidity();
    }

    private void checkValidity() throws IllegalArgumentException {
        if (!isAircraftValid(departure) || !isAircraftValid(arrival)) {
            throw new IllegalArgumentException("Selected aircraft is not valid for the selected route.");
        }
    }

    private boolean isAircraftValid(Airport airport) {
        return Arrays.stream(airport.getAllowedAircrafts()).anyMatch(x -> {
            String model;
            // if (this.aircraft instanceof PassengerPlane) {
            //     model = ((PassengerPlane) this.aircraft).model;
            // } else if (this.aircraft instanceof Helicopter) {
            //     model = ((Helicopter) this.aircraft).getModel();
            // } else if (this.aircraft instanceof PassengerDrone) {
            //     model = "HypaHype";
            // } else {
            //     throw new IllegalArgumentException(String.format("Aircraft is not recognized"));
            // }
            model = (this.aircraft).getModel();
            return x.equals(model);
        });
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public int getNumber() {
        return number;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return aircraft.toString() + "-" + number + "-" + departure.getCode() + "/" + arrival.getCode();
    }

    // public AircraftFactory getAircraftFactory() {
    //     return aircraftFactory;
    // }

}
