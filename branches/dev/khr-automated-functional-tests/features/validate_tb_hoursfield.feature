@wip

  Feature: Validate hours field in time blocks

    Background:
      Given I am logged in as earn code security employee


    Scenario: Verify blank hours field cannot be added
      When I add a blank hours field
      Then blank hours field error must be displayed

    Scenario: Verify hours cannot extend the limit
      When I add hours more than the limit
      Then hours limit error must be displayed

    Scenario: Verify valid time block can be added
      When I add time block with hours
      Then valid time block entry must display in calendar

    Scenario: Verify time blocks can be added over different dates
      When I create a time block for a date range not applied for all dates
      Then the time block entry should appear for each day

    Scenario: Verify time blocks can be added over date range
      When I create a time block for a date range
      Then the time block entry should appear for each day

