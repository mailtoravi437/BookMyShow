package org.example.bookmyshow.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
public class Seat {
    private final String id;
    private final int seatNumber;
    private final int row;

}
