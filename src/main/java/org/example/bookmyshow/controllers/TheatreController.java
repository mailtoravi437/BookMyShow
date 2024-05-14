package org.example.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.bookmyshow.exceptions.NotFoundExceptions;
import org.example.bookmyshow.model.City;
import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.model.Theatre;
import org.example.bookmyshow.services.TheatreService;

@AllArgsConstructor
public class TheatreController {
    final private TheatreService theatreService;


    public String createTheatre(@NonNull final String theatreName, @NonNull final City city){
        return theatreService.createTheatre(theatreName,city).getId();
    }

    public String createScreenInTheatre(@NonNull final String screenName,@NonNull final Theatre theatre){
        return theatreService.createScreenInTheatre(screenName,theatre).getId();
    }

    public String createSeatInScreen(@NonNull final Integer row, @NonNull final Integer seatNo, @NonNull final String screenId) throws NotFoundExceptions {
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(row,seatNo,screen).getId();
    }
}
