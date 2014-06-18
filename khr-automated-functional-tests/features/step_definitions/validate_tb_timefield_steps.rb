When(/^I add a time block with blank out time$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :in_time => "8am",
                      :defer_pick_assignment => true

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
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :out_time => "10am",
                      :defer_pick_assignment => true


end

When(/^I add a time block with invalid out time$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :in_time => "8am",
                      :out_time => "asdf",
                      :defer_pick_assignment => true

end


When(/^I add a time block with invalid in time$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :out_time => "10am",
                      :in_time => "asdf",
                      :defer_pick_assignment => true

end


When(/^I add a time block with in time later than out time$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :out_time => "8am",
                      :in_time => "10am",
                      :defer_pick_assignment => true

end

When(/^I add a time block with in time same as out time$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :earn_code => "RGN : Regular Pay Non-Exempt",
                      :in_time => "8am",
                      :out_time => "8am",
                      :defer_pick_assignment => true

end