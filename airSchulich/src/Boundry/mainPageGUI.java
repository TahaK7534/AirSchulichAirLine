package src.Boundry;

import javax.swing.*;

import src.Control.UserController;

import java.awt.*;
import java.sql.Connection;

public class mainPageGUI {

    private JFrame frame;

    public void userMainPage(String role, String username, Connection dbConnect) {
        frame = new JFrame();
        frame.setTitle("Main Page");
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel userLabel = new JLabel(role + " | " + username);
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton, Color.RED);

        logoutButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(new UserController());
            userGUI.createLogin(dbConnect);
            frame.dispose();
        });

        // Create two buttons centered both vertically and horizontally
        JButton flightView = new JButton("View Flights");
        styleButton(flightView, Color.BLUE);

        JButton MonthlyNews = new JButton("Monthly News");
        styleButton(MonthlyNews, Color.BLUE);

        flightView.setPreferredSize(new Dimension(100, 30));
        MonthlyNews.setPreferredSize(new Dimension(100, 30));





        flightView.addActionListener(e -> {
            new FlightSelectionGUI(username, dbConnect);
            frame.dispose();
        });





        MonthlyNews.addActionListener(e -> {
            // call something here
        });





        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.add(userLabel, BorderLayout.WEST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userPanel, BorderLayout.NORTH);

        // Create a panel for the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
        buttonPanel.add(flightView, gbc);

        gbc.gridy = 1;
        buttonPanel.add(MonthlyNews, gbc);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }




    private void styleButton(JButton button, Color color) {
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(80, 20));
    }
    

    public void guestPage(Connection dbConnect) {
        frame = new JFrame();
        frame.setTitle("Guest Page");
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel guestLabel = new JLabel("Welcome Guest");
        JButton loginButton = new JButton("Login");
        styleButton(loginButton, Color.RED);

        loginButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(new UserController());
            userGUI.createLogin(dbConnect);
            frame.dispose();
        });

        // Create two buttons centered both vertically and horizontally
        JButton flightView = new JButton("View Flights");
        styleButton(flightView, Color.BLUE);

        flightView.setPreferredSize(new Dimension(100, 30));

        JPanel guestPanel = new JPanel(new BorderLayout());
        guestPanel.add(guestLabel, BorderLayout.WEST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(guestPanel, BorderLayout.NORTH);

        // Create a panel for the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
        buttonPanel.add(flightView, gbc);

        gbc.gridy = 1;

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(loginButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }






    public void AdminPage(String role, String username, Connection dbConnect) {
        frame = new JFrame();
        frame.setTitle("Admin Page");
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel userLabel = new JLabel(role + " | " + username);
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton, Color.RED);

        logoutButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(new UserController());
            userGUI.createLogin(dbConnect);
            frame.dispose();
        });

        // Create two buttons centered both vertically and horizontally
        JButton flightView = new JButton("Add Flights");
        styleButton(flightView, Color.BLUE);

        JButton MonthlyNews = new JButton("Monthly News");
        styleButton(MonthlyNews, Color.BLUE);

        flightView.setPreferredSize(new Dimension(100, 30));
        MonthlyNews.setPreferredSize(new Dimension(100, 30));





        flightView.addActionListener(e -> {
            new AddFlightGUI(dbConnect, role, username);
            frame.dispose();


        });





        MonthlyNews.addActionListener(e -> {

        });





        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.add(userLabel, BorderLayout.WEST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userPanel, BorderLayout.NORTH);

        // Create a panel for the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
        buttonPanel.add(flightView, gbc);

        gbc.gridy = 1;
        buttonPanel.add(MonthlyNews, gbc);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }







}