package com.statista.code.challenge.model;

import java.time.LocalDateTime;

public class AccountingDepartment extends Department {
    public AccountingDepartment() {
        super("Accounting Department");
    }

    @Override
    public Business doBusiness() {
        super.doBusiness();

        System.out.println("Handling financial transactions and records.");

        return new Business(LocalDateTime.now(), this.departmentName);
    }
}