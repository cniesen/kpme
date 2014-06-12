@wip

  Feature: Validate amount field in time blocks

    Background:
      Given I am logged in as earn code security employee

    Scenario: Verify amount field cannot be empty
      When I add a time block with empty amount
      Then empty amount error text should be displayed

    Scenario: Verify amount field is only numeric
      When I add a time block with alphanumeric amount
      Then alphanumeric amount error text should be displayed

    Scenario: Verify amount field has valid representation
      When I add a time block with invalid amount
      Then invalid amount error text should be displayed

    Scenario: Verify valid time block with amount can be added
      When I add a time block with valid amount
      Then amount should appear in summary

    Scenario: Verify amount time blocks added over date range
      When I create a time block not applied for all dates
      Then amount time block should appear for each day
