Given(/^I am logged in as iowa hourly timekeeping employee with clock in work status$/) do
  @wait_sec=0
  log_in 'iaclock1'

  on ClockPage do |page|
    if (page.work_status.text != "Work Status : No previous clock information")
      @doc_id=page.document_id
      log_in 'admin'
      clear_existing_document(@doc_id)
      log_in 'iaclock1'
    end
    @timeentry = create ClockTimeentryObject, :wait_time=>@wait_sec,
                                              :clock_action=>"clock_in"
    sleep(300)
    page.missed_punch.click
  end


end


When(/^I submit a missed punch for a clock out$/) do

  in_date=(Time.now).strftime("%m/%d/%Y")
  in_time=(Time.now-3780).strftime("%I:%M %p")

  @missed_punch = create MissedPunchEntryObject, :note => "test for missed clock out action on a timeblock",
                         :missed_action => "Clock Out",
                         :punched_date => in_date,
                         :punched_time => in_time
  #@missed_punch.close
end

