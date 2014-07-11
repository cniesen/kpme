When(/^I add nightly time block applied for each day$/) do
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,2)

  end

  @timeblock = create TimeBlockObject, :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "10:00 PM",
                      :out_time => "02:00 AM",
                      :apply_time => "Yes"

end


Then(/^the time block should not be added$/) do
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Error : The \"apply to each day\" box should not be checked."
  end
end


When(/^I add nightly time block not applied for each day$/) do

  @days=2
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(2,4)

  end

  @timeblock = create TimeBlockObject, :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "10:00 PM",
                      :out_time => "02:00 AM",
                      :assignment_number => "0",
                      :apply_time => "No"

end


Then(/^the time block should be added$/) do

  on KpmeCalendarPage do |page|

    for day in @days..(@days+2)
      page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == @timeblock.in_time+" - "+"12:00 AM" if day == 2
      page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == "12:00 AM"+" - "+"12:00 AM" if day == 3
      page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == "12:00 AM"+" - "+@timeblock.out_time if day == 4
    end

  end

end



When(/^I add nightly time block individually for days$/) do

@days=8
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(8,8)

  end

  @timeblock_1 = create TimeBlockObject, :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "10:00 PM",
                      :out_time => "12:00 AM",
                      :assignment_number => "0",
                      :apply_time => "No"

  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(9,9)

  end

  @timeblock_2 = create TimeBlockObject, :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                        :start_date => @finalstart_dt,
                        :end_date => @finalend_dt,
                        :in_time => "12:00 AM",
                        :out_time => "02:00 AM",
                        :defer_check_entry => true,
                        :assignment_number => "0",
                        :apply_time => "No"

end



Then(/^the time block should be added for each day$/) do

  on KpmeCalendarPage do |page|

    for day in @days..(@days+1)
      page.time_last_text(@timeblock_1.assignment_number,day).split("\n")[1].should == @timeblock_1.in_time+" - "+@timeblock_1.out_time if day == 8
      page.time_last_text(@timeblock_2.assignment_number,day).split("\n")[1].should == @timeblock_2.in_time+" - "+@timeblock_2.out_time if day == 9

      end
   end

end

When(/^I add regular earn codes over multiple days$/) do
  @days=1
  @ec_type='RGN'
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,3)

  end

  @timeblock = create TimeBlockObject, :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "01:00 PM",
                      :out_time => "10:00 AM",
                      :assignment_number => "0"


end

And(/^un apply the time block for each day$/) do

  @timeblock.edit :apply_time => "No"
  @timeblock.add_time_block


end


Then(/^the time block should be added for every day$/) do

  on KpmeCalendarPage do |page|

    for day in @days..(@days+2)
      if day == 1
        page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == @timeblock.in_time+" - "+ "12:00 AM"
        page.hours_entry(day).should == @ec_type+" - "+"11.00 hours"
      end
      if day == 2
        page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == "12:00 AM"+" - "+"12:00 AM"
        page.hours_entry(day).should == @ec_type+" - "+"24.00 hours"
      end
      if day == 3
        page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == "12:00 AM"+" - "+@timeblock.out_time
        page.hours_entry(day).should == @ec_type+" - "+"5.00 hours"
      end

    end
  end
end


When(/^I add day time blocks over different days$/) do
  @days=8
  @ec_type='RGN'
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(8,10)

  end

  @timeblock = create TimeBlockObject, :assignment => "IN-DEPT NE Work Area : $5.00 Rcd 0 IN-DEPT",
                      :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :in_time => "08:00 AM",
                      :out_time => "10:00 AM",
                      :assignment_number => "0"

end


Then(/^blocks with exact time values should be added$/) do

  on KpmeCalendarPage do |page|

    for day in @days..(@days+2)
        page.time_last_text(@timeblock.assignment_number,day).split("\n")[1].should == @timeblock.in_time+" - "+ @timeblock.out_time
        page.hours_entry(day).should == @ec_type+" - "+"2.00 hours"

    end
  end

end