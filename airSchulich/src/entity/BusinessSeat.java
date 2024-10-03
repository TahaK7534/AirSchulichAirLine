package src.entity;

public class BusinessSeat extends seat {
    private String additionalBusinessInfo;

    public BusinessSeat(int seatNumber, String seatName, String additionalBusinessInfo) {
        super(seatNumber, seatName, 300.0); // Example price for Business Seat
        this.additionalBusinessInfo = additionalBusinessInfo;
    }

    // Getter and setter for additionalBusinessInfo (you can generate these using your IDE)

    public String getAdditionalBusinessInfo() {
        return additionalBusinessInfo;
    }

    public void setAdditionalBusinessInfo(String additionalBusinessInfo) {
        this.additionalBusinessInfo = additionalBusinessInfo;
    }
}