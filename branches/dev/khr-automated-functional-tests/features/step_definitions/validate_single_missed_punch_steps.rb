Given(/^I am logged in as iowa hourly timekeeping employee with no work status$/) do
  log_in 'iaclock1'

  on ClockPage do |page|
    if (page.work_status.text != "Work Status : No previous clock information")
      @doc_id=page.document_id
      log_in 'admin'
      clear_existing_document(@doc_id)
      log_in 'iaclock1'
    end
   page.missed_punch.click
  end

end


When(/^I submit a missed punch$/) do

  in_date=(Time.now-86400).strftime("%m/%d/%Y")
  in_time=(Time.now-72000).strftime("%H:%M")


  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                                                 :note => "creating a missed punch clock action on a timeblock",
                                                 :missed_action => "Clock In",
                                                 :punched_date => in_date,
                                                 :punched_time => in_time


end


Then(/^missed punch document is successfully created$/) do

  on MissedPunchPage do |page|
     page.validation_header.wait_until_present
     page.validation_message.text.should == "Document was successfully saved.\nDocument was successfully submitted."
     page.validation_header.text.should == "This page has 2 messages"
     page.close
  end

  on ClockPage do |page|
    page.work_status.text[0,24].should include "Work Status : Clocked in"
  end


end


And(/^I clock out hours$/) do
  @timeentry = create ClockTimeentryObject, :wait_time=>1,
                                            :clock_action=>"clock_out"
end


Then(/^work status should change$/) do
  on ClockPage do |page|
    page.work_status.text[0,25].should include "Work Status : Clocked out"
  end
end



Given(/^I have a missed punch$/) do
  log_in 'iaclock1'

  on ClockPage do |page|
    if (page.work_status.text != "Work Status : No previous clock information")
      @doc_id=page.document_id
      log_in 'admin'
      clear_existing_document(@doc_id)
      log_in 'iaclock1'
    end
    page.missed_punch.click

  end

  in_date=(Time.now-86400).strftime("%m/%d/%Y")
  in_time=(Time.now-72000).strftime("%H:%M")


  @missed_punch = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "test allowing only one missed punch clock action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => in_date,
                         :punched_time => in_time
  @missed_punch.close

  @timeentry = create ClockTimeentryObject, :wait_time=>1,
                      :clock_action=>"clock_out"

  @timeentry.click_missedpunch


end



When(/^I submit a new missed punch$/) do

  in_date=(Time.now-86400).strftime("%m/%d/%Y")
  in_time=(Time.now-72200).strftime("%H:%M")


  @missed_punch1 = create MissedPunchEntryObject, :assignment_name => "IA-DEPT HR Work Area : $5.00 Rcd 0 IA-DEPT" ,
                         :note => "test allowing only one missed punch clock action on a timeblock",
                         :missed_action => "Clock In",
                         :punched_date => in_date,
                         :punched_time => in_time


end

Then(/^error message on missed punch appears$/) do

  on MissedPunchPage do |page|
    page.validation_message.wait_until_present
    page.validation_message.text.should == "The date/time is not valid. It cannot be before previous clock action, or 24 hours beyond a clock in."
    page.close
  end


end