package part3Q1;

public class Passenger {
    private String passengerID;
    private FlightManagement flightManager;
    private Map<Flight, Seat> schedule;

    public Passenger(FlightManagement manager) {
        this.flightManager = manager;
        this.schedule = new HashMap<Flight, Seat>();
    }
}

