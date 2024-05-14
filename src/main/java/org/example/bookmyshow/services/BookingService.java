package org.example.bookmyshow.services;

import lombok.Data;
import lombok.NonNull;
import org.example.bookmyshow.exceptions.NotFoundExceptions;
import org.example.bookmyshow.model.Booking;
import org.example.bookmyshow.model.BookingStatus;
import org.example.bookmyshow.model.Seat;
import org.example.bookmyshow.model.Show;
import org.example.bookmyshow.providers.SeatLockProvider;
import org.springframework.scheduling.annotation.Scheduled;

import javax.net.ssl.SSLSession;
import java.awt.print.Book;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Data
public class BookingService {
    private final Map<String, Booking> showBookings;
    private final SeatLockProvider seatLockProvider;
    private final ScheduledExecutorService executorService;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.executorService = Executors.newScheduledThreadPool(1);
        this.showBookings = new HashMap<>();
    }

    public Booking createBooking(String userId, Show show, List<Seat> seats) {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show.getShowId() , seats, userId);
        showBookings.put(bookingId, booking);

        executorService.schedule(()->{
            if(!booking.isConfirmed()){
                seatLockProvider.unlockSeats(show,booking.getSeats(),userId);
                showBookings.remove(bookingId);
            }
        },5, TimeUnit.MINUTES);

        return booking;
    }

    public Booking getBooking(@NonNull final String bookingId) {
        if(!showBookings.containsKey(bookingId)){
            throw new NotFoundExceptions("Booking not found");
        }
        return showBookings.get(bookingId);
    }

    public List<Seat> getBookedSeats(Show show) {
       return getAllBookings(show).stream().filter(Booking::isConfirmed).map(Booking::getSeatsBooked).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private List<Seat> getAllBookings(Show show) {
        List<Seat> response = new ArrayList<>();
        for(Booking booking : showBookings.values()) {
            if (booking.getShow().equals(show.getShowId())) {
                response.addAll(booking.getSeats());
            }
        }
        return response;
    }

    public void confirmBooking(Booking booking, String user) {
        if(!booking.getUserId().equals(user)){
            throw new NotFoundExceptions("User not found");
        }
        for(Seat seat : booking.getSeats()){
            if(!seatLockProvider.validateLock(booking.getShow(),seat,user)){
                throw new NotFoundExceptions("Seat not found");
            }
        }
    }
}
