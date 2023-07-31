package com.statista.code.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
    private static final AtomicInteger bookingIdCounter = new AtomicInteger(1);

    @JsonProperty("booking_id")
    private int booking_id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private double price;

    @JsonProperty("currency")
    private CurrencyType currency; // @todo switch to java.util.Currency

    @JsonProperty("subscription_start_date")
    private Timestamp subscription_start_date;
    @JsonProperty("email")
    private String email;

    @JsonProperty("department")
    private Department department;

    @JsonProperty("storage_path")
    private String storage_path;

    public enum CurrencyType {
        USD, GBP, JPY, EUR, RUB
    }


    public Booking() {}

    public Booking(int booking_id, String description, double price, CurrencyType currency,
                   Timestamp subscription_start_date, String email, Department department, String storage_path) {
        this.booking_id = booking_id;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.subscription_start_date = subscription_start_date;
        this.email = email;
        this.department = department;
        this.storage_path = storage_path;
    }

    public int getBooking_id() {
        if (booking_id ==0) {
            booking_id = bookingIdCounter.getAndIncrement();
        }

        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    @JsonProperty("subscription_start_date")
    public long getSubscriptionStartDateTimestamp() {
        return subscription_start_date.getTime() / 1000; // Convert to seconds (Unix timestamp)
    }

    public void setSubscription_start_date(Timestamp subscription_start_date) {
        this.subscription_start_date = subscription_start_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public Department getDepartmentObj() {
        return department;
    }

    public String getDepartment() {
        return department.departmentName;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getStorage_path() {
        return storage_path;
    }

    public void setStorage_path(String storage_path) {
        this.storage_path = storage_path;
    }
}
