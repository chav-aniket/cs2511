package unsw.venues;

import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import java.time.LocalDate;

public class Room {
    public enum Size {
        SMALL, MEDIUM, LARGE
    }
    private String name;
    private Size roomSize;
    private LinkedHashMap<String, Reservation> reservations;

    private Comparator<Reservation> byDate = new Comparator<Reservation>() {
        public int compare(Reservation r1, Reservation r2) {
            if (r1.getStart().isBefore(r2.getStart())) return -1;
            else return 1;
        }
    };

    public Room(String name, Size roomSize) {
        this.name = name;
        this.roomSize = roomSize;
        this.reservations = new LinkedHashMap<String, Reservation>();
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return roomSize;
    }

    public Collection<Reservation> getReservations() {
        return reservations.values();
    }

    public void removeReservation(String id) {
        reservations.remove(id);
    }

    public Collection<Reservation> orderedReservations() {
        ArrayList<Reservation> sorted = new ArrayList<Reservation>(reservations.values());
        Collections.sort(sorted, byDate);
        return sorted;
    }

    public JSONObject getJSON() {
        JSONObject room = new JSONObject();
        JSONArray reserveArray = new JSONArray();
        Collection<Reservation> reservations = orderedReservations();
        for (Reservation r1: reservations) {
            reserveArray.put(r1.getJSONDetails());
        }
        room.put("reservations", reserveArray);
        room.put("room", name);
        return room;
    }

    public static Size stringToSize(String s) {
        Size ret = Size.SMALL;
        switch(s) {
            case "medium":
                ret = Size.MEDIUM;
                break;
            case "large":
                ret = Size.LARGE;
                break;
        }
        return ret;
    }

    public boolean isAvailable(LocalDate start, LocalDate end) {
        for (Reservation r: getReservations()) {
            if (!r.isFree(start, end)) return false;
        }
        return true;
    }

    public void addReservation(String id, Reservation r) {
        reservations.put(id, r);
    }

}
