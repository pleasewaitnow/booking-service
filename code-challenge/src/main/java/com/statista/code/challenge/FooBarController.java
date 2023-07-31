package com.statista.code.challenge;
import com.statista.code.challenge.model.Booking;
import com.statista.code.challenge.model.Business;
import com.statista.code.challenge.service.BookingService;
import com.statista.code.challenge.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @apiNote all of our API endpoints are located here
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
        bookingService.save(booking.getBooking_id(), booking);
        emailService.send(booking.getEmail());

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    @PutMapping("/bookings/{booking_id}")
    public ResponseEntity updateBooking(@PathVariable int booking_id, @RequestBody Booking requestBooking) {
        requestBooking.setBooking_id(booking_id);
        Optional<Booking> booking = bookingService.get(booking_id);

        if (! booking.isPresent()) {
            return this.createBooking(requestBooking);
        }

        bookingService.update(booking_id, requestBooking);

        return this.getBookingById(booking_id);
    }
    @GetMapping("/bookings/{booking_id}")
    public ResponseEntity getBookingById(@PathVariable int booking_id) {
        Optional<Booking> booking = bookingService.get(booking_id);

        if (! booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    @GetMapping("/bookings/department/{department}")
    public ResponseEntity getDepartmentBookingsList(@PathVariable String department) {


        return new ResponseEntity<>(bookingService.getBookingsByDepartmentName(department), HttpStatus.OK);
    }
    @GetMapping("/bookings/currencies")
    public ResponseEntity getBookingsCurrencies() {
        return new ResponseEntity<>(bookingService.getBookingCurrencies(), HttpStatus.OK);
    }
    @GetMapping("/sum/{currency}")
    public ResponseEntity getBookingsCurrencySum(@PathVariable Booking.CurrencyType currency) {
        return new ResponseEntity<>(bookingService.getSumPrice(currency), HttpStatus.OK);
    }
    @GetMapping("/bookings/dobusiness/{booking_id}")
    public ResponseEntity<Business> getBookingDoBusiness(@PathVariable int booking_id) {
        Optional<Booking> booking = bookingService.get(booking_id);

        if (! booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Business businessOutCome = booking.get().getDepartment().doBusiness();

        return new ResponseEntity<>(businessOutCome, HttpStatus.OK);
    }
}