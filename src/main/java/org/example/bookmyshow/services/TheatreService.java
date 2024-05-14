package org.example.bookmyshow.services;

import lombok.Data;
import lombok.NonNull;
import org.example.bookmyshow.exceptions.NotFoundExceptions;
import org.example.bookmyshow.model.City;
import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.model.Seat;
import org.example.bookmyshow.model.Theatre;

import javax.net.ssl.SSLSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class TheatreService {
    private final Map<String,Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    TheatreService(){
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>(); }

    public Theatre createTheatre(String theatreName,City city){
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId,theatreName, city);
        theatres.put(theatreId,theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(@NonNull final String screenName,@NonNull final Theatre theatre) {
        Screen screen = new Screen(screenName,theatre);
        screens.put(screen.getId(),screen);
        theatre.addScreens(screen);
        return screen;
    }

    public Seat getSeat(@NonNull final String seatId){
        if(!seats.containsKey(seatId)){
            throw new NotFoundExceptions("Not found");
        }

        return seats.get(seatId);
    }

    public Screen getScreen(String screenId) throws NotFoundExceptions{
        if(!screens.containsKey(screenId))
            throw new NotFoundExceptions("Screen not found");
        return screens.get(screenId);
    }

    public Seat createSeatInScreen(Integer row, Integer seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId,row,seatNo);
        seats.put(seatId,seat);
        screen.addSeat(seat);
        return seat;
    }
}
