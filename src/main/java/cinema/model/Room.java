package cinema.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Room {
    public static final int TOTAL_ROWS = 9;
    public static final int TOTAL_COLUMNS = 9;
    private int total_columns;
    private int total_rows;
    private Seat [][] available_seats;

    public Room() {
        this.total_rows = total_rows;
        this.total_rows = total_columns;
        this.generateSeats();
    }

    public Room(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = available_seats;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public Seat[][] getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Seat[][] available_seats) {
        this.available_seats = available_seats;
    }

    @Override
    public String toString() {
        return "Room{" +
                "total_columns=" + total_columns +
                ", total_rows=" + total_rows +
                ", available_seats=" + Arrays.toString(available_seats) +
                '}';
    }

    public void generateSeats() {
        available_seats = new Seat[this.total_rows][this.total_columns];
        for (int i = 0; i <= this.total_rows; i++) {
            for (int j = 0; j <= this.total_columns; j++) {
                if (i <= 4) {
                    available_seats[i][j] = new Seat(i, j, 10, false);
                } else {
                    available_seats[i][j] = new Seat(i,j,8,false);
                }
            }
        }
    }

    public ArrayList<Seat> getAvailableSeats() {
        ArrayList<Seat> flattenList = new ArrayList<>();
        for (Seat[] row : available_seats) {
            for (Seat seat : row) {
                flattenList.add(seat);
            }
        }
        return flattenList;
    }

    public Ticket purchaseSeat (Seat seat) {
        UUID uuid = UUID.randomUUID();
        LoggerFactory.getLogger(Room.class).info("row: " + seat.getRow() + "column: " + seat.getColumn() + "sold? " + seat.isSold());
        available_seats[seat.getRow() - 1][seat.getColumn() - 1].setSold(true);
        Ticket ticket = new Ticket(uuid.toString(),available_seats[seat.getRow() - 1][seat.getColumn() - 1]);
        return ticket;
    }

    public ReturnedTicket returnTicket(Seat seat) {
        available_seats[seat.getRow() - 1][seat.getColumn() -1].setSold(false);
        ReturnedTicket returnedTicket = new ReturnedTicket (available_seats[seat.getRow() - 1][seat.getColumn() - 1]);
        return returnedTicket;
    }

    public boolean checkSeatSold (Seat seat) {
        return available_seats[seat.getRow() - 1][seat.getColumn() - 1].isSold();
    }
}
