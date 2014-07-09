When(/^I add a time block with empty amount$/) do

  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)

  end
  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "ECA : ECA - Earn code with Amount"


end



Then(/^(.*?) error text should be displayed$/) do |error_type|

  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Amount field cannot be empty" if error_type == 'empty amount'
    page.validation_text.should == "Leave Amount should be numeric" if error_type == 'alphanumeric amount'
    page.validation_text.should == "Error : Amount cannot have more than two digits after decimal point." if error_type == 'invalid amount'
  end
end


When(/^I add a time block with alphanumeric amount$/) do

  navigate_to_timedetail

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "ECA : ECA - Earn code with Amount",
                      :amount => "10a"

end

When(/^I add a time block with invalid amount$/) do

  navigate_to_timedetail

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end

  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "ECA : ECA - Earn code with Amount",
                      :amount => "5.2365"

end


When(/^I add a time block with valid amount$/) do
  navigate_to_timedetail

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,1)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "ECA : ECA - Earn code with Amount",
                      :amount => "5.00"

end



Then(/^amount should appear in summary$/) do

  on KpmeCalendarPage do |page|
    page.amount_entry(1).should include (@timeblock.amount)
    page.summary_assignment_name(0,1).should == @timeblock.assignment
    page.summary_fieldvalue(0,'ECA',1).should == "$"+@timeblock.amount
    page.widget_entry(1)
  end


  on TimeblockWidgetPage do |page|

    page.start_date.value.should include (@timeblock.start_date)
    page.end_date.value.should include (@timeblock.end_date)
    page.assignment.selected?(@timeblock.assignment)
    page.earn_code.selected?(@timeblock.earn_code)
    page.amount.value.should == @timeblock.amount[0,1]
    page.close


  end


end


When(/^I create a time block not applied for all dates$/) do
  navigate_to_timedetail

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(1,2)
  end


  @timeblock = create TimeBlockObject,:start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :assignment => "IA-EC HR Work Area : $5.00 Rcd 0 IA-EC",
                      :earn_code => "ECA : ECA - Earn code with Amount",
                      :amount => "5.00",
                      :apply_time => "No"

end




Then(/^amount time block should appear for each day$/) do

  on KpmeCalendarPage do |page|
    day1 = 1
    day2 = 2

    for current_day in day1..day2
      #puts "current day is #{current_day}"
      page.summary_fieldvalue(0,'ECA',current_day).should include (@timeblock.amount)
      page.widget_entry(current_day)


      on TimeblockWidgetPage do |page1|

        if current_day == day1
          page1.start_date.value.should include (@timeblock.start_date)
          page1.end_date.value.should include (@timeblock.start_date)
        end
        if current_day == day2
          page1.start_date.value.should include (@timeblock.end_date)
          page1.end_date.value.should include (@timeblock.end_date)
        end
        page1.assignment.selected?(@timeblock.assignment)
        page1.earn_code.selected?(@timeblock.earn_code)
        page1.amount.value.should == @timeblock.amount[0,1]
        page1.close

      end
    end
  end


end


