@regresssuite

Feature: Getting a customer

  Scenario: Getting an existing customer
    Given customers are available

      | id | lastName  | firstName | age      | active | dateOfBirth      |
      | 1  | Capaldi   | Peter     | 4 years  | false  | 18 June 2005     |
      | 2  | Tennant   | David     |          | false  | 25 December 2013 |
      | 3  | Whittaker | Jodie     | 3 months |        | 25 December 2017 |
  When

#  Scenario: Getting an existing customer
#    Given a customer exists with id 24
#    When a user retrieves the user by id 400
#    Then the status code is 404

#Feature: GetCustomer
#
#  Scenario Outline: recieving response to getCustomer
#
#    When service receiving request GetCustomer by Id:"<id>"
#    Then service returns response for customer by Id:"<id>"
#    And Last Name "<lastName>",
#    And First Name"<firstName>",
#    And Age "<age>",
#    And Status"<active>"
#    And Date of Birth"<dateOfBirth>"
#    And the Response status "<status>"
#    Examples:
#      | id | lastName  | firstName | age      | active | dateOfBirth      | status |
#      | 1  | Capaldi   | Peter     | 4 years  | false  | 18 June 2005     | 200    |
#      | 2  | Tennant   | David     |          | false  | 25 December 2013 | 200    |
#      | 3  | Whittaker | Jodie     | 3 months |        | 25 December 2017 | 200    |
#      | 4  |           |           |          |        |                  | 404    |
#
#
##  Scenario Outline: trying to recieve unavailable Id of customer
##    When service is receiving request to get Customer with "id"
##    And "id" dfsdf
##    Examples:
##      | id | lastName  | firstName | age     | active | dateOfBirth  | status |
##      | 1  | Capaldi   | Peter     | 4 years | false  | 18 June 2005 | 200    |
##      | 2  |           |           |         |        |              | 404    |
##      | 3  | Whittaker | Jodie     |         |        |              | 200    |
#
#
##  Scenario Outline: receiving response to createCustomer Positive Scenario
##    When service receiving request CreateCustomer with LastName: "<lastName>", FirstName "<firstName>",Age: "<age>", Status "<active>" and DateOfBirth "<dateOfBirth>"
##    Then service system sent the response with "<status>"
##    And service
##    Examples:
##      | lastName  | firstName | age      | active | dateOfBirth      | status |
##      | Capaldi   | Peter     | 4 years  | false  | 18 June 2005     | 201    |
##      |           | David     | 4 years  | false  | 25 December 2013 | 401    |
##      | Whittaker |           | 3 months | true   | 25 December 2017 | 401    |
##      | Tennant   | David     |          |        |                  | 201    |
##
##
