package org.example.bookmyshow.services;

import org.example.bookmyshow.model.Movie;
import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.model.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    Map<String,Show> shows = new HashMap<>();
    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId,movie,screen , startTime, durationInSeconds);
        return show;
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }
}
