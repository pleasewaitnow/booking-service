package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Booking;

import junit.framework.TestCase;

public class BookingServiceTest extends TestCase {

    Booking bookingOne;
    Booking bookingTwo;
    @Override
    public void setUp() throws Exception {
        super.setUp();

        bookingOne = new Booking();
        bookingOne.setBooking_id(1);
        bookingOne.setCurrency(Booking.CurrencyType.USD);
        bookingOne.setDepartment_name("Sales Department");

        bookingTwo = new Booking();
        bookingTwo.setBooking_id(2);
        bookingTwo.setDepartment_name("Sales Department");
        bookingTwo.setCurrency(Booking.CurrencyType.EUR);
    }

    public void testGetBookingCurrencies() {
        BookingService bookingService = new BookingService();
        assertEquals(0, bookingService.getBookingCurrencies().size());

        bookingService.save(bookingOne.getBooking_id(), bookingOne);
        assertEquals(1, bookingService.getBookingCurrencies().size());

        bookingService.save(bookingTwo.getBooking_id(), bookingTwo);
        assertEquals(2, bookingService.getBookingCurrencies().size());
    }
}