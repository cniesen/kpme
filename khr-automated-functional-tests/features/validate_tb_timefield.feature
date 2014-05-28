@draft


Feature: Validate time field in time blocks

  Background:
    Given I am logged in as employee
    And the assignment type is NE work area
    And the earn code is RGN

  Scenario: Verify blank out time cannot be added
    When I add a time block with blank out time
    Then blank out time error message must be displayed

  Scenario: Verify blank in time cannot be added
    When I add a time block with blank in time
    Then blank in time error message must be displayed

  Scenario: Verify invalid out time cannot be added
    When I add a time block with invalid out time
    Then invalid out time error message must be displayed

  Scenario: Verify invalid in time cannot be added
    When I add a time block with invalid in time
    Then invalid in time error message must be displayed

  Scenario: Verify invalid time cannot be added
    When I add a time block with in time later than out time
    Then invalid time error message must be displayed

  Scenario: Verify same time cannot be added
    When I add a time block with in time same as out time
    Then same time error message must be displayed