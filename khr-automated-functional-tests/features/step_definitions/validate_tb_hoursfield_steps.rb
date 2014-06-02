Given(/^I am logged in as employee2$/) do
  log_in 'ecsecurity'
end


And(/^the assignment type is EC work area$/) do
 @timeblock = make TimeBlockObject
 @timeblock.select_timedetail
 @timeblock.select_date
 @timeblock.edit :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC"

end

And(/^earn code is EC1$/) do
  @timeblock.edit :earn_code => "EC1 : EC1 - Security"
end


When(/^I add a blank hours field$/) do
 @timeblock.add_time_block
end


Then(/^blank hours field error must be displayed$/) do

  on TimeblockWidgetPage do |page|
  page.validation_text.should == "Hour field cannot be empty"
  end
end


When(/^I add hours more than the limit$/) do
  @timeblock.edit :hours => "25"
  @timeblock.add_time_block
end


Then(/^hours limit error must be displayed$/) do
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Error : Hours cannot exceed 24."
  end
end


When(/^I add time block with hours filled$/) do
  @timeblock.edit :hours => "24"
  @timeblock.add_time_block

end


Then(/^valid time block entry must display in calendar$/) do
  #@timeblock.del_time
  on KpmeCalendarPage do |page|
    page.hours_entry[6,2].should == "24"
    page.widget_entry
  end

end