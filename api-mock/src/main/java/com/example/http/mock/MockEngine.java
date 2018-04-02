package com.example.http.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockEngine {

    private Map<Integer, Customer> customers = new ConcurrentHashMap<>();

    public void createCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomer(int id) {
        return customers.get(id);
    }

}
