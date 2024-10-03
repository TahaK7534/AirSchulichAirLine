package src.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserListSingleton {
    private static UserListSingleton instance = new UserListSingleton();

    public ArrayList<Account> UserList;

    private UserListSingleton() {
        UserList = new ArrayList<Account>();
    }

    public static UserListSingleton getOnlyInstance() {
        return instance;
    }

    public void addUser(Account user) {
        UserList.add(user);
    }


        public static void  getAllUsers(Connection dbConnect) {

        try {
            if (dbConnect != null && !dbConnect.isClosed()) {
                Statement statement = dbConnect.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_table");

                while (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String role = resultSet.getString("role");

                    Account user;

                    if ("User".equals(role)) {
                        user = new User(userId, username, password, email, role);
                    }
                    else if("Admin".equals(role)) {
                        user = new Admin(userId, username, password, email, role);
                    }

                    else if ("flightAttendent".equals(role)){
                        user = new flightAttendent(userId, username, password, email, role);
                    }
                    else if ("airlineAgents".equals(role)) {
                        user = new airlineAgents(userId, username, password, email, role);
                    }
                    else if ("tourismAgents".equals(role)) {
                        user = new tourismAgent(userId, username, password, email, role);
                    }
                    
                    else {
                        user = new Account(userId, username, password, email, role);
                    }
    

                    UserListSingleton.getOnlyInstance().addUser(user);
                }

                resultSet.close();
                statement.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    public void printAllUsers() {
        for (Account user : UserList) {
            System.out.println("User ID: " + user.getUserId() + ", Username: " + user.getUsername() +
                    ", Password: " + user.getPassword() + ", Email: " + user.getEmail());
        }
    }




    public Account findUserByUsername(String username) {
        for (Account user : UserList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }


}
