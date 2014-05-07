Given /^I attempt to view the KHR portal page$/ do
  visit PortalMenu
end

#Then /^I should be able to see a portal group named "(.*?)"$/ do |arg1|
Then /^I should be able to see a portal group named \"Transactions\"$/ do
  PortalMenu.expected_element :portal_group_transactions
end