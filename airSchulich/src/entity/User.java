package src.entity;



public class User extends Guest{

    String MonthlyInfo;

    public User(int userId, String username, String password, String email, String role) {
        super(userId, username, password, email, role);
    }

    public void getMonthlyInfo() {

    }



}


