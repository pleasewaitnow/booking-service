package com.statista.code.challenge;
import com.statista.code.challenge.model.Booking;
import com.statista.code.challenge.service.BookingService;
import com.statista.code.challenge.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * this is our API endpoints
 */

@Controller
@RestController
@RequestMapping("/bookingservice")
public class FooBarController {

    private BookingService bookingService;
    private EmailService emailService;


    @Autowired
    public FooBarController(BookingService bookingService, EmailService emailService)
    {
        this.bookingService = bookingService;
        this.emailService = emailService;
    }

    @PostMapping("/bookings")
    public ResponseEntity createBooking(@RequestBody Booking booking) {
        bookingService.save(booking);
        emailService.send(booking.getEmail());

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    @PutMapping("/bookings/{booking_id}")
    public ResponseEntity updateBooking(@PathVariable int booking_id, @RequestBody Booking requestBooking) {
        requestBooking.setBooking_id(booking_id);
        Optional<Booking> booking = bookingService.getBookingById(booking_id);

        if (! booking.isPresent()) {
            return this.createBooking(requestBooking);
        }

        bookingService.update(booking_id, requestBooking);

        return this.getBookingById(booking_id);
    }
    @GetMapping("/bookings/{booking_id}")
    public ResponseEntity getBookingById(@PathVariable int booking_id) {
        Optional<Booking> booking = bookingService.getBookingById(booking_id);

        if (! booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/bookings/department/{department}")
    public ResponseEntity getDepartmentBookingsList(@PathVariable String department) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/bookings/currencies")
    public ResponseEntity getBookingsCurrencies() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/sum/{currency}")
    public ResponseEntity getBookingsCurrencySum(@PathVariable String currency) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/bookings/dobusiness/{booking_id}")
    public ResponseEntity getBookingDoBusiness(@PathVariable String booking_id) {
        return ResponseEntity.ok().build();
    }
}