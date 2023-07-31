package com.statista.code.challenge.service;

import com.statista.code.challenge.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private Map<Integer, Booking> bookingByIdMap; // Key: booking_id

    List<Booking.CurrencyType> usedCurrencies = new ArrayList<>();

    private Map<String, List<Booking>> bookingsByDepartmentMap; // Key: Department



    public BookingService() {
        bookingByIdMap = new HashMap<>();
        bookingsByDepartmentMap = new HashMap<>();

        // todo: make this part more resilient
        bookingsByDepartmentMap.put("Accounting Department", new ArrayList<>());
        bookingsByDepartmentMap.put("Sales Department", new ArrayList<>());
        bookingsByDepartmentMap.put("People Department", new ArrayList<>());
    }

    // Method to get a booking by booking_id
    public Optional<Booking> get(int bookingId) {
        return Optional.ofNullable(bookingByIdMap.get(bookingId));
    }

    // Method to get all bookings related to a department name
    public List<Booking> getBookingsByDepartmentName(String department) {
        if (! bookingsByDepartmentMap.containsKey(department)) {
            return Collections.emptyList();
        }

        return bookingsByDepartmentMap.get(department);
    }

    /**
     * saves booking into all bookings, saves the usage of currency and saves the records related to a department
     */
    public void save(int bookingId, Booking booking) {
        bookingByIdMap.put(bookingId, booking);
        usedCurrencies.add(booking.getCurrency());

        Department department = booking.getDepartmentObj();
        List<Booking> departmentBookings = bookingsByDepartmentMap.get(department.departmentName);
        departmentBookings.add(booking);
    }

    /**
     * deletes the department binding, remove unused currency, and calls the save function to establish new bindings
     */
    public void update(int bookingId, Booking requestBooking) {
        Booking oldBooking = bookingByIdMap.get(bookingId);

        Department oldDepartment = oldBooking.getDepartmentObj();
        List<Booking> departmentBookings = bookingsByDepartmentMap.get(oldDepartment.departmentName);
        departmentBookings.remove(oldBooking);

        usedCurrencies.remove(oldBooking.getCurrency());

        save(bookingId, requestBooking);
    }

    public Set<Booking.CurrencyType> getBookingCurrencies() {
        return new HashSet<>(usedCurrencies);
    }

    public double getSumPrice(Booking.CurrencyType currency) {
        return bookingByIdMap.values().stream()
                .filter(booking -> currency.equals(booking.getCurrency()))
                .mapToDouble(Booking::getPrice)
                .sum();
    }
}