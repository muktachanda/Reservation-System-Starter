package flight.reservation.plane_factory;
import flight.reservation.plane.Aircraft;

public interface AircraftFactory {
    public Aircraft createAircraft(String model);
}
