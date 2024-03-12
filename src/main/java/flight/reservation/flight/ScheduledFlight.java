package flight.reservation.flight;

import flight.reservation.Airport;
import flight.reservation.Passenger;
// import flight.reservation.plane.Helicopter;
// import flight.reservation.plane.PassengerDrone;
// import flight.reservation.plane.PassengerPlane;
import flight.reservation.plane.Aircraft;
// import flight.reservation.plane_factory.AircraftFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduledFlight extends Flight {

    private final List<Passenger> passengers;
    private final Date departureTime;
    private double currentPrice = 100;

    public ScheduledFlight(int number, Airport departure, Airport arrival, Aircraft aircraft, Date departureTime) {
        super(number, departure, arrival, aircraft);
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
    }

    public ScheduledFlight(int number, Airport departure, Airport arrival, Aircraft aircraft, Date departureTime, double currentPrice) {
        super(number, departure, arrival, aircraft);
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
        this.currentPrice = currentPrice;
    }

    public int getCrewMemberCapacity() throws NoSuchFieldException {
        // if (this.aircraft instanceof PassengerPlane) {
        //     return ((PassengerPlane) this.aircraft).crewCapacity;
        // }
        // if (this.aircraft instanceof Helicopter) {
        //     return 2;
        // }
        // if (this.aircraft instanceof PassengerDrone) {
        //     return 0;
        // }
        // throw new NoSuchFieldException("this aircraft has no information about its crew capacity");

        return (this.aircraft).getCrewCapacity();


    }

    public void addPassengers(List<Passenger> passengers) {
        this.passengers.addAll(passengers);
    }

    public void removePassengers(List<Passenger> passengers) {
        this.passengers.removeAll(passengers);
    }

    public int getCapacity() throws NoSuchFieldException {
        // if (this.aircraft instanceof PassengerPlane) {
        //     return ((PassengerPlane) this.aircraft).passengerCapacity;
        // }
        // if (this.aircraft instanceof Helicopter) {
        //     return ((Helicopter) this.aircraft).getPassengerCapacity();
        // }
        // if (this.aircraft instanceof PassengerDrone) {
        //     return 4;
        // }
        // throw new NoSuchFieldException("this aircraft has no information about its capacity");

        return (this.aircraft).getPassengerCapacity();


    }

    public int getAvailableCapacity() throws NoSuchFieldException {
        return this.getCapacity() - this.passengers.size();
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
