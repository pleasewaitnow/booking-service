package com.statista.code.challenge.model;

import java.time.LocalDateTime;

public class AccountingDepartment extends Department {
    private static AccountingDepartment instance;

    public AccountingDepartment() {
        super("Accounting Department");
    }

    public static AccountingDepartment getInstance() {
        if (instance == null) {
            instance = new AccountingDepartment();
        }
        return instance;
    }

    @Override
    public Business doBusiness() {
        super.doBusiness();

        System.out.println("Handling financial transactions and records.");

        return new Business(LocalDateTime.now(), this.departmentName);
    }
}