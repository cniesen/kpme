When(/^I cancel a delete operation of a time block$/) do
  navigate_to_timedetail
  @days=1
  @ec_type='RGN'
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end

  @timeblock = create TimeBlockObject, :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "08:00 AM",
                      :out_time => "10:00 AM",
                      :assignment_number => "0"

  @timeblock.remove_timeblock(@days,"true")

end



Then(/^the time block and summary values remain the same$/) do

  on KpmeCalendarPage do |page|
    page.time_last_text(@timeblock.assignment_number,@days).split("\n")[1].should == @timeblock.in_time+" - "+@timeblock.out_time
    page.hours_entry(@days).should == @ec_type+" - "+"2.00 hours"
    page.summary_fieldvalue(@timeblock.assignment_number,@ec_type,@days).should == "2.00"
    page.regular_day_total(@days).should == "2.00"

  end

end


When(/^I delete a time block$/) do
  navigate_to_timedetail
  @days=1
  @ec_type='RGN'
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end

  @timeblock = create TimeBlockObject, :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "08:00 AM",
                      :out_time => "10:00 AM",
                      :assignment_number => "0"

  @timeblock.remove_timeblock(@days,"false")

end


Then(/^the time block and summary values are removed$/) do
  on KpmeCalendarPage do |page|
    page.assignment_count(@days).should == 2
  end
end