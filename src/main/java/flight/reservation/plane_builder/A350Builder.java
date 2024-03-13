package flight.reservation.plane_builder;

import flight.reservation.plane.PassengerPlane;

public class A350Builder implements PlaneInterface {

    PassengerPlane a350Plane = new PassengerPlane();

    public A350Builder() {
        a350Plane.model = "A350";
    }

    @Override
    public void buildPassengerCapacity() {
        a350Plane.passengerCapacity = 320;
    }

    @Override
    public void buildCrewCapacity() {
        a350Plane.setCrewCapacity(40);
    }

    public PassengerPlane getPlane() {
        return a350Plane;
    }
    
}
