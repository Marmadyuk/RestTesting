@regresssuite
Feature: createCustomer

  @positive
  Scenario: Server is receiving response to createCustomer with all attributes
    When Server is receiving request to createCustomer
      | id | lastName | firstName | age | isActive | dateOfBirth |
      | 1  | Capaldi  | Peter     | 4   | false    | 2012-05-31  |

    Then service is returning response with code 201 and status message "successfully created"

  @positive
  Scenario: Server is receiving response to createCustomer just with mandatory attributes

    When Server is receiving request to createCustomer
      | id | lastName  | firstName | age | active | dateOfBirth | status |
      | 3  | Whittaker | Jodie     |     |        |             | 200    |

    Then service is returning response with code 201 and status message "successfully created"

  @negative
  Scenario: Server is receiving response to createCustomer just without one mandatory attribute

    When Server is receiving request to createCustomer
      | id | lastName  | firstName | age      | isActive | dateOfBirth      |
      | 3  | Whittaker |           | 3 months | true     | 25 December 2017 |

    Then service is returning response with code 400 and status message "mandatory fields are blank or have invalid format"





#  Scenario Outline: trying to recieve unavailable Id of customer
#    When service is receiving request to get Customer with "id"
#    And "id" dfsdf
#    Examples:
#      | id | lastName  | firstName | age     | active | dateOfBirth  | status |
#      | 1  | Capaldi   | Peter     | 4 years | false  | 18 June 2005 | 200    |
#      | 2  |           |           |         |        |              | 404    |
#      | 3  | Whittaker | Jodie     |         |        |              | 200    |


#  Scenario Outline: receiving response to createCustomer Positive Scenario
#    When service receiving request CreateCustomer with LastName: "<lastName>", FirstName "<firstName>",Age: "<age>", Status "<active>" and DateOfBirth "<dateOfBirth>"
#    Then service system sent the response with "<status>"
#    And service
#    Examples:
#      | lastName  | firstName | age      | active | dateOfBirth      | status |
#      | Capaldi   | Peter     | 4 years  | false  | 18 June 2005     | 201    |
#      |           | David     | 4 years  | false  | 25 December 2013 | 401    |
#      | Whittaker |           | 3 months | true   | 25 December 2017 | 401    |
#      | Tennant   | David     |          |        |                  | 201    |
#

