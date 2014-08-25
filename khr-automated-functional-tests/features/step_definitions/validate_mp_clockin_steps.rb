When(/^I submit a missed punch for a clock in$/) do
  @wait_sec=60
  in_date=(Time.now).strftime("%m/%d/%Y")

  in_time=(Time.now-3600).strftime("%I:%M %p")

  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "test for missed clock in action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => in_date,
                         :punched_time => in_time
  @missed_punch.close


end

Then(/^the time block must be created$/) do

  on ClockPage do |page|
    if @missed_punch.missed_action == "Clock In"
      page.work_status.text[0,25].should include "Work Status : Clocked out"
      page.assignment_name.text.should == @missed_punch.assignment_name
    else
    page.work_status.text[0,24].should include "Work Status : Clocked in" if @missed_punch.missed_action == "Clock Out"
    page.assignment_name.text.should == "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
    end
  end

  navigate_to_timedetail



  on KpmeCalendarPage do |page|

    days=(DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y")-DateTime.strptime(page.pay_period.split('-').first,"%m/%d/%Y")).to_i
    page.missed_punch_icon(days)
    page.missed_punch_entry(days).should include "Missed Punch:\nDate Clock Action Details\n"
    page.missed_punch_entry(days).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%Y-%m-%d")
    if (@missed_punch.punched_time[0,2] == "12" && @missed_punch.punched_time[6,2] == "AM")
      page.missed_punch_entry(days).should include "00:"+@missed_punch.punched_time[3,2]
    else
      page.missed_punch_entry(days).should include @missed_punch.punched_time[0,5]
    end
    if @missed_punch.missed_action == "Clock In"
      page.missed_punch_entry(days).should include "CI " + @missed_punch.assignment_name[0,22]+ " $0.00 Rcd 0"
    else
      page.missed_punch_entry(days).should include "CO " + "IA-DEPT HR Work Area : $0.00 Rcd 0"
    end

    page.missed_punch_entry(days).should include "Doc Status: ENROUTE"
    page.missed_punch_icon(days)

  end


end

Then(/^I should see the time entry values$/) do

  on TimeInquiryPage do |page|

    if @missed_punch.missed_action == "Clock In"
      calendar_date=@timeentry.clock_out_work_sts.to_s[34,@timeentry.clock_out_work_sts.length]
      clock=@timeentry.clock_out_work_sts.to_s[-34,5]
      noon=@timeentry.clock_out_work_sts.to_s[-25,2]

      calendar_date= Date.strptime(calendar_date, '%a, %B %d %Y %H:%M:%S %p, %Z').strftime('%m/%d/%Y')

      page.windows[1].use
      @list_no=(page.number_of_items)-2

      page.timeentry_table(@list_no).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")
      page.timeentry_table(@list_no).should include "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
      page.timeentry_table(@list_no).should include @missed_punch.punched_time
      page.timeentry_table(@list_no).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")+" "+@missed_punch.punched_time
      page.timeentry_table(@list_no).should include clock+" "+noon
      page.timeentry_table(@list_no).should include calendar_date+" "+clock+" "+noon

    else
      calendar_date=@timeentry.clock_in_work_sts.to_s[33,@timeentry.clock_in_work_sts.length]

      calendar_date= Date.strptime(calendar_date, '%a, %B %d %Y %H:%M:%S %p, %Z').strftime('%m/%d/%Y')
      page.windows[1].use
      @list_no=(page.number_of_items)-2

      page.timeentry_table(@list_no).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")
      page.timeentry_table(@list_no).should include "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
      page.timeentry_table(@list_no).should include (DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(2/1440.0)).strftime("%I:%M %p")
      page.timeentry_table(@list_no).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")+" "+(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(2/1440.0)).strftime("%I:%M %p")
      page.timeentry_table(@list_no).should include @missed_punch.punched_time
      page.timeentry_table(@list_no).should include (DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")+" "+(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(3/1440.0)).strftime("%I:%M %p"))||(DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")+" "+(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(4/1440.0)).strftime("%I:%M %p"))


     end
    page.windows.last.close


  end

end