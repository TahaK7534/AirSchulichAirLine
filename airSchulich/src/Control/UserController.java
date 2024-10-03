package src.Control;

import java.sql.Connection;
import src.Boundry.UserGUI;
import src.entity.Account;
// import src.entity.Guest;
import src.entity.UserListSingleton;

public class UserController {

    public int registerUser(String username, String password, String email, UserGUI x, Connection dbConnect) {

        int a = Account.addUser(dbConnect,username,password,email, "User");

        return a;
}

    public int login_controler(String username, String password) {
        String loginSuccessful = Account.login(username, password);

        if (loginSuccessful == null) {
            return 0; // Login unsuccessful
        }

        if (loginSuccessful.equals("User")) {
            return 1; // User login successful
        } 
        else if (loginSuccessful.equals("admin")) {
            return 2; // Admin login successful
        } 
        // Handle other roles if needed
        else {
            // For roles other than user or admin
            return 3; // Customize this value based on your requirements
        }
    }

    // public void guest() {
    //     Guest tempGuest = new Guest(0, null, null, null, null);


    // }

    public String findRole(String username) {
        String role = UserListSingleton.getOnlyInstance().findUserByUsername(username).getRole();
        return role;

    }
}

