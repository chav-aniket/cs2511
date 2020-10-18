/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    private LinkedHashMap<String, Venue> venues;
    private LinkedHashMap<String, Reservation> reservations;

    public VenueHireSystem() {
        // TODO Auto-generated constructor stub
        venues = new LinkedHashMap<String, Venue>();
        reservations = new LinkedHashMap<String, Reservation>();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        // TODO Implement other commands
        case "change":
            id = json.getString("id");
            start = LocalDate.parse(json.getString("start"));
            end = LocalDate.parse(json.getString("end"));
            small = json.getInt("small");
            medium = json.getInt("medium");
            large = json.getInt("large");

            result = change(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;
        
        case "cancel":
            id = json.getString("id");
            cancelReservation(id);
            break;
        
        case "list":
            venue = json.getString("venue");
            JSONArray resArray = list(venue);
            System.out.println(resArray.toString(2));
            break;
        }
    }

    public Venue getVenue(String venue) {
        return venues.get(venue);
    }
    
    public Reservation getReservation(String id) {
        return reservations.get(id);
    }

    private void addRoom(String venue, String room, String size) {
        // TODO Process the room command
        if (getVenue(venue) == null) venues.put(venue, new Venue());
        getVenue(venue).addRoom(room, Room.stringToSize(size));
    }

    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        int sum = small + medium + large;
        JSONObject result = new JSONObject();

        // TODO Process the request commmand
        ArrayList<String> foundRooms = new ArrayList<String>();
        String status = "rejected";
        String foundVenue = "";
        for (String k: venues.keySet()) {
            Venue v = getVenue(k);
            foundRooms = v.findRooms(start, end, new int[] {small, medium, large});
            foundVenue = k;
            if (foundRooms.size() == sum) {
                status = "success";
                break;
            }
        }

        result.put("status", status);
        switch (status) {
            case "success":
                result.put("venue", foundVenue);
                
                Reservation newReservation = getVenue(foundVenue).addReservation(id, foundRooms, start, end);
                reservations.put(id, newReservation);

                JSONArray roomsArray = newReservation.getJSONRooms();
                result.put("rooms", roomsArray);
                break;
            case "rejected":
                break;
        }
        
        return result;
    }


    public JSONObject change(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();
        Reservation backup = getReservation(id);
        cancelReservation(id);

        JSONObject newReservation = request(id, start, end, small, medium, large);

        String status = newReservation.getString("status");

        switch (status) {
            case "success":
            result = newReservation;
            break;
            case "rejected":
            result.put("status", status);
            backup.roomAdd();
            reservations.put(id, backup);
            break;
        }
        return result;
    }
    
    public void cancelReservation(String id) {
        for (Reservation r: reservations.values()) {
            if (id.equals(r.getID())) {
                r.removeSelf();
                reservations.remove(id);
                return;
            }
        }
    }
    
    public JSONArray list(String venue) {
        JSONArray rooms = new JSONArray();
        for (Room r: getVenue(venue).getRooms()) {
            // for (Reservation r1: r.getReservations()) {
            //     r1.getID();
            // }
            rooms.put(r.getJSON());
        }
        return rooms;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
