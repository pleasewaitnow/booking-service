package com.statista.code.challenge.model;

import java.time.LocalDateTime;

public class SalesDepartment extends Department {
    public SalesDepartment() {
        super("Sales Department");
    }

    @Override
    public Business doBusiness() {
        super.doBusiness();

        System.out.println("Selling products and services.");

        return new Business(LocalDateTime.now(), this.departmentName);
    }
}