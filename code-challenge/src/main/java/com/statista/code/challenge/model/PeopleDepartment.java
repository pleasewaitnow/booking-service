package com.statista.code.challenge.model;

public class PeopleDepartment extends Department {
    public PeopleDepartment() {
        super("People Department");
    }

    @Override
    public void doBusiness() {
        System.out.println("Managing HR-related activities.");
    }
}