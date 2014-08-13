When(/^I cancel a delete operation of a time block$/) do

  @timeblock.remove_timeblock(@days,"true")

end



Then(/^the time block and summary values remain the same$/) do

  on KpmeCalendarPage do |page|


    page.time_last_text(@timeblock.assignment_number,@days).split("\n")[1].should == DateTime.strptime(@timeblock.in_time,'%I%P').strftime('%H:%M %p')+" - "+ DateTime.strptime(@timeblock.out_time,'%I%P').strftime('%H:%M %p')
    page.hours_entry(@days).should == @ec_type+" - "+"2.00 hours"
    page.summary_fieldvalue(@timeblock.assignment_number,@ec_type,@days).should == "2.00"
    page.regular_day_total(@days).should == "2.00"

  end

end


When(/^I delete a time block$/) do

  @timeblock.remove_timeblock(@days,"false")
end


Then(/^the time block and summary values are removed$/) do
  on KpmeCalendarPage do |page|
    page.assignment_count(@days).should == 2
  end
end