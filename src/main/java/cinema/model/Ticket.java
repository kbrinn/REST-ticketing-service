package cinema.model;

public class Ticket {
    private String token;
    private Seat ticket;

    public Ticket(String token, Seat seat) {
        this.token = token;
        this.ticket = seat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "token='" + token + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
