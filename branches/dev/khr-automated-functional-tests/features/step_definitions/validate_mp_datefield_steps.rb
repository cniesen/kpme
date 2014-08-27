When(/^I provide blank date and time in missed punch$/) do

  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "creating a missed punch clock action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => "",
                         :punched_time => ""




end


Then(/^error message on blank fields appear$/) do
  on MissedPunchPage do |page|
    page.validation_header.wait_until_present
    page.validation_message.text.should == "Missed Action Date (MM/DD/YYYY) is a required field.\nMissed Action Time is a required field."
    page.validation_header.text.should == "This page has 2 errors"
    page.close
  end
end


When(/^I provide a future date in missed punch$/) do


  in_date=(Time.now+86400).strftime("%m/%d/%Y")
  in_time=(Time.now+72000).strftime("%H:%M")


  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "creating a missed punch clock action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => in_date,
                         :punched_time => in_time
end



Then(/^error message on future date appears$/) do
  on MissedPunchPage do |page|
    page.validation_message.wait_until_present
    page.validation_message.text.should == "Missed Punch Action Date cannot be a future date."
    page.close
    end
end

When(/^I provide a past date in missed punch$/) do

  in_date=(Time.now-172800).strftime("%m/%d/%Y")



  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "creating a missed punch clock action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => in_date,
                         :punched_time => "9:00 AM"
end

Then(/^error message on past date appears$/) do
  on MissedPunchPage do |page|
    page.validation_message.wait_until_present
    page.validation_message.text.should include "The date/time is not valid. A Clock In Action cannot be 24 hours before the current time."
    page.close
  end
end


When(/^I provide a future time in missed punch$/) do

  in_date=(Time.now).strftime("%m/%d/%Y")
  in_time=(Time.now+7200).strftime("%H:%M")


  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "creating a missed punch clock action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => in_date,
                         :punched_time => in_time
end


Then(/^error message on time appears$/) do
  on MissedPunchPage do |page|
    page.validation_message.wait_until_present
    page.validation_message.text.should == "Missed Punch Action Time cannot be a future time."
    page.close
  end
end