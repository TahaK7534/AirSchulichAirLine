package src.entity;

public class OrdinarySeat extends seat {
    private boolean isWindowSeat;

    public OrdinarySeat(int seatNumber, String seatName, boolean isWindowSeat) {
        super(seatNumber, seatName, 100.0); // Example price for Ordinary Seat
        this.isWindowSeat = isWindowSeat;
    }

    // Getter and setter for isWindowSeat (you can generate these using your IDE)

    public boolean isWindowSeat() {
        return isWindowSeat;
    }

    public void setWindowSeat(boolean windowSeat) {
        isWindowSeat = windowSeat;
    }
    
}