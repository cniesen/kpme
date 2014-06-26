@wip

Feature: Validate earn code summary in time blocks

  Background:
    Given I am logged in as earn code security employee

    Scenario: Verify summary grid displays totals for regular earn codes
      When I add a time block with regular earn code
      Then summary grid displays total hours for the earn code

    Scenario: Verify summary grid displays totals for the entire week
     When I add a time block for the entire week with regular earn code
      Then summary grid displays total hours for the entire week

  Scenario: Verify summary grid displays totals for security earn code
    When I add a time block with security earn code
    Then summary grid displays the earn code with total hours

  Scenario: Verify summary grid displays week totals for individual earn code
    When I add time blocks with different earn code for the entire week
    Then summary grid displays the total hours for the individual earn code

   Scenario: Verify summary grid displays totals for earn codes with amount
     When I add an amount time block for a day
     Then summary grid displays the amount for the day

  Scenario: Verify summary grid displays totals with amount for the week
    When I add an amount time block for the week
    Then summary grid displays the earn code with amount for the week