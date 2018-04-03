@regresssuite
Feature: createCustomer

  @positive
  Scenario: Server is request response to createCustomer with all attributes
    When Server is receiving request to createCustomer
      | id | lastName | firstName | age | isActive | dateOfBirth |
      | 1  | Capaldi  | Peter     | 4   | false    | 2012-05-31  |

    Then service is returning response with code 201 and Id 1 status message "successfully created"

  @positive
  Scenario: Server is receiving request to createCustomer just with mandatory attributes

    When Server is receiving request to createCustomer
      | id | lastName  | firstName | age | active | dateOfBirth |
      | 2  | Whittaker | Jodie     |     |        |             |

    Then service is returning response with code 201 and Id 2 status message "successfully created"


  @negative
  Scenario: Server is receiving request to createCustomer just without one mandatory attribute

    When Server is receiving request to createCustomer
      | id | lastName  | firstName | age | isActive | dateOfBirth |
      | 3  | Whittaker |           | 3   | true     | 2017-12-25  |

    Then service is returning response with code 401 and status message "mandatory fields are blank or have invalid format"


  @negative
  Scenario: Server is receiving request to createCustomer just without one mandatory attribute

    When Server is receiving request to createCustomer
      | id | lastName | firstName | age | isActive | dateOfBirth |
      | 4  |          | Jodie     | 3   | true     | 2017-12-25  |

    Then service is returning response with code 401 and status message "mandatory fields are blank or have invalid format"

  @negative
  Scenario: Server is receiving reques to createCustomer just without one mandatory attribute

    When Server is receiving request to createCustomer
      | id | lastName  | firstName | age | isActive | dateOfBirth |
      | 5  | Whittaker |           | 3   | true     | 2017-12-25  |

    Then service is returning response with code 401 and status message "mandatory fields are blank or have invalid format"

  @negative
  Scenario: Server is receiving request to createCustomer just without one mandatory attribute

    When Server is receiving request to createCustomer
      | id | lastName  | firstName | age | isActive | dateOfBirth |
      |    | Whittaker | Jodie     | 3   | true     | 2017-12-25  |
#  @negative
#  Scenario: Server is receiving response to createCustomer just without one mandatory attribute
#
#    When Server is receiving request to createCustomer
#      | id | lastName  | firstName | age | isActive | dateOfBirth |
#      |  sdfsdf  | Whittaker | Jodie     | 3   | true     | 2017-12-25  |

  @negative
  Scenario: Server is receiving request to createCustomer just without all mandatory attribute

    When Server is receiving request to createCustomer
      | id | lastName | firstName | age | isActive | dateOfBirth |
      |    |          |           | 3   | true     | 2017-12-25  |

    Then service is returning response with code 401 and status message "mandatory fields are blank or have invalid format"
