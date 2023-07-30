package com.statista.code.challenge.model;

public class SalesDepartment extends Department {
    public SalesDepartment() {
        super("Sales Department");
    }

    @Override
    public void doBusiness() {
        System.out.println("Selling products and services.");
    }
}