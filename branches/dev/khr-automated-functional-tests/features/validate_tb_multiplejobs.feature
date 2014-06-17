@wip

Feature: Validate multiple job time blocks

  Background:
      Given I am logged in as earn code security employee

  Scenario: Verify multiple jobs time blocks can be added
      When I add multiple time blocks with different assignments
      Then the different time blocks must appear in summary
