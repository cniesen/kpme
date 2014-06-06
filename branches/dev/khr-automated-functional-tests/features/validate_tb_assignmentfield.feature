@draft

Feature: Validate assignment field in time blocks

  Background:
    Given I am logged in as indiana time keeping employee

  Scenario: Verify assignment field cannot be empty
    When I add a time block with blank assignment
    Then assignment field error should be displayed

  Scenario: Verify default earn code is selected
    When I select non-exempt work area assignment
    Then default earn code should be selected

  Scenario: Verify blank earn code cannot be added
    When I select hourly work area assignment
    Then default earn codes are displayed
    And add a time block with blank earn code
    Then earn code field error should be displayed

