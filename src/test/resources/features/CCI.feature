Feature: CCI

  @regression
  Scenario: Elastic Errors, HealthCheck And DB Module Control Control
    Given The user login CCI elastic service
    Given The user login CCI elastic page
    Given user go to mail
    When  user login email
    When user click compose button
    When user enters mail credentials for cci
    Then user clicks send button