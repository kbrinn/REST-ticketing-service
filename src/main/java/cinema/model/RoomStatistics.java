package cinema.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class RoomStatistics {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;
    @JsonIgnore
    HashMap<String, Ticket> room_tickets;


    public RoomStatistics(HashMap<String, Ticket> room_tickets) {
        this.room_tickets = new HashMap<>();
    }

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public HashMap<String, Ticket> getRoom_tickets() {
        return room_tickets;
    }

    public void setRoom_tickets(HashMap<String, Ticket> room_tickets) {
        this.room_tickets = room_tickets;
    }

    @Override
    public String toString() {
        return "RoomStatistics{" +
                "current_income=" + current_income +
                ", number_of_available_seats=" + number_of_available_seats +
                ", number_of_purchased_tickets=" + number_of_purchased_tickets +
                ", room_tickets=" + room_tickets +
                '}';
    }

    public void generateStatistics() {
        room_tickets.forEach((key, value) -> {
            if (value.getTicket().isSold()) {
                current_income += value.getTicket().getPrice();
                number_of_purchased_tickets++;
                number_of_available_seats = (Room.TOTAL_ROWS * Room.TOTAL_COLUMNS) - number_of_purchased_tickets;
            }
        });
    }
}
