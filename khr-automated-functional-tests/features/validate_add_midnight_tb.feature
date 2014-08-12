#KPMEQA-712
@wip

  Feature: Validate midnight time blocks for semi monthly pay period

    Background:
      Given I am logged in as iowa non-exempt timekeeping employee

    Scenario: Validate midnight time block can be added to first day in semi monthly pay period
       When I add a midnight time block to the start of pay period
       Then the time block should be created

    Scenario: Validate midnight time block can be added to last day in semi monthly pay period
      When I add a midnight time block to the end of pay period
      Then the time block should be created