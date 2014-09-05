When(/^I submit a missed punch for a clock in$/) do
  @wait_sec=60
  in_date=(Time.now-32400).strftime("%m/%d/%Y")
  in_time_hr=(Time.now-32400).strftime("%I:%M %p")
  in_time=in_time_hr[0,2]+":09"+in_time_hr[6,2]
  in_time=DateTime.strptime(in_time,"%I:%M %p").strftime("%I:%M %p")

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
      @current_time_out=@timeentry.current_time_out.to_s[0,5]
      @current_out_meridian=(@timeentry.current_time_out.to_s[9,2]).upcase
      out_status=@timeentry.clock_out_work_sts.to_s[34,@timeentry.clock_out_work_sts.length]
      @calendar_date= DateTime.strptime(out_status, '%a, %B %d %Y %H:%M:%S %p, %Z').strftime('%m/%d/%Y')
      @day_diff =(DateTime.strptime(@calendar_date,"%m/%d/%Y")-DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y")).to_i
      @out_time=DateTime.strptime(out_status, '%a, %B %d %Y %H:%M:%S %p, %Z').strftime('%I:%M %p')
      ((@out_time[3,2].to_i)%6).should==0
    else
      page.work_status.text[0,24].should include "Work Status : Clocked in"
      page.assignment_name.text.should == "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
    end
  end

  navigate_to_timedetail

  on KpmeCalendarPage do |page|
   if @missed_punch.missed_action == "Clock In"
     @days=(DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y")-DateTime.strptime(page.pay_period.split('-').first,"%m/%d/%Y")).to_i
     day = @days
            while day <= (@days+1)
              day=0 if @days == -1
              page.missed_punch_icon(day)
              page.missed_punch_entry(day).should include "Missed Punch:\nDate Clock Action Details\n"
              page.missed_punch_entry(day).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%Y-%m-%d")
              if (@missed_punch.punched_time[0,2] == "12" && @missed_punch.punched_time[6,2] == "AM")
                page.missed_punch_entry(day).should include "00:"+@missed_punch.punched_time[3,2]
              else
                page.missed_punch_entry(day).should include DateTime.strptime(@missed_punch.punched_time,"%I:%M %p").strftime("%H:%M")
              end
              page.missed_punch_entry(day).should include "CI " + @missed_punch.assignment_name[0,22]+ " $0.00 Rcd 0"
              page.missed_punch_entry(day).should include "Doc Status: ENROUTE"
              page.missed_punch_icon(day)
              if (@day_diff == 0 || @days == -1)
                 day=day+2
              else
                 day=day+1
              end
            end
    else
            @days=(DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y")-DateTime.strptime(page.pay_period.split('-').first,"%m/%d/%Y")).to_i
            page.missed_punch_icon(@days)
            page.missed_punch_entry(@days).should include "Missed Punch:\nDate Clock Action Details\n"
            page.missed_punch_entry(@days).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%Y-%m-%d")
            if (@missed_punch.punched_time[0,2] == "12" && @missed_punch.punched_time[6,2] == "AM")
              page.missed_punch_entry(@days).should include "00:"+@missed_punch.punched_time[3,2]
            else
              page.missed_punch_entry(@days).should include DateTime.strptime(@missed_punch.punched_time,"%I:%M %p").strftime("%H:%M")
            end
            page.missed_punch_entry(@days).should include "CO " + "IA-DEPT HR Work Area : $0.00 Rcd 0"
            page.missed_punch_entry(@days).should include "Doc Status: ENROUTE"
            page.missed_punch_icon(@days)
     end
  end
end

Then(/^I should see the time entry values$/) do

  on TimeInquiryPage do |page|

    if @missed_punch.missed_action == "Clock In"
      page.windows[1].use
      if (@days!= -1)
        @list_no=(page.number_of_items)-3
        #puts @day_diff
        page.timeentry_table(@list_no).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")
        page.timeentry_table(@list_no).should include "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
        page.timeentry_table(@list_no).should include  (DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(3/1440.0)).strftime("%I:%M %p")

        if (@day_diff==0)
          page.timeentry_table(@list_no).should include @out_time
          time_diff=page.time_difference(@list_no)
          time_diff.should be_within(6).of(0)
          time_minrange=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")-(1/1440.0)).strftime("%I:%M %p")
          time_maxrange=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")+(1/1440.0)).strftime("%I:%M %p")
          time_exact=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")).strftime("%I:%M %p")
          [time_minrange,time_exact,time_maxrange].should include(page.timeentry_table(@list_no)[-8,8])
       else
          page.timeentry_table(@list_no).should include  "12:00 AM"
          page.timeentry_table(@list_no).should include ((DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y")+1).strftime("%m/%d/%Y")+" "+"12:00 AM")
          time_diff=page.time_difference(@list_no)
          time_diff.should be < 540
       end
     end

      if (@day_diff!= 0)
        @list_no=(page.number_of_items)-2
        page.timeentry_table(@list_no).should include @calendar_date
        page.timeentry_table(@list_no).should include "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
        page.timeentry_table(@list_no).should include  "12:00 AM"
        page.timeentry_table(@list_no).should include @calendar_date+" "+"12:00 AM"
        page.timeentry_table(@list_no).should include @out_time
        time_diff=page.time_difference(@list_no)
        time_diff.should be < 540
        time_minrange1=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")-(1/1440.0)).strftime("%I:%M %p")
        time_minrange2=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")-(2/1440.0)).strftime("%I:%M %p")
        time_minrange3=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")-(3/1440.0)).strftime("%I:%M %p")
        time_maxrange1=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")+(1/1440.0)).strftime("%I:%M %p")
        time_maxrange2=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")+(2/1440.0)).strftime("%I:%M %p")
        time_maxrange3=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")+(3/1440.0)).strftime("%I:%M %p")
        time_exact=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")).strftime("%I:%M %p")
        [time_minrange3,time_minrange2,time_minrange1,time_exact,time_maxrange1,time_maxrange2,time_maxrange3].should include(page.timeentry_table(@list_no)[-8,8])
        time1=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")-(2/1440.0)).strftime("%H%M")
        time2=(DateTime.strptime(@current_time_out+" "+@current_out_meridian,"%I:%M %p")+(3/1440.0)).strftime("%H%M")
        page_time=DateTime.strptime(page.timeentry_table(@list_no)[-8,8],"%I:%M %p").strftime("%H%M")
        expect(page_time).to be_between(time1,time2)
      end
    else
        page.windows[1].use
        @list_no=(page.number_of_items)-2
        page.timeentry_table(@list_no).should include @calendar_date
        page.timeentry_table(@list_no).should include "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT"
        page.timeentry_table(@list_no).should include @time
        page.timeentry_table(@list_no).should include DateTime.strptime(@missed_punch.punched_date,"%m/%d/%Y").strftime("%m/%d/%Y")
        intime_minrange5=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(5/1440.0)).strftime("%I:%M %p")
        intime_minrange4=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(4/1440.0)).strftime("%I:%M %p")
        intime_minrange3=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(3/1440.0)).strftime("%I:%M %p")
        intime_minrange2=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(2/1440.0)).strftime("%I:%M %p")
        intime_maxrange1=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")-(1/1440.0)).strftime("%I:%M %p")
        intime=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")).strftime("%I:%M %p")
        [intime_minrange5,intime_minrange4,intime_minrange3,intime_minrange2,intime_maxrange1,intime].should include(page.timeentry_table(@list_no)[-37,8])
        outtime_minrange1=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(1/1440.0)).strftime("%I:%M %p")
        outtime_minrange2=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(2/1440.0)).strftime("%I:%M %p")
        outtime_minrange3=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(3/1440.0)).strftime("%I:%M %p")
        outtime_minrange4=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(4/1440.0)).strftime("%I:%M %p")
        outtime_minrange5=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(5/1440.0)).strftime("%I:%M %p")
        outtime=(DateTime.strptime(@missed_punch.punched_time,"%I:%M %p")+(6/1440.0)).strftime("%I:%M %p")
        [outtime_minrange1,outtime_minrange2,outtime_minrange3,outtime_minrange4,outtime_minrange5,outtime].should include(page.timeentry_table(@list_no)[-8,8])
        ((page.time_value(@list_no,2)[3,2].to_i)%6).should==0
        ((page.time_value(@list_no,4)[3,2].to_i)%6).should==0
    end
    page.windows.last.close
  end
end