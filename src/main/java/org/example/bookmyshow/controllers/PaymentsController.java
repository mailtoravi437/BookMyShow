package org.example.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.bookmyshow.services.BookingService;
import org.example.bookmyshow.services.PaymentService;

@AllArgsConstructor
public class PaymentsController {
    private final PaymentService paymentService;
    private final BookingService bookingService;


    public void paymentFailed(@NonNull final String bookingId,@NonNull final String user){
       paymentService.processPaymentFailed(bookingService.getBooking(bookingId) ,user);
    }

    public void paymentSuccess(@NonNull final String bookingId,@NonNull final String user){
        bookingService.confirmBooking(bookingService.getBooking(bookingId),user);
    }


}
