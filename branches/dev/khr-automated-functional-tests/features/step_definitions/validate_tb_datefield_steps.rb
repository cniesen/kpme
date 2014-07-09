Given /^I am logged in as indiana non-exempt timekeeping employee$/ do
  log_in 'indetail1'

end

When /^I add a time block with blank end date$/ do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => "",
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :in_time => "8am",
                      :out_time => "10am",
                      :defer_pick_assignment => true


end


Then /^(.*?) error message should be displayed$/ do |error_type|
  on TimeblockWidgetPage do |page|

    page.validation_text.should == "End Date is not a valid date" if error_type == "blank end date"
    page.validation_text.should == "Start Date is later than end date." if error_type == "start date later"
    page.close
  end

end



When(/^I add a time block with blank start date$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => "",
                      :end_date => @finalend_dt,
                      :defer_pick_assignment => true,
                      :defer_add => true

end


Then(/^no assignment should be displayed$/) do
  on TimeblockWidgetPage do |page|
    page.assignment.selected?("-- enter valid date range --").should == true
    page.close
  end

end

When(/^I add a time block with start date later than end date$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(2,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :in_time => "8am",
                      :out_time => "10am",
                      :defer_pick_assignment => true

end
