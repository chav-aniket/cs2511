import java.io.File;

public class FlightManagement {
    private List<Flight> flights;

    public FlightManagement() {
        flights = new ArrayList<Flight>();
    }

    // Returns boolean to notify whether the booking has been successful or not
    private boolean bookFlight(Passenger passenger, String flightID) {
        return false;
    }

    // Returns boolean to notify whether the cancelation has been successful or not
    private boolean cancelFlight(Passenger passenger, String flightID) {
        return false;
    }

    // Returns boolean to notify whether the update has been successful or not
    private boolean updateFlight(Passenger passenger, String flightID, FlightClass flightClass) {
        return false;
    }
}