package com.statista.code.challenge.model;

public class Department {
    public String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Business doBusiness() {
        System.out.println("business operation for " + departmentName);
        return null;
    }
}