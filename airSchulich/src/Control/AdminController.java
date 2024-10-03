package src.Control;

import src.entity.Flight;

import java.sql.Connection;
import java.util.Date;

public class AdminController {

    public int addFlight(Connection dbConnect, String flightNumber, Date departureDate, Date returnDate,
                          String priceRange, String departureLocation, String arrivalLocation,
                          String departureTime, String arrivalTime, int numOfBusinessSeats,
                          int numOfComfortSeats, int numOfOrdinarySeats) {
        int x =Flight.addFlight(
                dbConnect,
                flightNumber,
                departureDate,
                returnDate,
                priceRange,
                departureLocation,
                arrivalLocation,
                departureTime,
                arrivalTime,
                numOfBusinessSeats,
                numOfComfortSeats,
                numOfOrdinarySeats
        );

        return x;
    }
}
