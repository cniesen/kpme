When(/^I add time blocks with different earn code$/) do
  navigate_to_timedetail
  @days=1


  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock_ne = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                         :in_time => "12:00 PM",
                         :out_time => "01:00 PM",
                         :assignment_number => "0"

  @timeblock_ne1 = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                         :in_time => "11:00 AM",
                         :out_time => "12:00 PM",
                         :assignment_number => "0",
                         :defer_check_entry => true

end


Then(/^time blocks should display based on time entry$/) do

  on KpmeCalendarPage do |page|
    page.time_first_text(@timeblock_ne1.assignment_number,@days).split("\n")[1].should == @timeblock_ne1.in_time+" - "+@timeblock_ne1.out_time
    page.time_last_text(@timeblock_ne.assignment_number,@days).split("\n")[1].should == @timeblock_ne.in_time+" - "+@timeblock_ne.out_time
  end


end

When(/^I add time blocks with different assignments with earn code$/) do
  navigate_to_timedetail
  @days=1


  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock_ne = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                         :in_time => "12:30 PM",
                         :out_time => "01:00 PM",
                         :assignment_number => "1"

  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                          :end_date => @finalend_dt,
                          :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                          :in_time => "11:00 AM",
                          :out_time => "12:30 PM",
                          :earn_code => "CAL : Call Back Comp",
                          :assignment_number => "0",
                          :defer_check_entry => true

end


Then(/^the different assignment time blocks should display based on time entry$/) do

  on KpmeCalendarPage do |page|
    page.time_first_text(@timeblock_hr.assignment_number,@days).split("\n")[1].should == @timeblock_hr.in_time+" - "+@timeblock_hr.out_time
    page.time_last_text(@timeblock_ne.assignment_number,@days).split("\n")[1].should == @timeblock_ne.in_time+" - "+@timeblock_ne.out_time

  end
end