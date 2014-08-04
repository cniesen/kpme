#KPMEQA-49
@wip


  Feature: Validate clock in and clock out entry in calendar


    Scenario: Validating the clock-in and clock-out functionality
      Given I am logged in as indiana hourly time keeping employee
      When I clock in and out hours
      Then time entry should be created in calendar page
      And I inquire the actual time entry
      Then I should see all the exact time values

