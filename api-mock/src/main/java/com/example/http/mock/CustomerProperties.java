package com.example.http.mock;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CustomerProperties {

    private Integer age;
    private boolean active;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;


    public Integer getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
