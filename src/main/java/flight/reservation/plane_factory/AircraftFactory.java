package flight.reservation.plane_factory;
import flight.reservation.plane.Aircraft;

public abstract class AircraftFactory {
    public abstract Aircraft createAircraft(String model);
}
