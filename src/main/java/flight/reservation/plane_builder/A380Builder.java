package flight.reservation.plane_builder;

import flight.reservation.plane.PassengerPlane;

public class A380Builder implements PlaneInterface {

    PassengerPlane a380Plane = new PassengerPlane();

    public A380Builder() {
        a380Plane.model = "A380";
    }

    @Override
    public void buildPassengerCapacity() {
        a380Plane.passengerCapacity = 500;
    }

    @Override
    public void buildCrewCapacity() {
        a380Plane.setCrewCapacity(42);
    }
    
    public PassengerPlane getPlane() {
        return a380Plane;
    }
    
}
