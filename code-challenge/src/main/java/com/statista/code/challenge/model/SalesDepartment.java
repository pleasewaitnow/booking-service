package com.statista.code.challenge.model;

import java.time.LocalDateTime;

public class SalesDepartment extends Department {
    private static SalesDepartment instance;

    public SalesDepartment() {
        super("Sales Department");
    }

    public static SalesDepartment getInstance() {
        if (instance == null) {
            instance = new SalesDepartment();
        }
        return instance;
    }

    @Override
    public Business doBusiness() {
        super.doBusiness();

        System.out.println("Selling products and services.");

        return new Business(LocalDateTime.now(), this.departmentName);
    }
}