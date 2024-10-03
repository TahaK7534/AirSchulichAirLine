package src.entity;

public class Ticket {
    private int id;
    private int seat_num;
    private String date;
    private String passenger_name;

    public Ticket(int id, int seat_num, String date, String name) {
        this.id = id;
        this.seat_num = seat_num;
        this.date = date;
        this.passenger_name = name;
    }

    public int getID() {return id;}
    public int getSeatNum() {return seat_num;}
    public String getDate() {return date;}
    public String getName() {return passenger_name;}
    

}
