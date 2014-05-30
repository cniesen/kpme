=begin
Given(/^I am logged in as employee(\d+)$/) do |arg1|
  log_in 'inclockdetail1'
end
=end

Given(/^I am logged in as employee1$/) do
  log_in 'inclockdetail1'
end

When(/^I add a time block with blank assignment$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :in_time => "8am" ,:out_time => "10am"
  @timeblock.add_time_block
end

Then(/^(.*?) error should be displayed$/) do  |error_type|
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Assignment field cannot be empty" if error_type == "assignment field"
    page.validation_text.should == "Earn Code field cannot be empty" if error_type == "earn code field"
    page.close
  end
end


When(/^I select NE work area in assignment$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT"

end


Then(/^default earn code should be selected$/) do
  on TimeblockWidgetPage do |page|
    page.earn_code.selected?("RGN : Regular Pay Non-Exempt").should == true
    page.earn_code_cal.exists?.should == true
  end

end


When(/^I select HR work area in assignment$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :assignment => "IN-DEPT HR Work Area : $5.00 Rcd 1 IN-DEPT"
end

Then(/^default earn codes are displayed$/) do
  on TimeblockWidgetPage do |page|
    page.earn_code.selected?("-- select an earn code --").should == true
    page.earn_code_cal.exists?.should == true
  end

end

And(/^add a time block with blank earn code$/) do
  @timeblock.add_time_block
end