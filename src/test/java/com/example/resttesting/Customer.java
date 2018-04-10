package com.example.resttesting;

import java.util.Objects;

public class Customer {
  public Integer id;

  public String first_name;

  public String last_name;
  public CustomerProperties properties;

  public Customer(Integer id, String first_name, String last_name, CustomerProperties properties) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.properties = properties;


  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) &&
        Objects.equals(first_name, customer.first_name) &&
        Objects.equals(last_name, customer.last_name) &&
        Objects.equals(properties, customer.properties);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, first_name, last_name, properties);
  }

  public CustomerProperties getProperties() {
    return properties;
  }

}
