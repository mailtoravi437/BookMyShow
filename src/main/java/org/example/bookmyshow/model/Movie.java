package org.example.bookmyshow.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Movie {
    private final String movieId;
    private final String movieName;
}
