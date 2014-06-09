@wip

Feature: Validate date field in time blocks

  Background:
    Given I am logged in as indiana non-exempt timekeeping employee

  Scenario: Verify blank end date cannot be added
    When I add a time block with blank end date
    Then blank end date error message should be displayed

  Scenario: Verify blank start date cannot be added
    When I add a time block with blank start date
    Then blank start date error message should be displayed

  Scenario: Verify blank start and end date cannot be added
    When I add a time block with blank start and end date
    Then blank dates error message should be displayed

  Scenario: Verify start date later than end date cannot be added
    When I add a time block with start date later than end date
    Then start date later error message should be displayed