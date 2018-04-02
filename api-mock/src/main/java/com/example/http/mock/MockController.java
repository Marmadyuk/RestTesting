package com.example.http.mock;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/customer")
public class MockController {

    public static class CreateResponse {
        private final int id;
        private final String status;

        public CreateResponse(int id, String status) {
            this.id = id;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }
    }

    private MockEngine mockEngine = new MockEngine();

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Integer id) {
        Customer customer = mockEngine.getCustomer(id);
        if (customer == null) {
            return ResponseEntity.status(404).body("no customer with given id");
        }
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (!valid(customer)) {
            return  ResponseEntity.status(401).body("mandatory fields are blank or have invalid format");
        } else {
            mockEngine.createCustomer(customer);
            return ResponseEntity.status(201)
                    .body(new CreateResponse(customer.getId(), "successfully created"));
        }
    }

    private boolean valid(Customer customer) {
        return customer.getId() != null && customer.getFirstName() != null && customer.getLastName() != null;
    }

}
