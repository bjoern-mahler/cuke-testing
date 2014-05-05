Feature: "Test"

  @1234
  Scenario: "Test data fixtures"
    Given a thing
    When doing something
    And there is one other thing
    Then everything is fine

  @1235
  Scenario: "Test a usecase"
    Given a base usecase
    When calling the get method on the usecase
    Then there should be a response map with a key "test" and the value "test value"
    And the response should also be available in another step definition file

