package org.example.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.bookmyshow.exceptions.NotFoundExceptions;
import org.example.bookmyshow.model.Movie;
import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.model.Seat;
import org.example.bookmyshow.model.Show;
import org.example.bookmyshow.services.MovieService;
import org.example.bookmyshow.services.SeatAvailabilityService;
import org.example.bookmyshow.services.ShowService;
import org.example.bookmyshow.services.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {
    private final ShowService showService;
    private final SeatAvailabilityService seatAvailabilityService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    public String createShow(@NonNull final String movieId,@NonNull final String screenId,@NonNull final Date startTime,@NonNull final Integer durationInSeconds) throws NotFoundExceptions {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getShowId();
    }

    public List<String> getAvailableSeats(@NonNull final String showId ) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }


}
