package src.Boundry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Control.FlightController;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Connection;

class FlightSelectionGUI {
    private JFrame frame;
    private JTable flightTable;
    private JButton confirmButton;

    public FlightSelectionGUI(String username, Connection dbConnect) {



        frame = new JFrame("Airline Booking System");
        JButton refreshButton = new JButton("Refresh Flights");
        confirmButton = new JButton("Confirm Selection");
        confirmButton.setEnabled(false); // Initially disable confirm button

        // Sample flight data (you can replace this with your actual flight data)
        // Object[][] flightsData = {
        //         {"Flight 001", "2023-12-01", "2023-12-05", "$200 - $300", "Location A", "Location B", "09:00 AM", "12:30 PM"},
        //         {"Flight 002", "2023-12-02", "2023-12-06", "$250 - $350", "Location C", "Location D", "10:30 AM", "01:30 PM"},
        //         // Add more flight information as needed
        // };

        FlightController flightCon = new FlightController();
        Object[][] flightsData = flightCon.getFlightList(username,dbConnect);
        




        // Column headers for the first GUI
        String[] columnHeaders = {"Flight No.", "Date", "Arrival Date", "Price Range"};

        // Creating the table model (disabling cell editing) for the first GUI
        DefaultTableModel model = new DefaultTableModel(flightsData, columnHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        flightTable = new JTable(model);
        flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flightTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering

        JScrollPane scrollPane = new JScrollPane(flightTable);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(refreshButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(confirmButton, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> {
            // Logic to refresh flights (e.g., fetching updated flight data)
            // Replace this with your actual logic to update flights
            // For now, let's assume the data remains the same for demonstration purposes
            



            // JOptionPane.showMessageDialog(frame, "Flights Refreshed!");
            new FlightSelectionGUI(username,dbConnect);
            frame.dispose();

        });

        flightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = flightTable.getSelectedRow();
                    confirmButton.setEnabled(selectedRow != -1); // Enable confirm button if a row is selected
                }
            }
        });

        confirmButton.addActionListener(e -> {
            int selectedRow = flightTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedFlightInfo = (String) model.getValueAt(selectedRow, 0); // Assuming Flight No. is at index 0
                new FlightDetailsGUI(selectedFlightInfo, flightsData, username, dbConnect); // Open flight details GUI for the selected flight
                frame.dispose(); // Close the current flight menu info
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a flight to proceed.");
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }


}
