#KPMEQA-668
@wip

    Feature: Validate date and time during missed punch creation

      Background:
        Given I am logged in as iowa hourly timekeeping employee

      Scenario: Verify date and time cannot be blank in missed punch
        When I provide blank date and time in missed punch
        Then error message on blank fields appear

      Scenario: Verify future date cannot be provided in missed punch
        When I provide a future date in missed punch
        Then error message on future date appears

      Scenario: Verify future time cannot be provided in missed punch
        When I provide a future time in missed punch
        Then error message on time appears

      Scenario: Verify past date cannot be provided in missed punch
        When I provide a past date in missed punch
        Then error message on past date appears



