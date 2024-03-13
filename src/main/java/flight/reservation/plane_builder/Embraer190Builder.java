package flight.reservation.plane_builder;

import flight.reservation.plane.PassengerPlane;

public class Embraer190Builder implements PlaneInterface {

    PassengerPlane embraer190Plane = new PassengerPlane();

    public Embraer190Builder() {
        embraer190Plane.model = "Embraer 190";
    }

    @Override
    public void buildPassengerCapacity() {
        embraer190Plane.passengerCapacity = 100;
    }

    @Override
    public void buildCrewCapacity() {
        embraer190Plane.setCrewCapacity(4);
    }
    
    public PassengerPlane getPlane() {
        return embraer190Plane;
    }
}
