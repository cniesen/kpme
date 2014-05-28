Given /^I am logged in as employee$/ do
  log_in 'indetail1'

end

When /^I add a time block with blank end date$/ do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :end_date=>""
  #@timeblock.select_earncode
  @timeblock.add_time_block

end


Then /^(.*?) error message should be displayed$/ do |error_type|
  on TimeblockWidgetPage do |page|
    #puts error_type puts page.validation_text

    page.validation_text.should == "End Date is not a valid date" if error_type == "blank end date"
    page.validation_text.should == "Start Date is not a valid date" if error_type == "blank start date"
    page.validation_text.should == "Start Date is not a valid date" if error_type == "blank dates"
    page.validation_text.should == "Start Date is later than end date." if error_type == "start date later"
    page.close
  end

end



When(/^I add a time block with blank start date$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :start_date => ""
  #@timeblock.select_earncode
  @timeblock.add_time_block

end


When(/^I add a time block with blank start and end date$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :start_date => "", :end_date => ""
  #@timeblock.select_earncode
  @timeblock.add_time_block
end



When(/^I add a time block with start date later than end date$/) do
  @timeblock = make TimeBlockObject
  @timeblock.select_date

  on TimeblockWidgetPage do |page|
    @new_startdt = page.add_dates
  end

  @timeblock.edit :start_date => @new_startdt
  @timeblock.add_time_block


end
