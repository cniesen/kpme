#KPMEQA-672
@wip

        Feature: Validate missed punch creation for clock out operation

          Scenario: Validate time block creation with missed punch for clock out operation
            Given I am logged in as iowa hourly timekeeping employee with clock in work status
            When I submit a missed punch for a clock out
            Then the time block must be created
            And I inquire the actual time entry
            Then I should see the time entry values