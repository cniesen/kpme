
Feature: View Portal Page

  As a variety of users, I should be able to view the portal page so that I can see clickable actions and have the ability to access my action list.

  Background:
    Given I attempt to view the KHR portal page

  Scenario: As a non-logged in user, I should be able to see a portal group named Transactions
    Then I should be able to see a portal group named "Transactions"
