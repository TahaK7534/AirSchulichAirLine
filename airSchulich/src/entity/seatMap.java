package src.entity;

import java.util.ArrayList;

public class seatMap {
    private ArrayList<seat> seats;

    public seatMap() {
        this.seats = new ArrayList<>();
    }

    public void addSeat(seat seat) {
        seats.add(seat);
    }

    public void removeSeat(int seatNumber) {
        // Find the seat with the specified seatNumber and remove it
        for (seat seat : seats) {
            if (seat.getSeatNumber() == seatNumber) {
                seats.remove(seat);
                break;
            }
        }
    }

    public ArrayList<seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<seat> seats) {
        this.seats = seats;
    }

    // Count the number of business seats in the seatMap
    public int getNumOfBusinessSeats() {
        int count = 0;
        for (seat seat : seats) {
            if (seat instanceof BusinessSeat) {
                count++;
            }
        }
        return count;
    }

    // Count the number of comfort seats in the seatMap
    public int getNumOfComfortSeats() {
        int count = 0;
        for (seat seat : seats) {
            if (seat instanceof ComfortSeat) {
                count++;
            }
        }
        return count;
    }

    // Count the number of ordinary seats in the seatMap
    public int getNumOfOrdinarySeats() {
        int count = 0;
        for (seat seat : seats) {
            if (seat instanceof OrdinarySeat) {
                count++;
            }
        }
        return count;
    }
}
