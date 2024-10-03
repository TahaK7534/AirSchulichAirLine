package src.entity;

public class Guest extends Account{

    public Guest(int userId, String username, String password, String email, String role) {
        super(userId, username, password, email, role);
    }

    public Object[][] browseFlightList() {
        browseStrategy browser = new browseFlight();
        Object[][] result = browser.browse();
        return result;
    }




    
}
