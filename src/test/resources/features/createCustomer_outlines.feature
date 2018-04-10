@regresssuite
Feature: createCustomer

  @outline
  Scenario Outline: Server is receiving the request to createCustomer
    When Server is receiving request to createCustomer with <id>,<lastName>,<firstName>,<age>,<isActive> and <dateOfBirth>
    Then service is returning response with code <responseCode> and Id <id> status message <statusMessage>
    Examples:
      | id | lastName  | firstName | age | isActive | dateOfBirth | responseCode | statusMessage                                     |
      | 1  | Capaldi   | Peter     | 4   | false    | 2012-05-31  | 201          | successfully created                              |
      | 2  | Whittaker | Jodie     |     |          |             | 201          | successfully created                              |
      | 3  | Whittaker |           | 3   | true     | 2017-12-25  | 401          | mandatory fields are blank or have invalid format                            |
      | 4  |           | Jodie     | 3   | true     | 2017-12-25  | 401          | mandatory fields are blank or have invalid format |
      | 5  | Whittaker |           | 3   | true     | 2017-12-25  | 401          | mandatory fields are blank or have invalid format |
      |    | Whittaker | Jodie     | 3   | true     | 2017-12-25  | 401          | mandatory fields are blank or have invalid format |
      |    |           |           | 3   | true     | 2017-12-25  | 401          | mandatory fields are blank or have invalid format |


