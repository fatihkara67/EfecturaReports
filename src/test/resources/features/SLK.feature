Feature: SLK Errors

  @regression
  Scenario: Look for errors
    Given The user login new relic
#    Given The user login SLK new relic page
    Given The user looks SLK log errors
    Given The user login SLK new relic for restart
    Given The user sends telegram sms for slk
#    Given user login to outlook
#    Given user sends the mail
#    Given user send group mail for slk
#    Given user log out the mail