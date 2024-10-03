package src.entity;

import java.awt.Color;

public class seat {
    protected int seatNumber;
    protected String seatName;
    protected double price;
    protected Color available = Color.GREEN;

    public seat(int seatNumber, String seatName, double price) {
        this.seatNumber = seatNumber;
        this.seatName = seatName;
        this.price = price;
    }

    public int getSeatNumber() {return seatNumber;}

    public void setSeatNumber(int seatNumber) {this.seatNumber = seatNumber;}

    public String getSeatName() {return seatName;}

    public void setSeatName(String seatName) {this.seatName = seatName;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}
}


// Inherited class for Ordinary Seat


