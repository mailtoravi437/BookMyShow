package org.example.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.bookmyshow.model.Seat;
import org.example.bookmyshow.model.Show;
import org.example.bookmyshow.services.BookingService;
import org.example.bookmyshow.services.MovieService;
import org.example.bookmyshow.services.ShowService;
import org.example.bookmyshow.services.TheatreService;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final TheatreService theatreService;
    private final BookingService bookingService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId, @NonNull final List<String> seatsIds){
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId,show,seats).getId();
    }
}
