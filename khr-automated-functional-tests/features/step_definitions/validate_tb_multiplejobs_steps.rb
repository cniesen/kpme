When(/^I add multiple time blocks with different assignments$/) do
  navigate_to_timedetail


  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "EC1 : EC1 - Security",
                      :hours => "2"

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock_ne = create TimeBlockObject, :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :earn_code => "EC1 : EC1 - Security",
                      :hours => "4",
                      :defer_check_entry => "true"


end


Then(/^the different time blocks must appear in summary$/) do
  on KpmeCalendarPage do |page|
    page.summary_assignment(0).should == @timeblock_hr.assignment
    page.summary_hours(0,'EC1',1).should include @timeblock_hr.hours
    page.summary_assignment_bgcolor(0).should == "rgba(152, 163, 134, 1)"
    page.summary_assignment(1).should == @timeblock_ne.assignment
    page.summary_hours(1,'EC1',1).should include @timeblock_ne.hours
    page.summary_assignment_bgcolor(1).should == "rgba(204, 204, 153, 1)"

  end


end
