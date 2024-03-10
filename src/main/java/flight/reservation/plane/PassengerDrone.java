package flight.reservation.plane;

public class PassengerDrone implements Aircraft{
    private String model = "HypaHype";
    private final int crewCapacity = 0;
    private final int passengerCapacity = 4;

    public PassengerDrone(String model) {
        if (model.equals("HypaHype")) {
            this.model = model;
        } else {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }

    public String getModel() {
        return model;
    }

    public int getCrewCapacity() {
        return crewCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }
}
