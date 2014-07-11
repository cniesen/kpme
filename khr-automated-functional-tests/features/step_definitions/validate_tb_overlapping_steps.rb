When(/^I add same value for multiple time blocks for a day$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(2,2)

  end
  @assign=['NE','HR'].sample

  if @assign == 'NE'
    @assignment_val="IA-EC NE Work Area : $5.00 Rcd 1 IA-EC"
    @ec_val="RGN : Regular Pay Non-Exempt"
  else
    @assignment_val="IA-EC HR Work Area : $5.00 Rcd 0 IA-EC"
    @ec_val="CAL : Call Back Comp"
  end

  @timeblock_ne1 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

  @timeblock_ne2 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am",
                      :defer_check_entry => true

end



Then(/^(.*?) time block error message displays$/) do |error_type|

  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Error : The time block you are trying to add overlaps with an existing time block." if error_type == "overlapping"
    page.close
  end

end

When(/^I add partly overlapping out time entry for a day$/) do

  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(2,2)

  end

  @assign=['NE','HR'].sample

  if @assign == 'NE'
    @assignment_val="IA-EC NE Work Area : $5.00 Rcd 1 IA-EC"
    @ec_val="RGN : Regular Pay Non-Exempt"
  else
    @assignment_val="IA-EC HR Work Area : $5.00 Rcd 0 IA-EC"
    @ec_val="CAL : Call Back Comp"
  end

  @timeblock_ne1 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

  @timeblock_ne2 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "9:30am",
                      :out_time => "11am",
                      :defer_check_entry => true

end



When(/^I add partly overlapping in time entry for a day$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(2,2)

  end

  @assign=['NE','HR'].sample

  if @assign == 'NE'
    @assignment_val="IA-EC NE Work Area : $5.00 Rcd 1 IA-EC"
    @ec_val="RGN : Regular Pay Non-Exempt"
  else
    @assignment_val="IA-EC HR Work Area : $5.00 Rcd 0 IA-EC"
    @ec_val="CAL : Call Back Comp"
  end

  @timeblock_ne1 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

  @timeblock_ne2 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "7:30am",
                      :out_time => "9am",
                      :defer_check_entry => true

end


When(/^I add time entry values embedded in another time block$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(2,2)

  end

  @assign=['NE','HR'].sample

  if @assign == 'NE'
    @assignment_val="IA-EC NE Work Area : $5.00 Rcd 1 IA-EC"
    @ec_val="RGN : Regular Pay Non-Exempt"
  else
    @assignment_val="IA-EC HR Work Area : $5.00 Rcd 0 IA-EC"
    @ec_val="CAL : Call Back Comp"
  end

  @timeblock_ne1 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

  @timeblock_ne2 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8:30am",
                      :out_time => "9:30am",
                      :defer_check_entry => true

end



When(/^I add time block encapsulating another time entry$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(2,2)

  end
  @assign=['NE','HR'].sample

  if @assign == 'NE'
    @assignment_val="IA-EC NE Work Area : $5.00 Rcd 1 IA-EC"
    @ec_val="RGN : Regular Pay Non-Exempt"
  else
    @assignment_val="IA-EC HR Work Area : $5.00 Rcd 0 IA-EC"
    @ec_val="CAL : Call Back Comp"
  end

  @timeblock_ne1 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

  @timeblock_ne2 = create TimeBlockObject, :assignment => @assignment_val,
                      :earn_code => @ec_val,
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "7:30am",
                      :out_time => "11:30am",
                      :defer_check_entry => true

end