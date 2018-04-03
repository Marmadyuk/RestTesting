package com.example.resttesting;

public class Customer {
  private Integer id;

  private String first_name;

  private String last_name;
  private CustomerProperties properties;

  public Customer(Integer id, String first_name, String lastName, CustomerProperties properties) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = lastName;
    this.properties = properties;


  }

}
