@wip

Feature: Validate overnight shifts in time block

  Background:
    Given I am logged in as indiana non-exempt timekeeping employee

    Scenario: Validate overnight time blocks cannot be added daily
      When I add nightly time block applied for each day
      Then the time block should not be added

    Scenario: Validate overnight time blocks can be added
      When I add nightly time block not applied for each day
      Then the time block should be added

    Scenario: Validate overnight time blocks can be added individually
      When I add nightly time block individually for days
      Then the time block should be added for each day

    Scenario: Validate multiple day and night time blocks can be added
      When I add regular earn codes over multiple days
      Then the time block should not be added
      And un apply the time block for each day
      Then the time block should be added for every day

  Scenario: Validate multiple day time blocks can be added
    When I add day time blocks over different days
    Then blocks with exact time values should be added

