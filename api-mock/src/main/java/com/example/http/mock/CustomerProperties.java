package com.example.http.mock;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CustomerProperties {

    private Integer age;
    private boolean active;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;


    public Integer getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
