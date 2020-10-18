package part3Q1;

import java.time.LocalDateTime;

public class Flight {
    // Flight name e.g. QF1
    private String flightID;

    // Flight arrival and departure
    private LocalDateTime departure;
    private LocalDateTime arrival;

    private List<Seat> seats;

    public Flight(String flightID, LocalDateTime departure, LocalDateTime arrival,
                    int firstClassSeatNum, int businessClassSeatNum, int economyClassSeatNum) {
        this.departure = departure;
        this.arrival = arrival;
        seats = seatGenerator(firstClassSeatNum, businessClassSeatNum, economyClassSeatNum);
    }

    // Generates a list of seats with unique seat IDs given the number required
    private List<Seat> seatGenerator(int firstClassSeatNum, businessClassSeatNum, economyClassSeatNum) {
        return null;
    }

    // Checks whether a certain passenger can change their seat and if possible changes
    public boolean changeSeat(String seatID, FlightClass from, FlightClass to) {
        return false;
    }

    // Finds a seat in a certain class
    public Seat findSeat(FlightClass seatClass) {
        return null;
    }

    // Adds a passenger to a certain seat
    public void addPassenger(Passenger passenger, String seatID) {
        return;
    }

    // Removes a passenger from a certain seat
    public void removePassenger(Passenger passenger, String seatID) {
        return;
    }
}

