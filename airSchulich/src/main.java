// javac -cp .;lib/mysql-connector-j-8.2.0.jar src\Boundry\*.java src\Control\*.java src\Entity\*.java src\*.java
// java -cp .;lib/mysql-connector-j-8.2.0.jar src.main



// javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Entity/*.java
// javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Control/*.java
// javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Boundry/*.java
// javac -cp .:lib/mysql-connector-j-8.2.0.jar src/*.java


package src;

// import src.Boundry.*;

// import src.entity.*;
// import src.Control.*;


import java.sql.*;

import javax.swing.SwingUtilities;

import src.Boundry.UserGUI;
import src.Control.UserController;
import src.Boundry.mainPageGUI;
import src.entity.FlightListSingleton;
// import src.entity.User;
import src.entity.UserListSingleton;

public class main {
    private static Connection dbConnect;

    public static void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/airschulich", "root", "Pokemon1K!");
        } catch (SQLException e) {
            System.err.println("Error establishing database connection:");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            createConnection();



            UserListSingleton.getAllUsers(dbConnect);
            // UserListSingleton.getOnlyInstance().printAllUsers();
            // User.addUser(dbConnect, "newUser", "newPassword", "newUser@example.com");



            FlightListSingleton.getAllFlights(dbConnect);

        // Print all flights
            FlightListSingleton.getOnlyInstance().printAllFlights();

            UserController temp  = new UserController();

            UserGUI userGUI = new UserGUI(temp);
            userGUI.createLogin(dbConnect);


        });


    }


}
