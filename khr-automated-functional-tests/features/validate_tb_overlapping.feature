@wip

Feature: Validate overlapping time blocks

  Background:
    Given I am logged in as earn code security employee

  Scenario: Validate overlapping same time blocks cannot be added
    When I add same value for multiple time blocks for a day
    Then overlapping time block error message displays

  Scenario: Validate partly overlapping out time blocks cannot be added
    When I add partly overlapping out time entry for a day
    Then overlapping time block error message displays

  Scenario: Validate partly overlapping in time blocks cannot be added
    When I add partly overlapping in time entry for a day
    Then overlapping time block error message displays

  Scenario: Validate embedding time blocks cannot be done
    When I add time entry values embedded in another time block
    Then overlapping time block error message displays

  Scenario: Validate encapsulating time blocks cannot be done
    When I add time block encapsulating another time entry
    Then overlapping time block error message displays

