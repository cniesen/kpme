@draft

  Feature: Validate hours field in time blocks

    Background:
      Given I am logged in as employee2
      And the assignment type is EC work area
      And earn code is EC1

    Scenario: Verify blank hours field cannot be added
      When I add a blank hours field
      Then blank hours field error must be displayed

    Scenario: Verify hours cannot extend the limit
      When I add hours more than the limit
      Then hours limit error must be displayed

    Scenario: Verify valid time block can be added
      When I add time block with hours filled
      Then valid time block entry must display in calendar