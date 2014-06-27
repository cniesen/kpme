Feature: Validate earn code summary in time blocks

  Background:
    Given I am logged in as earn code security employee

  Scenario: Verify same assignment with multiple earn codes can be added
    When I add time blocks with different earn code
    Then time blocks should display based on time entry

  Scenario: Verify multiple assignment with multiple earn codes can be added
    When I add time blocks with different assignments with earn code
    Then the different assignment time blocks should display based on time entry
