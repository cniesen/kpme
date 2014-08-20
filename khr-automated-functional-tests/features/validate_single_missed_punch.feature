#KPMEQA-670
@wip

  Feature: Validate single missed punch creation


    Scenario: Validate a missed punch can be created
      Given I am logged in as iowa hourly timekeeping employee with no work status
      When I submit a missed punch
      Then missed punch document is successfully created
      And I clock out hours
      Then work status should change


    Scenario: Validate only one missed punch can be used to create time block
      Given I have a missed punch
      When I submit a new missed punch
      Then error message on missed punch appears