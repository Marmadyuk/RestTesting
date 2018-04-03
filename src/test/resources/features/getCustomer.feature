@regresssuite

Feature: Getting a customer

  @positive
  Scenario: Getting an existing customer
    When a user retrieves the customer by id 1
    Then the status code is for customer with id 1 returned the status 200
    And returned full information of the customer with id 1 and args
      | id | lastName | firstName | age | isActive | dateOfBirth |
      | 1  | Capaldi  | Peter     | 4   | false    | 2012-05-31  |

  @negative
  Scenario: Getting a non existent customer
    When a user retrieves the customer by id 5000
    Then the status code is for customer with id 5000 returned the status 404

