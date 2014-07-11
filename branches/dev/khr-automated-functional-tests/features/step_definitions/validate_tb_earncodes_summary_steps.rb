When(/^I add a time block with regular earn code$/) do
  navigate_to_timedetail
  @ec_type='RGN'
  @day_ranges=[1,8].sample

  if @day_ranges == 1
    @days=1
    @week_no=1
  else
    @days=8
    @week_no=2
  end

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(@day_ranges,@day_ranges)
  end


  @timeblock_ne = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :in_time => "8am",
                      :out_time => "9am",
                      :assignment_number => "0"
end



Then(/^summary grid displays total hours for the earn code$/) do

  on KpmeCalendarPage do |page|
   page.hours_entry(@days)[6,10].should == "1.00 hours"
   page.summary_fieldvalue(@timeblock_ne.assignment_number,@ec_type,@days).should == "1.00"
   page.week_day_summary(@days).should == "1.00"
   page.regular_day_total(@days).should =="1.00"
   page.week_summary_total(@week_no).should == "1.00"

  end

end

When(/^I add a time block for the entire week with regular earn code$/) do

  navigate_to_timedetail
  @ec_type='RGN'
  @day_range1=[0,7].sample

  if @day_range1 == 0
    @days=6
    @week_no=1
    @day_range2=1
  else
    @days=13
    @week_no=2
    @day_range2=8
  end

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(@day_range1,(@day_range1+6))
  end


  @timeblock_ne = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                         :in_time => "9am",
                         :out_time => "10am",
                         :assignment_number => "0"



  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(@day_range2,@day_range2)
  end


  @timeblock_ne1 = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                         :in_time => "8am",
                         :out_time => "9am",
                         :defer_check_entry => true
end


Then(/^summary grid displays total hours for the entire week$/) do

  on KpmeCalendarPage do |page|
    page.hours_entry(@days)[6,10].should == "1.00 hours"
    for day in @days.downto(@days-6)
      #puts day
      if day==1 or day==8
        page.summary_fieldvalue(@timeblock_ne.assignment_number,@ec_type,day).should == "2.00"
        page.week_day_summary(day).should == "2.00"
        page.regular_day_total(day).should =="2.00"
      else
        page.summary_fieldvalue(@timeblock_ne.assignment_number,@ec_type,day).should == "1.00"
        page.week_day_summary(day).should == "1.00"
        page.regular_day_total(day).should == "1.00"
      end
    end
        page.week_summary_total(@week_no).should == "8.00"

  end

end

When(/^I add a time block with security earn code$/) do

  navigate_to_timedetail
  @ec_type='EC1'
  @day_ranges=[1,8].sample

  if @day_ranges == 1
    @days=1
    @week_no=1
  else
    @days=8
    @week_no=2
  end

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(@day_ranges,@day_ranges)
  end


  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                         :earn_code => "EC1 : EC1 - Security",
                         :hours => 1,
                         :assignment_number => "0"
end

Then(/^summary grid displays the earn code with total hours$/) do

  on KpmeCalendarPage do |page|
    page.hours_entry(@days)[6,10].should == "1.00 hours"

    page.summary_assignment_name(@timeblock_hr.assignment_number,@week_no).should == @timeblock_hr.assignment
    page.summary_fieldvalue(@timeblock_hr.assignment_number,@ec_type,@days) == "1.00"
    page.assignment_total(@timeblock_hr.assignment_number,@ec_type,@week_no).should == "1.00"
    page.other_total(@days).should =="1.00"
    page.week_other_total(@week_no).should == "1.00"

  end

end


When(/^I add time blocks with different earn code for the entire week$/) do
  navigate_to_timedetail
  @ec_type='EC1'
  @day_range1=[0,7].sample

  if @day_range1 == 0
    @days=6
    @week_no=1
    @day_range2=1
  else
    @days=13
    @week_no=2
    @day_range2=8
  end

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(@day_range1,(@day_range1+6))
  end

  @timeblock_ne = create TimeBlockObject, :start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                         :earn_code => "EC1 : EC1 - Security",
                         :hours => "1",
                         :assignment_number => "1"

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(@day_range2,@day_range2)
  end


  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment =>  "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                         :earn_code => "EC1 : EC1 - Security",
                         :hours => "1",
                         :defer_check_entry => true,
                         :assignment_number => "0"

end

Then(/^summary grid displays the total hours for the individual earn code$/) do

  on KpmeCalendarPage do |page|
    page.hours_entry(@days)[6,10].should == "1.00 hours"

    for day in @days.downto(@days-6)

      page.summary_assignment_name(@timeblock_hr.assignment_number,@week_no).should == @timeblock_hr.assignment
      page.summary_assignment_name(@timeblock_ne.assignment_number,@week_no).should == @timeblock_ne.assignment
      if day==1 or day==8
        page.summary_fieldvalue(@timeblock_ne.assignment_number,@ec_type,day).should == "1.00"
        page.summary_fieldvalue(@timeblock_hr.assignment_number,@ec_type,day).should == "1.00"
        page.day_other_total(day).should == "2.00"
      else
        page.summary_fieldvalue(@timeblock_ne.assignment_number,@ec_type,day).should == "1.00"
        page.day_other_total(day).should == "1.00"
      end
    end
    page.assignment_total(@timeblock_ne.assignment_number,@ec_type,@week_no).should == "7.00"
    page.assignment_total(@timeblock_hr.assignment_number,@ec_type,@week_no).should == "1.00"
    page.week_other_total(@week_no).should == "8.00"

  end

end


When(/^I add an amount time block for a day$/) do
  navigate_to_timedetail
  @ec_type='ECA'

  @days = 1
  @week_no = 1
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                         :earn_code => "ECA : ECA - Earn code with Amount",
                         :amount => "10.00",
                         :assignment_number => "0"

end


Then(/^summary grid displays the amount for the day$/) do

  on KpmeCalendarPage do |page|
    page.amount_entry(@days).should include (@timeblock_hr.amount)
    page.summary_fieldvalue(@timeblock_hr.assignment_number,@ec_type,@days).should == "$"+@timeblock_hr.amount
    page.week_other_total(@week_no).should == "0.00"

    end

end


When(/^I add an amount time block for the week$/) do
  navigate_to_timedetail
  @ec_type='ECA'

  @days = 6
  @week_no = 1
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(0,6)
  end

  @timeblock_hr = create TimeBlockObject,:start_date => @finalstart_dt,
                         :end_date => @finalend_dt,
                         :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                         :earn_code => "ECA : ECA - Earn code with Amount",
                         :amount => "10.00",
                         :assignment_number => "0"

end


Then(/^summary grid displays the earn code with amount for the week$/) do

  on KpmeCalendarPage do |page|
    for day in @days.downto(@days-6)
    page.amount_entry(@days).should include (@timeblock_hr.amount)
    page.summary_fieldvalue(@timeblock_hr.assignment_number,@ec_type,@days).should == "$"+@timeblock_hr.amount
    end
    page.week_other_total(@week_no).should == "0.00"

  end

end