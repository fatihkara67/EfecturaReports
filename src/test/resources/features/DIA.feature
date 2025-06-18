
Feature: DIA Environment Controls

  @regression
  Scenario: Look for errors
    Given The user login elastic
    Given The user login elastic service
    Given The user login Fletum
    Given The user open BPM page
    Given The user write "MODUL" in search all filter
    Given The user take the modul count
    Given The user write "MENU" in search all filter
    Given The user take the menu count
    Given The user login tedarikci screen
    Given Wait tedarik table
    Given The user gets Advice Count
    Given The user gets oneri siparis date
    Given The user gets OneriSiparis_Results
#    Given The user get log count
    Given The user sends telegram sms for dia




#    Given user login to outlook
#    Given The user sends email for dia
#    Given The user sends group email for dia
#    Given user log out the mail

#    Given The user impersonate Beyza
#    Given The user gets flow counts


