Feature: db check

  Scenario: newrelic
    Given The user login new relic
    Given The user check logs


  Scenario: db
    Given Check


  Scenario Outline: sdfs
    And The user send aply gift request for acc "<AccountId>" gift "<giftItemId>" and venue "<venueId>"

    Examples:
      | AccountId | giftItemId | venueId |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |
      | 55555555x | 763665     | 817455  |