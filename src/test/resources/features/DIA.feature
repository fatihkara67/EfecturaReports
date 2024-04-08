
Feature: DIA Environment Controls

  @regression
  Scenario: Look for errors
    Given The user login elastic
    Given The user login elastic service
    Given The user login Fletum
    Given The user open BPM page
    Given The user write "modül" in search all filter
    Given The user take the modul count
    Given The user write "menü" in search all filter
    Given The user take the menu count
    Given The user login tedarikci screen
    Given Wait tedarik table
#    Given user go to mail
#    When  user login email
#    When user click compose button
#    When user enters mail credentials
#    Then user clicks send button
    Given user login to outlook
    Given The user sends email for dia
    Given user log out the mail


