package src.Boundry;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class FlightDetailsGUI {
    private JFrame detailsFrame;
    private JButton backButton;
    private JButton nextButton;

    public FlightDetailsGUI(String selectedFlightInfo, Object[][] flightsData, String username, Connection dbConnect) {
        for (Object[] flight : flightsData) {
            if (selectedFlightInfo.equals(flight[0])) {
                String flightDetails = "Flight No.: " + flight[0] + "\n" +
                        "Departure Date: " + flight[1] + "\n" +
                        "Arrival Date: " + flight[2] + "\n" +
                        "Price Range: " + flight[3] + "\n" +
                        "Departure Location: " + flight[4] + "\n" +
                        "Arrival Location: " + flight[5] + "\n" +
                        "Departure Time: " + flight[6] + "\n" +
                        "Arrival Time: " + flight[7];

                JTextArea textArea = new JTextArea(flightDetails);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                detailsFrame = new JFrame("Flight Details - " + selectedFlightInfo);

                backButton = new JButton("Back");
                backButton.addActionListener(e -> {
                    detailsFrame.dispose();
                    new FlightSelectionGUI(username, dbConnect); // Open the FlightSelectionGUI
                });

                nextButton = new JButton("Next");
                nextButton.addActionListener(e -> {
                    detailsFrame.dispose(); // Close the flight details GUI
                    new AirlineSeatMap(username, dbConnect); // Open the AirlineSeatMap GUI
                });

                JPanel detailsPanel = new JPanel(new BorderLayout());
                detailsPanel.add(scrollPane, BorderLayout.CENTER);

                JPanel buttonsPanel = new JPanel(new FlowLayout());
                buttonsPanel.add(backButton);
                buttonsPanel.add(nextButton);

                detailsPanel.add(buttonsPanel, BorderLayout.SOUTH);

                detailsFrame.add(detailsPanel);
                detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                detailsFrame.setSize(400, 300);
                detailsFrame.setVisible(true);
                break;
            }
        }
    }
    
/*
    public static void main(String[] args) {
        // This class is instantiated from FlightSelectionGUI, not used directly as an entry point
    }
*/
}
