package org.example.bookmyshow.services;

import org.example.bookmyshow.exceptions.NotFoundExceptions;
import org.example.bookmyshow.model.Booking;
import org.example.bookmyshow.providers.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    Map<Booking,Integer> bookingFailures;
    private final Integer allowedRetries;
    private final SeatLockProvider seatLockProvider;

    PaymentService(Integer allowedRetries, SeatLockProvider seatLockProvider){
        this.allowedRetries = allowedRetries;
        this.seatLockProvider = seatLockProvider;
        bookingFailures = new HashMap<>();
    }
    public void processPaymentFailed(Booking booking, String user) {
        if(!booking.getUserId().equals(user)){
            throw new NotFoundExceptions("Booking not found");
        }

        if(!bookingFailures.containsKey(booking)){
            bookingFailures.put(booking,0);
        }

        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking,newFailuresCount);
        if(newFailuresCount>allowedRetries){

            seatLockProvider.unlockSeats(booking.getShow(),booking.getSeats(),booking.getUserId());
        }
    }
}
