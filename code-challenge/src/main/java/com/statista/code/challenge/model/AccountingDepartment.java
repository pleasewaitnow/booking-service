package com.statista.code.challenge.model;

public class AccountingDepartment extends Department {
    public AccountingDepartment() {
        super("Accounting Department");
    }

    @Override
    public void doBusiness() {
        System.out.println("Handling financial transactions and records.");
    }
}