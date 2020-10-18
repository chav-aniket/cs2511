package unsw.venues;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private LocalDate start;
    private LocalDate end;
    private ArrayList<Room> rooms;

    public Reservation(String id, LocalDate start, LocalDate end, ArrayList<Room> rooms) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.rooms = rooms;
        roomAdd();
    }
    
    public void roomAdd() {
        for (Room r: rooms) {
            r.addReservation(id, this);
        }
    }

    public String getID() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void removeSelf() {
        for (Room r: rooms) {
            r.removeReservation(id);
        }
    }

    public boolean isFree(LocalDate from, LocalDate to) {
        if (start.compareTo(to) <= 0 && end.compareTo(from) >= 0) {
            return false;
        }
        return true;
    }

    public JSONObject getJSONDetails() {
        JSONObject r = new JSONObject();
        r.put("end", end.toString());
        r.put("start", start.toString());
        r.put("id", id);
        return r;
    }

    public JSONArray getJSONRooms() {
        JSONArray roomsArray = new JSONArray();
        for (Room room: rooms) {
            roomsArray.put(room.getName());
        }
        return roomsArray;
    }
}
