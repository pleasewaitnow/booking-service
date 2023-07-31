package com.statista.code.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statista.code.challenge.model.Booking;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FooBarControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBookingById() throws Exception {
        int bookingId = 121;

        testUpdateBooking();

        mockMvc.perform(get("/bookingservice/bookings/{bookingId}", bookingId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBookingByIdNotFound() throws Exception {
        mockMvc.perform(get("/bookingservice/bookingservice/bookings/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateBooking() throws Exception {
        int bookingId = 121;
        Booking fakeOne = new Booking(1, "fake one", 0, Booking.CurrencyType.USD, Timestamp.from(Instant.now()), "fake@fake.com", "/");
        fakeOne.setDepartment_name("Sales Department");

        ObjectMapper mapper = new ObjectMapper();
        String bookingJson = mapper.writeValueAsString(fakeOne);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/bookingservice/bookings/{bookingId}", bookingId)
                        .content(bookingJson) // Set the request body with the updated Booking JSON
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}