package src.Control;

import java.sql.Connection;

import src.entity.Account;
import src.entity.FlightListSingleton;
import src.entity.User;
import src.entity.UserListSingleton;

public class FlightController {
    public Object[][] getFlightList(String username, Connection db) {

        FlightListSingleton.getAllFlights(db);
        FlightListSingleton.getOnlyInstance().printAllFlights();

        Account person = UserListSingleton.getOnlyInstance().findUserByUsername(username);

        // Check if the person is a Guest
        if (person instanceof User) {
            User user = (User) person; // Cast to Guest
            Object[][] flights = user.browseFlightList();
            return flights;
        }

        // You might want to return something from this method
        return null;
    }


}
