package flight.reservation.plane_factory;
import flight.reservation.plane.PassengerPlane;
import flight.reservation.plane_builder.A350Builder;
import flight.reservation.plane_builder.A380Builder;
import flight.reservation.plane_builder.AntonovAN2Builder;
import flight.reservation.plane_builder.Embraer190Builder;
import flight.reservation.plane_builder.PlaneDirector;
import flight.reservation.plane.Aircraft;

public class PassengerPlaneFactory extends AircraftFactory {
    public Aircraft createAircraft(String model) {
        return createPassengerPlane(model);
    }

    // create a380
    public static PassengerPlane createA380(PlaneDirector director) {
        A380Builder builder = new A380Builder();
        director.setPlaneType(builder);
        director.constructPlane();
        PassengerPlane a380 = builder.getPlane();
        return a380;
    }

    // create a350
    public static PassengerPlane createA350(PlaneDirector director) {
        A350Builder builder = new A350Builder();
        director.setPlaneType(builder);
        director.constructPlane();
        PassengerPlane a350 = builder.getPlane();
        return a350;
    }

    // create embraer 190
    public static PassengerPlane createEmbraer190(PlaneDirector director) {
        Embraer190Builder builder = new Embraer190Builder();
        director.setPlaneType(builder);
        director.constructPlane();
        PassengerPlane embraer190 = builder.getPlane();
        return embraer190;
    }


    // create antonov an2
    public static PassengerPlane createAntonovAN2(PlaneDirector director) {
        AntonovAN2Builder builder = new AntonovAN2Builder();
        director.setPlaneType(builder);
        director.constructPlane();
        PassengerPlane antonovAN2 = builder.getPlane();
        return antonovAN2;
    }


    public static PassengerPlane createPassengerPlane(String model) {
        switch (model) {
                case "A380":
                        return createA380(new PlaneDirector());
                case "A350":
                        return createA350(new PlaneDirector());
                case "Embraer 190":
                        return createEmbraer190(new PlaneDirector());
                case "Antonov AN2":        
                        return createAntonovAN2(new PlaneDirector());

                default:
                        return createAntonovAN2(new PlaneDirector());
        }
    }
}
