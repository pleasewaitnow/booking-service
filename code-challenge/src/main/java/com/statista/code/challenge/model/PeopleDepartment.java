package com.statista.code.challenge.model;

import java.time.LocalDateTime;

public class PeopleDepartment extends Department {
    public PeopleDepartment() {
        super("People Department");
    }

    @Override
    public Business doBusiness() {
        super.doBusiness();

        System.out.println("Managing HR-related activities.");

        return new Business(LocalDateTime.now(), this.departmentName);
    }
}