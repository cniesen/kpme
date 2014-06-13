Given(/^I am logged in as indiana time keeping employee$/) do
  log_in 'inclockdetail1'
end

When(/^I add a time block with blank assignment$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

end

Then(/^(.*?) error should be displayed$/) do  |error_type|
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Assignment field cannot be empty" if error_type == "assignment field"
    page.validation_text.should == "Earn Code field cannot be empty" if error_type == "earn code field"
    page.close
  end
end


When(/^I select non-exempt work area assignment$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                      :defer_add => true


end


Then(/^default earn code should be selected$/) do
  on TimeblockWidgetPage do |page|
    page.earn_code.selected?("RGN : Regular Pay Non-Exempt").should == true
    page.earn_code_cal.exists?.should == true
  end

end


When(/^I select hourly work area assignment$/) do
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