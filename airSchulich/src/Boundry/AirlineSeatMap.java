package src.Boundry;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class AirlineSeatMap extends JFrame {
    private JButton[][] seats;
    private JButton confirmButton;
    private JButton backButton;
    private List<JButton> selectedSeats;

    public AirlineSeatMap(String username, Connection dbConnect) {
        setTitle("Select a seatmap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Select a seatmap");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel seatMapPanel = new JPanel(new GridLayout(40, 11, 5, 5)); // Increased columns to accommodate spaces
        seats = new JButton[40][111]; // Increased columns to accommodate spaces

        selectedSeats = new ArrayList<>();

        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 11; col++) {
                if (col == 3 || col == 7 || row == 5 || row == 15) {
                    seats[row][col] = new JButton("");
                    seats[row][col].setEnabled(false);
                    seatMapPanel.add(seats[row][col]);
                } else {
                    int seatNumber;
                    String seatType;
                    if (row < 5) {
                        seatType = "B";
                        seatNumber = (row * 7) + col + 1 - (col > 6 ? 2 : 1);
                    } else if (row < 15){
                        seatType = "C";
                        seatNumber = (((row - 10) * 7) + col + 1 - (col > 6 ? 2 : 1));
                    }
                    else {
                        seatType = "E";
                        seatNumber = (((row - 10) * 7) + col + 1 - (col > 6 ? 2 : 1));


                    }
                    seats[row][col] = new JButton(seatType + seatNumber);
                    seatMapPanel.add(seats[row][col]);
                    seats[row][col].addActionListener(new SeatButtonListener());
                }
            }
        }

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        backButton = new JButton("Back");
        confirmButton = new JButton("Confirm");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the seat map window
                SwingUtilities.invokeLater(() -> new FlightSelectionGUI(username, dbConnect)); // Opens the flight menu GUI
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedSeats.size() > 0) {
                    int option = JOptionPane.showConfirmDialog(null, "Confirm booking selected seat(s)?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        StringBuilder selectedSeatNumbers = new StringBuilder();
                        for (JButton seat : selectedSeats) {
                            selectedSeatNumbers.append(seat.getText()).append(", ");
                        }
                        JOptionPane.showMessageDialog(null, "Seat booking successful for: " + selectedSeatNumbers.toString());
                        dispose(); // Closes the window after seat selection
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select at least one seat before confirming.");
                }
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        add(seatMapPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class SeatButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedSeat = (JButton) e.getSource();
            if (!clickedSeat.getText().isEmpty()) {
                if (selectedSeats.contains(clickedSeat)) {
                    selectedSeats.remove(clickedSeat);
                    clickedSeat.setBackground(null); // Deselect the seat
                } else {
                    selectedSeats.add(clickedSeat);
                    clickedSeat.setBackground(Color.GREEN); // Highlight selected seat
                }
            }
        }
    }
/*    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AirlineSeatMap());
    } 
*/

}
