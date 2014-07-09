When(/^I add a regular earn code time block$/) do
  navigate_to_timedetail
  @days=1
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end


  @timeblock = create TimeBlockObject, :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                          :start_date => @finalstart_dt,
                          :end_date => @finalend_dt,
                          :in_time => "8am",
                          :out_time => "10am"

end


Then(/^I should see correct values in the time block entry$/) do

  on KpmeCalendarPage do |page|

    page.widget_entry(@days)
   end

    on TimeblockWidgetPage do |page|

      page.start_date.value.should == @timeblock.start_date
      page.end_date.value.should == @timeblock.end_date
      page.assignment.selected?(@timeblock.assignment).should == true
      page.earn_code.selected?("RGN : Regular Pay Non-Exempt").should == true
      page.in_time.value.should == DateTime.strptime(@timeblock.in_time,'%I%P').strftime('%H:%M %p')
      page.out_time.value.should == DateTime.strptime(@timeblock.out_time,'%I%P').strftime('%H:%M %p')
      page.close
    end


end


Given(/^I have a time block created$/) do
  navigate_to_timedetail
  @days=1
  @ec_type="RGN"
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end


  @timeblock = create TimeBlockObject, :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "8am",
                      :out_time => "10am"

end

When(/^I edit time values of the time block$/) do


  @timeblock.edit    :cal_day => 1,
                     :in_time => "9am",
                     :out_time => "12pm",
                     :edit_existing_entry => "true",
                     :assignment_number => "0"
  @timeblock.add_time_block

end


Then(/^I should see latest values in summary and time block entry$/) do
  on KpmeCalendarPage do |page|

        page.time_last_text(@timeblock.assignment_number,@days).split("\n")[1].should == DateTime.strptime(@timeblock.in_time,'%I%P').strftime('%H:%M %p')+" - "+ DateTime.strptime(@timeblock.out_time,'%I%P').strftime('%H:%M %p')
        page.hours_entry(@days).should == @ec_type+" - "+"3.00 hours"
        page.summary_fieldvalue(@timeblock.assignment_number,@ec_type,@days).should == "3.00"
    end
end


Given(/^I have an overnight time block created$/) do
  navigate_to_timedetail
  @days=8
  @ec_type="RGN"
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(8,9)

  end


  @timeblock = create TimeBlockObject, :assignment => "IA-EC NE Work Area : $5.00 Rcd 1 IA-EC",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "10pm",
                      :out_time => "2am",
                      :apply_time => "No"

end


When(/^I edit values of the time block$/) do

  @timeblock.edit    :cal_day => 8,
                     :in_time => "9pm",
                     :edit_existing_entry => "true",
                     :assignment_number => "0"
  @timeblock.add_time_block

  @timeblock.edit    :cal_day => 9,
                     :out_time => "3am",
                     :edit_existing_entry => "true",
                     :assignment_number => "0"
  @timeblock.add_time_block

end


Then(/^I should see values in summary and time block entry$/) do
  on KpmeCalendarPage do |page|

    for day in @days..(@days+1)
       if day == 8
         page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == DateTime.strptime(@timeblock.in_time,'%I%P').strftime('%I:%M %p')+" - "+ "12:00 AM"
         page.hours_entry(day).should == @ec_type+" - "+"3.00 hours"
         page.summary_fieldvalue(@timeblock.assignment_number,@ec_type,day).should == "3.00"
       else
         page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should ==  "12:00 AM"+" - "+DateTime.strptime(@timeblock.out_time,'%I%P').strftime('%H:%M %p')
         page.hours_entry(day).should == @ec_type+" - "+"3.00 hours"
         page.summary_fieldvalue(@timeblock.assignment_number,@ec_type,day).should == "3.00"

       end
    end

  end
end