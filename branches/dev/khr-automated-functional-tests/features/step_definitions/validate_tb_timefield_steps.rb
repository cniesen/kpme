And(/^regular pay earn code is selected$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :earn_code => "RGN : Regular Pay Non-Exempt"

end

When(/^I add a time block with blank out time$/) do
  @timeblock.edit :in_time => "8am"
  @timeblock.add_time_block

end


Then(/^(.*?) error message must be displayed$/) do |error_type|
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Time entry field cannot be empty" if error_type == "blank out time"
    page.validation_text.should == "Time entry field cannot be empty" if error_type == "blank in time"
    page.validation_text.should == "Time entry field cannot be empty" if error_type == "invalid out time"
    page.validation_text.should == "Time entry field cannot be empty" if error_type == "invalid in time"
    page.validation_text.should == "Error : The time or date is not valid." if error_type == "invalid time"
    page.validation_text.should == "Error : Start time and end time cannot be equivalent" if error_type == "same time"
    page.close
  end

end


When(/^I add a time block with blank in time$/) do
  @timeblock.edit :out_time => "10am"
  @timeblock.add_time_block
end

When(/^I add a time block with invalid out time$/) do
  @timeblock.edit :in_time => "8am", :out_time => "asdf"
  @timeblock.add_time_block
end


When(/^I add a time block with invalid in time$/) do
  @timeblock.edit :out_time => "10am", :in_time => "asdf"
  @timeblock.add_time_block
end


When(/^I add a time block with in time later than out time$/) do
  @timeblock.edit :in_time => "10am", :out_time => "8am"
  @timeblock.add_time_block
end

When(/^I add a time block with in time same as out time$/) do
  @timeblock.edit :in_time => "8am", :out_time => "8am"
  @timeblock.add_time_block
end