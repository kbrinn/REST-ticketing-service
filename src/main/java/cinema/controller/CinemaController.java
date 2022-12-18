package cinema.controller;
import cinema.model.*;

import io.micrometer.core.lang.NonNull;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@RestController
public class CinemaController {

    Room room = new Room(Room.TOTAL_ROWS, Room.TOTAL_COLUMNS);
    HashMap<String, Ticket> ticketSold = new HashMap<String, Ticket>();
    private String password;

    @GetMapping("/seats")
    public Room generateRoom() {
        return room;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat (@requestBody Seat seat) {
        if (seat.getColumn() > room.getTotal_columns() || seat.getRow() > room.getTotal_columns() ||seat.getColumn() <= 0 || seat.getRow() <= 0) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            if (room.checkSeatSold(seat)) {
                return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            } else {
                Ticket sold = room.purchaseSeat(seat);
                ticketsold.put(sold.getToken(), sold);
                LoggerFactory.getLogger(CinemaController.class).info("/purchase endpoint: " + sold.getToken());
                return new ResponseEntity<>(sold, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnedTicket (@RequestBody Ticket ticket) {
        LoggerFactory.getLogger(CinemaController.class).info("/return endpoint " + ticket.getToken());
        ReturnedTicket returnedTicket = null;
        if (ticketSold.containsKey(ticket.getToken())) {
            Ticket tmp = ticketSold.get(ticket.getToken());
            returnedTicker = room.returnTicket(tmp.getTicket());
        } else {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnedTicket, HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<?>  statistics (@RequestBody (required = false) String password) {
        LoggerFactory.getLogger(CinemaController.class).info("/stats endpoint " + password);
        RoomStatistics roomStatistics = new RoomStatistics(ticketSold);
        if(password == null || password.equals("")){
            return new ResponseEntity<>(Map.of("error","The password is wrong!"),HttpStatus.UNAUTHORIZED);
        } else {
            roomStatistics.generateStatistics();
            if (Objects.equals(password, "password=super_secret")) {
                return new ResponseEntity<>(roomStatistics, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
            }
        }
    }

}
