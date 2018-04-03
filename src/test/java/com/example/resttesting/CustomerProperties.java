package com.example.resttesting;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerProperties {
  private Integer age;
  private boolean active;
  @SerializedName("date_of_birth")
  private String dateOfBirth;

  public Integer getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerProperties that = (CustomerProperties) o;
    return isActive() == that.isActive() &&
        Objects.equals(getAge(), that.getAge()) &&
        Objects.equals(getDateOfBirth(), that.getDateOfBirth());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getAge(), isActive(), getDateOfBirth());
  }

  public boolean isActive() {
    return active;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public CustomerProperties(Integer age, boolean active, String dateOfBirth) {
    this.age = age;
    this.active = active;
    this.dateOfBirth = dateOfBirth;

  }
}
