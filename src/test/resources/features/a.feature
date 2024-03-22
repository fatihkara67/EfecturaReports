Feature: a

  Scenario: mail
    Given user go to mail
    When  user login email
    When user click compose button
    When user enters mail credentials
    Then user clicks send button