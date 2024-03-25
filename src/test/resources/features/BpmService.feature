
Feature: BPM Service

  @regression
  Scenario: Look for errors
    Given The user login elastic
    Given The user login elastic service
    Given user go to mail
    When  user login email
    When user click compose button
    When user enters mail credentials
    Then user clicks send button


