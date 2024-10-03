package src.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class browseFlight implements browseStrategy {
    public Object[][] browse() {
        ArrayList<Flight> tempFlights = FlightListSingleton.getOnlyInstance().flightList;

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a 2D array to store flight data
        Object[][] flightsData = new Object[tempFlights.size()][8];

        // Iterate through the flights and populate the array
        for (int i = 0; i < tempFlights.size(); i++) {
            Flight flight = tempFlights.get(i);
            flightsData[i][0] =  flight.getFlightNumber();
            flightsData[i][1] = dateFormat.format(flight.getDepartureDate());
            flightsData[i][2] = dateFormat.format(flight.getReturnDate());
            flightsData[i][3] = flight.getPriceRange();
            flightsData[i][4] = flight.getDepartureLocation();
            flightsData[i][5] = flight.getArrivalLocation();
            flightsData[i][6] = flight.getDepartureTime();
            flightsData[i][7] = flight.getArrivalTime();
        }

        return flightsData;
    }
}
