package com.statista.code.challenge.model;

import java.time.LocalDateTime;

public class PeopleDepartment extends Department {
    private static PeopleDepartment instance;

    public PeopleDepartment() {
        super("People Department");
    }

    public static PeopleDepartment getInstance() {
        if (instance == null) {
            instance = new PeopleDepartment();
        }
        return instance;
    }

    @Override
    public Business doBusiness() {
        super.doBusiness();

        System.out.println("Managing HR-related activities.");

        return new Business(LocalDateTime.now(), this.departmentName);
    }
}