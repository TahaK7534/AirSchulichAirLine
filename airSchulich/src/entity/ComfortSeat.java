package src.entity;

public class ComfortSeat extends seat {
    private boolean hasExtraLegroom;

    public ComfortSeat(int seatNumber, String seatName, boolean hasExtraLegroom) {
        super(seatNumber, seatName, 150.0); // Example price for Comfort Seat
        this.hasExtraLegroom = hasExtraLegroom;
    }

    public boolean hasExtraLegroom() {
        return hasExtraLegroom;
    }

    public void setExtraLegroom(boolean extraLegroom) {
        hasExtraLegroom = extraLegroom;
    }
    
}