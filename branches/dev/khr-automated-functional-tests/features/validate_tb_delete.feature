#KPMEQA-278
@wip


Feature: Validate deleting time blocks

  Background:
    Given I am logged in as earn code security employee

    Scenario: Cancelling a delete operation retains the existing values
      When I cancel a delete operation of a time block
      Then the time block and summary values remain the same


    Scenario: Validate the deletion of time block
      When I delete a time block
      Then the time block and summary values are removed
