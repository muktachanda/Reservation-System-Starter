package flight.reservation.plane_factory;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.Aircraft;

public class PassengerDroneFactory implements AircraftFactory {
    public Aircraft createAircraft(String model) {
        return new PassengerDrone(model);
    }
}
