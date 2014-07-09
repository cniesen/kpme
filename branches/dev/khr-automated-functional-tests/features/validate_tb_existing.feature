@wip

Feature: Validate overlapping time blocks

  Background:
    Given I am logged in as earn code security employee

   Scenario: Validate the values of a created time block
     When I add a regular earn code time block
     Then I should see correct values in the time block entry

   Scenario: Validate values in existing time block can be edited
     Given I have a time block created
     When I edit time values of the time block
     Then I should see latest values in summary and time block entry

  Scenario: Validate time block values from overnight shift
    Given I have an overnight time block created
    When I edit values of the time block
    Then I should see values in summary and time block entry