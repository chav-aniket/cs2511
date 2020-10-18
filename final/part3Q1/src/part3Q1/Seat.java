package part3Q1;

public class Seat {
    private String seatID;
    private FlightClass seatClass;
    private Passenger passenger;

    // Each seat holds the passenger it will accomodate

    public Seat(String seatID, FlightClass seatClass) {
        this.seatID = seatID;
        this.seatClass = seatClass;
        this.passenger = null;
    }

    // Checks whether a passenger has chosen this seat already
    public boolean isAssigned() {
        return false;
    }

    // Assigns passenger to seat
    public void AssignPassenger(Passenger passenger) {
        return;
    }
}