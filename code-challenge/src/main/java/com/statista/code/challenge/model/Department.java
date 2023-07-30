package com.statista.code.challenge.model;

public class Department {
    public String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public void doBusiness() {
        System.out.println("Generic business operation for " + departmentName);
    }
}