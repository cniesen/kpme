@wip

Feature: Validate time block cannot be added outside pay calendar

  Background:
    Given I am logged in as indiana non-exempt timekeeping employee

    Scenario: Validate time block cannot be added to dates prior to pay calendar
     When I select dates prior to the pay calendar
     Then error message about pay dates should appear

    Scenario: Validate time block cannot be added to dates later to pay calendar
      When I select dates later to the pay calendar
      Then error message about pay dates should appear

    Scenario: Validate assignment field is empty
      When I select start date earlier to the current pay period
      Then error message about pay dates should appear

    Scenario: Validate no assignments are available when the end date is later to pay calendar
      When I select end date later to the current pay period
      Then error message about pay dates should appear
