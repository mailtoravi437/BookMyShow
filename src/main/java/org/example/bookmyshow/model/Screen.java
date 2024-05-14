package org.example.bookmyshow.model;


import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Screen {
    private final String id;
    private final String name;
    private final Theatre theatre;

    private final List<Seat> seatList;

    public Screen(@NonNull final String name, @NonNull final Theatre theatre) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.theatre = theatre;
        this.seatList = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        this.seatList.add(seat);
    }
}
