@draft

Feature: Validate assignment field in time blocks

  Scenario: Verify assignment field cannot be empty
    Given I am logged in as employee1
    When I add a time block with blank assignment
    Then assignment field error should be displayed

  Scenario: Verify default earn code is selected
    Given I am logged in as employee1
    When I select NE work area in assignment
    Then default earn code should be selected

  Scenario: Verify blank earn code cannot be added
    Given I am logged in as employee1
    When I select HR work area in assignment
    Then default earn codes are displayed
    And add a time block with blank earn code
    Then earn code field error should be displayed

