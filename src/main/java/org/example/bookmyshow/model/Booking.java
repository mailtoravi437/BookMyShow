package org.example.bookmyshow.model;

import lombok.Data;
import lombok.NonNull;
import org.example.bookmyshow.exceptions.InvalidStateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seats;
    private final String userId;
    private BookingStatus bookingStatus;
    Map<String,Booking>  showBookings;

    public Booking(@NonNull final String id, final Show show, @NonNull final List<Seat> seats, @NonNull final String userId) {
        this.id = id;
        this.show = show;
        this.seats = seats;
        this.userId = userId;
        this.bookingStatus = BookingStatus.Created;
        showBookings = new HashMap<>();
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException("Invalid state exception");
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking(){
        if(this.bookingStatus != BookingStatus.Created){
            throw new InvalidStateException("Invalid state exception");
        }
        this.bookingStatus = BookingStatus.Expired;
    }


    public  boolean isConfirmed(Seat seat) {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public boolean isSeatBooked(Seat seat) {
        return this.seats.contains(seat);
    }
}
