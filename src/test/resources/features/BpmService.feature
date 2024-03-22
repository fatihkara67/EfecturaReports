@regression
Feature: BPM Service

  Background:
#    Given The user login elastic

  Scenario: Look for errors
    Given The user login elastic
    Given The user login elastic service
#    Given The user navigates to "<service>"
#    Given Verify service
    Given user go to mail
    When  user login email
    When user click compose button
    When user enters mail credentials
    Then user clicks send button

#    Examples:
#      | service     |
#      | bpm         |
#      | itemService |
#      | otpService  |
#      | dbConnector |
#      | fletumApi   |

  Scenario: mail
    Given user go to mail
    When  user login email
    When user click compose button
    When user enters mail credentials
    Then user clicks send button

