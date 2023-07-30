package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Booking;
import com.statista.code.challenge.model.Department;
import com.statista.code.challenge.model.SalesDepartment;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class BookingService {
    private Map<Integer, Booking> bookingByIdMap; // Key: booking_id
    private Map<Department, List<Booking>> bookingsByDepartmentMap; // Key: Department

    public BookingService() {
        bookingByIdMap = new HashMap<>();
        bookingsByDepartmentMap = new HashMap<>();


        // @todo delete this fake booking
        Booking fakeOne = new Booking(1, "fake one", 0, Booking.CurrencyType.USD, Timestamp.from(Instant.now()), "fake@fake.com", new SalesDepartment(), "/");
        bookingByIdMap.put(1, fakeOne);
    }

    // Method to get a booking by booking_id
    public Optional<Booking> getBookingById(int bookingId) {
        return Optional.ofNullable(bookingByIdMap.get(bookingId));
    }


    // Method to save a booking
    public void save(Booking booking) {
        int bookingId = booking.getBooking_id();

        // Save by booking_id
        bookingByIdMap.put(bookingId, booking);

//        // Save by department
//        Department department = booking.getDepartment();
//        if (!bookingsByDepartmentMap.containsKey(department)) {
//            bookingsByDepartmentMap.put(department, new ArrayList<>());
//        }
//        List<Booking> departmentBookings = bookingsByDepartmentMap.get(department);
//        departmentBookings.add(booking);
    }

    // Method to get all bookings related to a department
    public List<Booking> getBookingsByDepartment(Department department) {
        if (bookingsByDepartmentMap.containsKey(department)) {
            return bookingsByDepartmentMap.get(department);
        } else {
            return Collections.emptyList();
        }
    }

    public void update(int bookingId, Booking requestBooking) {
        bookingByIdMap.put(bookingId, requestBooking);
    }
}