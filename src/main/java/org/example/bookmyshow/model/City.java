package org.example.bookmyshow.model;

import lombok.Data;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class City {
    private final String id;
    private final String name;
    private final List<Theatre> theatres;


    public City(@NonNull final String id, @NonNull final String name) {
        this.id = id;
        this.name = name;
        this.theatres = new ArrayList<>();
    }

    public void addTheatre(@NonNull final Theatre theatre) {
        theatres.add(theatre);
    }
}