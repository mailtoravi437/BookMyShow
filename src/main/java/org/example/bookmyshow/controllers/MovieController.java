package org.example.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.bookmyshow.services.MovieService;

@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;
    public String createMovie(@NonNull final String movieName) {
        return movieService.createMovie(movieName).getMovieId();
    }

}
