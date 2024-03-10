package flight.reservation.plane;

public class Helicopter {
    private final String model;
    private final int passengerCapacity;

    public Helicopter(String model) {
        this.model = model;
        if (model.equals("H1")) {
            passengerCapacity = 4;
        } else if (model.equals("H2")) {
            passengerCapacity = 6;
        } else {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }

    public String getModel() {
        return model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }
}
