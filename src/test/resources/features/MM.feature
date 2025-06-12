Feature: MM Log Errors

  @regressionx
  Scenario: Look for errors
    Given The user login elastic
    Given The user look elastic service for mm
    Given The user sends telegram sms for mm
#    Given user login to outlook
#    Given The user sends email for mm
#    Given The user sends group email for mm
#    Given user log out the mail