#KPMEQA-671
@wip

       Feature: Validate missed punch creation for clock in operation

       Scenario: Validate time block creation with missed punch for clock in operation
         Given I am logged in as iowa hourly timekeeping employee with no work status
         When I submit a missed punch for a clock in
         And I clock out hours
         Then the time block must be created
         And I inquire the actual time entry
         Then I should see the time entry values

