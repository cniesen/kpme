Given(/^I am logged in as earn code security employee$/) do
  log_in 'ecsecurity'
end

When(/^I add a blank hours field$/) do

  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end

  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :start_date => @finalstart_dt,
                  :end_date => @finalend_dt,
                  :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                  :earn_code => "EC1 : EC1 - Security"

  @timeblock.add_time_block
end


Then(/^blank hours field error must be displayed$/) do

  on TimeblockWidgetPage do |page|
  page.validation_text.should == "Hour field cannot be empty"
  page.close
  end
end


When(/^I add hours more than the limit$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end

  @timeblock = make TimeBlockObject
  @timeblock.select_date
  @timeblock.edit :start_date => @finalstart_dt,
                  :end_date => @finalend_dt,
                  :hours => "25",
                  :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                  :earn_code => "EC1 : EC1 - Security"

  @timeblock.add_time_block
end


Then(/^hours limit error must be displayed$/) do
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Error : Hours cannot exceed 24."
    page.close
  end
end


When(/^I add time block with hours$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end

  @timeblock = create TimeBlockObject, :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :hours => "24",
                      :earn_code => "EC1 : EC1 - Security"

end


Then(/^valid time block entry must display in calendar$/) do
  on KpmeCalendarPage do |page|
    page.hours_entry(1)[6,2].should == @timeblock.hours
    page.widget_entry(1)
  end

  on TimeblockWidgetPage do |page|

    page.start_date.value.should include (@timeblock.start_date)
    page.end_date.value.should include (@timeblock.end_date)
    page.assignment.selected?(@timeblock.assignment)
    page.earn_code.selected?(@timeblock.earn_code)
    page.hours.value.should == @timeblock.hours
    page.close

  end

end

When(/^I create a time block for date range not applied to all dates$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,2)

  end

  @timeblock = create TimeBlockObject, :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :hours => "8",
                      :earn_code => "EC1 : EC1 - Security",
                      :apply_time => "No"



end

When(/^I create a time block for a date range$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,2)

  end

  @timeblock = create TimeBlockObject, :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :hours => "8",
                      :earn_code => "EC1 : EC1 - Security",
                      :apply_time => "Yes"


end


Then(/^time block entry should appear for each day$/) do
  on KpmeCalendarPage do |page|
    day1 = 1
    day2 = 2

    for current_day in day1..day2
      #puts "current day is #{current_day}"
      page.hours_entry(current_day)[6,1].should == @timeblock.hours
      page.widget_entry(current_day)


    on TimeblockWidgetPage do |page1|

      if current_day == day1
      page1.start_date.value.should include (@timeblock.start_date)
      page1.end_date.value.should include (@timeblock.start_date)
      end
      if current_day == day2
      page1.start_date.value.should include (@timeblock.end_date)
      page1.end_date.value.should include (@timeblock.end_date)
      end
      page1.assignment.selected?(@timeblock.assignment)
      page1.earn_code.selected?(@timeblock.earn_code)
      page1.hours.value.should == @timeblock.hours
      page1.close

     end
    end
  end

end

