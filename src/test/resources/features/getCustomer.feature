@regresssuite

Feature: Getting a customer

  @positive
  Scenario: Getting an existing customer
    When a user retrieves the user by id 1
    And returned full information of the customer with id 1
    Then the status code is for customer with id 1 returned the status 200

  @negative
  Scenario: Getting a non existent customer
    When a user retrieves the user by id 2
    Then the status code is for customer with id 2 returned the status 404

