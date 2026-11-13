package com.example.spring.spring_rest_security.entity;

public class StudentOld {
    private String firstname;
    private String lastName;

    public StudentOld() {
    }

    public StudentOld(String firstname, String lastName) {
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
