package src.entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Account {
    protected int userId;
    protected String username;
    protected String password;
    protected String email;
    protected String role;
    protected Ticket ticket;
    protected browseStrategy browseType;

    public Account(int userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void createTicket(int id, int seat_num, String date, String name) {
        ticket = new Ticket(id, seat_num, date, name);
    }

    public Ticket getTicket() {return this.ticket;}

    public int getUserId() {return userId;}
    
    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public String getEmail() {return email;}

    public String getRole() {return role;}




    public static int addUser(Connection dbConnect, String username, String password, String email, String role) {
        UserListSingleton temp = UserListSingleton.getOnlyInstance();
    
        // Validate email format
        if (!(email.contains("@") && email.contains("."))) {
            return -1;
        }
    
        // Check for duplicate username, email, or empty fields
        for (int i = 0; i < temp.UserList.size(); i++) {
            if (temp.UserList.get(i).getUsername().equals(username) ||
                temp.UserList.get(i).getEmail().equals(email) ||
                username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                return 0;
            }
        }
    
        try {
            if (dbConnect != null && !dbConnect.isClosed()) {
                String query = "INSERT INTO user_table (username, password, email, role) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, role);
    
                    int rowsAffected = preparedStatement.executeUpdate();
    
                    if (rowsAffected > 0) {
                        System.out.println("User added successfully.");
                    } else {
                        System.out.println("Failed to add user.");
                    }
                }
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Add user to the singleton instance with the role
        temp.addUser(new User(temp.UserList.size() + 2, username, password, email, role));
    
        return 1;

    }
    



    static public String login(String username, String password) {
        UserListSingleton temp = UserListSingleton.getOnlyInstance();
        for (int i = 0; i < temp.UserList.size(); i++) {
            if ((temp.UserList.get(i).username.equals(username)) &&  (temp.UserList.get(i).password.equals(password))) {
                System.out.println("Loging in");
                return temp.UserList.get(i).role;
            }
    }
    return null;

    }

    public void setStrategy(browseStrategy s) {
        browseType = s;
    }
    public void performBrowse() {
        browseType.browse();
    }



}



