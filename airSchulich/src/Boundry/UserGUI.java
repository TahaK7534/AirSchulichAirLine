package src.Boundry;

import src.Control.UserController;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class UserGUI{
    public JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    public UserController userController;


    public UserGUI(UserController userController) {
        this.userController = userController;
    }


    public void CreateAccount(Connection dbConnect) {
        frame = new JFrame("User Registration");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        UserGUI temp = this;



        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);  // Disable focusability

        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            String email = emailField.getText();
            int control = userController.registerUser(username, password, email, this, dbConnect);

            if (control == 0) {
                JOptionPane.showMessageDialog(frame, "Registration failed. Username or email already in use. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (control == 1) {
                JOptionPane.showMessageDialog(frame, "Account Created. Now Login", "Created", JOptionPane.INFORMATION_MESSAGE);
                UserGUI y = new UserGUI(userController);
                y.createLogin(dbConnect);
                frame.dispose();
            } else if (control == -1) {
                JOptionPane.showMessageDialog(frame, "Registration failed. Invalid email. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
        });



        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);  // Disable focusability

        backButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(temp.userController);
            userGUI.createLogin(dbConnect);

            frame.dispose();
        });

        frame.getContentPane().add(usernameLabel);
        frame.getContentPane().add(usernameField);
        frame.getContentPane().add(passwordLabel);
        frame.getContentPane().add(passwordField);
        frame.getContentPane().add(emailLabel);
        frame.getContentPane().add(emailField);
        frame.getContentPane().add(submitButton);
        frame.getContentPane().add(backButton);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }



    public void login(Connection dbConnect) {
        UserGUI temp = this;
        frame = new JFrame("User Login");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton submitButton = new JButton("Login");
        submitButton.setFocusable(false);  // Disable focusability
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            int loginSuccessful = userController.login_controler(username, password);
            
            if (loginSuccessful == 1) {
                JOptionPane.showMessageDialog(frame, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainPageGUI main = new mainPageGUI();




                String role = userController.findRole(username);




                // a check for what role in controller class and depending on the role call a different initialize function



                main.userMainPage(role, username, dbConnect);
                frame.dispose();
            }
            else if (loginSuccessful == 2) {

                JOptionPane.showMessageDialog(frame, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainPageGUI main = new mainPageGUI();




                String role = userController.findRole(username);




                // a check for what role in controller class and depending on the role call a different initialize function



                main.AdminPage(role, username, dbConnect);
                frame.dispose();


            } 
           
           
            else {
                JOptionPane.showMessageDialog(frame, "Login failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
            usernameField.setText("");
            passwordField.setText("");
        });
        
        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);  

        backButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(temp.userController);
            userGUI.createLogin(dbConnect);
        
            frame.dispose();
        });
        

        frame.getContentPane().add(usernameLabel);
        frame.getContentPane().add(usernameField);
        frame.getContentPane().add(passwordLabel);
        frame.getContentPane().add(passwordField);
        frame.getContentPane().add(submitButton);
        frame.getContentPane().add(backButton);


        frame.setLocationRelativeTo(null); 

        frame.setVisible(true);
    }

public void createLogin(Connection dbConnect) {
        UserGUI temp = this;
        frame = new JFrame("Create Account or Login");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(2, 1, 10, 10));


        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setFocusable(false);  

        createAccountButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(temp.userController);
            userGUI.CreateAccount(dbConnect);
            frame.dispose();
        });
        
        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);  
        loginButton.addActionListener(e -> {
            UserGUI userGUI = new UserGUI(temp.userController);
            userGUI.login(dbConnect);        
            frame.dispose();
        });
        
        JButton guestButton = new JButton("Guest");
        guestButton.setFocusable(false);  
        guestButton.addActionListener(e -> {
            mainPageGUI guest = new mainPageGUI();
            guest.guestPage(dbConnect);
            frame.dispose();

            // temp.userController.guest();        
        });
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);  
        exitButton.addActionListener(e -> frame.dispose());
        
        frame.getContentPane().add(createAccountButton);
        frame.getContentPane().add(loginButton);
        frame.getContentPane().add(guestButton);
        frame.getContentPane().add(exitButton);



        frame.setLocationRelativeTo(null); 

        frame.setVisible(true);
    }

}
    