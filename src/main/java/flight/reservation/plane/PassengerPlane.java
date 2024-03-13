package flight.reservation.plane;

public class PassengerPlane implements Aircraft{

    public String model;
    public int passengerCapacity;
    private int crewCapacity;

    public PassengerPlane() {
    }

    public PassengerPlane(String model) {
        this.model = model;
        switch (model) {
            case "A380":
                passengerCapacity = 500;
                crewCapacity = 42;
                break;
            case "A350":
                passengerCapacity = 320;
                crewCapacity = 40;
                break;
            case "Embraer 190":
                passengerCapacity = 25;
                crewCapacity = 5;
                break;
            case "Antonov AN2":
                passengerCapacity = 15;
                crewCapacity = 3;
                break;
            default:
                throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }

    public void setCrewCapacity(int crewCapacity) {
        this.crewCapacity = crewCapacity;
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
