package com.example.http.mock;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    private final Integer id;

    @JsonProperty("first_name")
    private final String firstName;

    @JsonProperty("last_name")
    private final String lastName;

    private final  CustomerProperties properties;

    @JsonCreator
    public Customer(@JsonProperty("id") Integer id, @JsonProperty("first_name")String firstName,@JsonProperty("last_name") String lastName,
                    @JsonProperty("properties")CustomerProperties properties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.properties = properties;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerProperties getProperties() {
        return properties;
    }

}
