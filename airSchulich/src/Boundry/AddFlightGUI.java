package src.Boundry;

import src.Control.AdminController;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFlightGUI {

    private JFrame frame;
    private JTextField flightNumberField;
    private JTextField departureDateField;
    private JTextField returnDateField;
    private JTextField priceRangeField;
    private JTextField departureLocationField;
    private JTextField arrivalLocationField;
    private JTextField departureTimeField;
    private JTextField arrivalTimeField;
    private JTextField numOfBusinessSeatsField;
    private JTextField numOfComfortSeatsField;
    private JTextField numOfOrdinarySeatsField;

    public AddFlightGUI(Connection dbConnect, String role, String username) {
        frame = new JFrame();
        frame.setTitle("Add Flight");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create labels and text fields for user input
        JLabel flightNumberLabel = new JLabel("Flight Number:");
        flightNumberField = new JTextField();

        JLabel departureDateLabel = new JLabel("Departure Date (yyyy-MM-dd):");
        departureDateField = new JTextField();

        JLabel returnDateLabel = new JLabel("Return Date (yyyy-MM-dd):");
        returnDateField = new JTextField();

        JLabel priceRangeLabel = new JLabel("Price Range:");
        priceRangeField = new JTextField();

        JLabel departureLocationLabel = new JLabel("Departure Location:");
        departureLocationField = new JTextField();

        JLabel arrivalLocationLabel = new JLabel("Arrival Location:");
        arrivalLocationField = new JTextField();

        JLabel departureTimeLabel = new JLabel("Departure Time:");
        departureTimeField = new JTextField();

        JLabel arrivalTimeLabel = new JLabel("Arrival Time:");
        arrivalTimeField = new JTextField();

        JLabel numOfBusinessSeatsLabel = new JLabel("Number of Business Seats:");
        numOfBusinessSeatsField = new JTextField();

        JLabel numOfComfortSeatsLabel = new JLabel("Number of Comfort Seats:");
        numOfComfortSeatsField = new JTextField();

        JLabel numOfOrdinarySeatsLabel = new JLabel("Number of Ordinary Seats:");
        numOfOrdinarySeatsField = new JTextField();

        // Create a button to submit the form
        JButton addButton = new JButton("Add Flight");
        addButton.addActionListener(e -> {
            // Get values from text fields
            String flightNumber = flightNumberField.getText();
            String departureDateStr = departureDateField.getText();
            String returnDateStr = returnDateField.getText();
            String priceRange = priceRangeField.getText();
            String departureLocation = departureLocationField.getText();
            String arrivalLocation = arrivalLocationField.getText();
            String departureTime = departureTimeField.getText();
            String arrivalTime = arrivalTimeField.getText();
            int numOfBusinessSeats = Integer.parseInt(numOfBusinessSeatsField.getText());
            int numOfComfortSeats = Integer.parseInt(numOfComfortSeatsField.getText());
            int numOfOrdinarySeats = Integer.parseInt(numOfOrdinarySeatsField.getText());

            // Example: Create a new Flight object and add it to the database
            AdminController adminController = new AdminController();
            int x = adminController.addFlight(
                    dbConnect,
                    flightNumber,
                    parseDate(departureDateStr),
                    parseDate(returnDateStr),
                    priceRange,
                    departureLocation,
                    arrivalLocation,
                    departureTime,
                    arrivalTime,
                    numOfBusinessSeats,
                    numOfComfortSeats,
                    numOfOrdinarySeats
            );

            // Handle the result as per your logic
        if (x == 0) {
            JOptionPane.showMessageDialog(frame, "Too many seats. Please try again.");
            mainPageGUI main = new mainPageGUI();
            main.AdminPage(role, username, dbConnect);
            frame.dispose();            

        }

        if (x == 1) {

            JOptionPane.showMessageDialog(frame, "Flight added");
            mainPageGUI main = new mainPageGUI();
            main.AdminPage(role, username, dbConnect);
            frame.dispose();

        }

        });

        // Create a panel to organize components
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(flightNumberLabel);
        panel.add(flightNumberField);
        panel.add(departureDateLabel);
        panel.add(departureDateField);
        panel.add(returnDateLabel);
        panel.add(returnDateField);
        panel.add(priceRangeLabel);
        panel.add(priceRangeField);
        panel.add(departureLocationLabel);
        panel.add(departureLocationField);
        panel.add(arrivalLocationLabel);
        panel.add(arrivalLocationField);
        panel.add(departureTimeLabel);
        panel.add(departureTimeField);
        panel.add(arrivalTimeLabel);
        panel.add(arrivalTimeField);
        panel.add(numOfBusinessSeatsLabel);
        panel.add(numOfBusinessSeatsField);
        panel.add(numOfComfortSeatsLabel);
        panel.add(numOfComfortSeatsField);
        panel.add(numOfOrdinarySeatsLabel);
        panel.add(numOfOrdinarySeatsField);

        // Add components to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(addButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
