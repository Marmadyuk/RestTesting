package com.example.resttesting;

import java.time.LocalDate;

public class CustomerProperties {
  private Integer age;
  private boolean active;
  private LocalDate dateOfBirth;

  public CustomerProperties(Integer age, boolean active, LocalDate dateOfBirth) {
    this.age = age;
    this.active = active;
    this.dateOfBirth = dateOfBirth;
  }
}
