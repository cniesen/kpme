When(/^I add multiple time blocks with different assignments$/) do
  navigate_to_timedetail

  @days = 1
  @week_no = 1
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "EC1 : EC1 - Security",
                      :hours => "2",
                      :assignment_number => "0"


  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock_ne = create TimeBlockObject, :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :earn_code => "EC1 : EC1 - Security",
                      :hours => "2",
                      :defer_check_entry => "true",
                      :assignment_number => "1"


end


Then(/^the different time blocks must appear in summary$/) do
  on KpmeCalendarPage do |page|


    page.summary_assignment_name(@timeblock_hr.assignment_number,@week_no).should == @timeblock_hr.assignment
    page.summary_fieldvalue(@timeblock_hr.assignment_number,@timeblock_hr.earn_code[0,3],@days).should include @timeblock_hr.hours
    page.summary_assignment_bgcolor(@timeblock_hr.assignment_number).should == "rgba(152, 163, 134, 1)"


    page.summary_assignment_name(@timeblock_ne.assignment_number,@week_no).should == @timeblock_ne.assignment
    page.summary_fieldvalue(@timeblock_ne.assignment_number,@timeblock_ne.earn_code[0,3],@days).should include @timeblock_ne.hours
    page.summary_assignment_bgcolor(@timeblock_ne.assignment_number).should == "rgba(204, 204, 153, 1)"

  end


end
