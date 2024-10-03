package src.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Flight {
    private int flight_id;
    private String flightNumber;
    private Date departureDate;
    private Date returnDate;
    private String priceRange;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;
    private seatMap seatMap;

    public Flight(int flight_id, String flightNumber, Date departureDate, Date returnDate, String priceRange,
                  String departureLocation, String arrivalLocation, String departureTime, String arrivalTime,
                  int numOfBusinessSeats, int numOfComfortSeats, int numOfOrdinarySeats) {
        this.flight_id = flight_id;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.priceRange = priceRange;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;

        // Create and set seats into the seatMap
        this.seatMap = new seatMap();

        // Check and adjust seat numbers to ensure the total doesn't exceed 286
        int totalSeats = numOfBusinessSeats + numOfComfortSeats + numOfOrdinarySeats;
        if (totalSeats > 286) {
            // Adjust seat numbers proportionally to ensure the total is within the limit
            double factor = 286.0 / totalSeats;
            numOfBusinessSeats = (int) Math.round(numOfBusinessSeats * factor);
            numOfComfortSeats = (int) Math.round(numOfComfortSeats * factor);
            numOfOrdinarySeats = (int) Math.round(numOfOrdinarySeats * factor);
        }

        for (int i = 1; i <= numOfBusinessSeats; i++) {
            BusinessSeat businessSeat = new BusinessSeat(i, "Business Seat " + i, "Additional Business Info " + i);
            seatMap.addSeat(businessSeat);
        }

        for (int i = 1; i <= numOfComfortSeats; i++) {
            ComfortSeat comfortSeat = new ComfortSeat(i, "Comfort Seat " + i, false);
            seatMap.addSeat(comfortSeat);
        }

        for (int i = 1; i <= numOfOrdinarySeats; i++) {
            OrdinarySeat ordinarySeat = new OrdinarySeat(i, "Ordinary Seat " + i, true);
            seatMap.addSeat(ordinarySeat);
        }
    }




        public static int addFlight(Connection dbConnect, String flightNumber, Date departureDate, Date returnDate,
                                String priceRange, String departureLocation, String arrivalLocation,
                                String departureTime, String arrivalTime, int numOfBusinessSeats,
                                int numOfComfortSeats, int numOfOrdinarySeats) {
        FlightListSingleton temp = FlightListSingleton.getOnlyInstance();

        if ((numOfBusinessSeats + numOfComfortSeats + numOfOrdinarySeats) > 286) {
            return 0;
        }

        try {
            if (dbConnect != null && !dbConnect.isClosed()) {
                String query = "INSERT INTO flight_table (flight_number, departure_date, return_date, price_range, " +
                        "departure_location, arrival_location, departure_time, arrival_time, " +
                        "num_of_business_seats, num_of_comfort_seats, num_of_ordinary_seats) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)) {
                    preparedStatement.setString(1, flightNumber);
                    preparedStatement.setDate(2, new java.sql.Date(departureDate.getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(returnDate.getTime()));
                    preparedStatement.setString(4, priceRange);
                    preparedStatement.setString(5, departureLocation);
                    preparedStatement.setString(6, arrivalLocation);
                    preparedStatement.setString(7, departureTime);
                    preparedStatement.setString(8, arrivalTime);
                    preparedStatement.setInt(9, numOfBusinessSeats);
                    preparedStatement.setInt(10, numOfComfortSeats);
                    preparedStatement.setInt(11, numOfOrdinarySeats);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Flight added successfully.");
                    } else {
                        System.out.println("Failed to add flight.");
                    }
                }
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add flight to the singleton instance

        temp.addFlight(new Flight(temp.getFlightList().size() + 2, flightNumber, departureDate, returnDate,
        priceRange, departureLocation, arrivalLocation, departureTime, arrivalTime,
        numOfBusinessSeats, numOfComfortSeats, numOfOrdinarySeats));

        return 1;
    }



    



    // Getters and setters (you can generate these using your IDE)

    public String getFlightNumber() {
        return flightNumber;
    }
    public int getFlightId() {
        return flight_id;
    }


    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public seatMap getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(seatMap seatMap) {
        this.seatMap = seatMap;
    }
}
