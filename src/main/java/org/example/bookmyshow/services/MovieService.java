package org.example.bookmyshow.services;

import lombok.Data;
import org.example.bookmyshow.model.Movie;

import javax.net.ssl.SSLSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class MovieService {
    private final Map<String,Movie> movies;
    MovieService(){
        this.movies = new HashMap<>();
    }
    public Movie createMovie(String movieName) {
        String movieId = UUID.randomUUID().toString();
        return new Movie(movieId, movieName);
    }

    public Movie getMovie(String movieId) {
        return movies.get(movieId);
    }
}
