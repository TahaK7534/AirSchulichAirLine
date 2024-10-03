package src.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class FlightListSingleton {
    private static FlightListSingleton instance = new FlightListSingleton();

    public ArrayList<Flight> flightList;

    private FlightListSingleton() {
        flightList = new ArrayList<>();
    }

    public static FlightListSingleton getOnlyInstance() {
        return instance;
    }

    public void addFlight(Flight flight) {
        flightList.add(flight);
    }

    public static void getAllFlights(Connection dbConnect) {
        FlightListSingleton.getOnlyInstance().flightList = new ArrayList<>();
        try {
            if (dbConnect != null && !dbConnect.isClosed()) {
                Statement statement = dbConnect.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM flight_table");

                while (resultSet.next()) {
                    int flightId = resultSet.getInt("flight_id");
                    String flightNumber = resultSet.getString("flight_number");
                    Date departureDate = resultSet.getDate("departure_date");
                    Date returnDate = resultSet.getDate("return_date");
                    String priceRange = resultSet.getString("price_range");
                    String departureLocation = resultSet.getString("departure_location");
                    String arrivalLocation = resultSet.getString("arrival_location");
                    String departureTime = resultSet.getString("departure_time");
                    String arrivalTime = resultSet.getString("arrival_time");
                    int numOfBusinessSeats = resultSet.getInt("num_of_business_seats");
                    int numOfComfortSeats = resultSet.getInt("num_of_comfort_seats");
                    int numOfOrdinarySeats = resultSet.getInt("num_of_ordinary_seats");

                    Flight flight = new Flight(
                            flightId,   
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

                    FlightListSingleton.getOnlyInstance().addFlight(flight);
                }

                resultSet.close();
                statement.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }


    public void printAllFlights() {
        for (Flight flight : flightList) {
            System.out.println("Flight ID: " + flight.getFlightId() +
                    ", Flight Number: " + flight.getFlightNumber() +
                    ", Departure Date: " + flight.getDepartureDate() +
                    ", Return Date: " + flight.getReturnDate() +
                    ", Price Range: " + flight.getPriceRange() +
                    ", Departure Location: " + flight.getDepartureLocation() +
                    ", Arrival Location: " + flight.getArrivalLocation() +
                    ", Departure Time: " + flight.getDepartureTime() +
                    ", Arrival Time: " + flight.getArrivalTime() +
                    ", Num of Business Seats: " + flight.getSeatMap().getNumOfBusinessSeats() +
                    ", Num of Comfort Seats: " + flight.getSeatMap().getNumOfComfortSeats() +
                    ", Num of Ordinary Seats: " + flight.getSeatMap().getNumOfOrdinarySeats()
            );
        }
    }

    // Existing code...



    public Flight findFlightByFlightNumber(String flightNumber) {
        for (Flight flight : flightList) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null; // Flight not found
    }


    public ArrayList<Flight> getFlightList() {
        return flightList;
    }
    
}
