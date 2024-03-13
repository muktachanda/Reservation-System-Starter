package flight.reservation.plane_builder;

import flight.reservation.plane.PassengerPlane;

public class AntonovAN2Builder implements PlaneInterface {

    PassengerPlane an2Plane = new PassengerPlane();

    @Override
    public void buildPassengerCapacity() {
        an2Plane.passengerCapacity = 12;
    }

    @Override
    public void buildCrewCapacity() {
        an2Plane.setCrewCapacity(2);
    }
    
    public PassengerPlane getPlane() {
        return an2Plane;
    }
}
