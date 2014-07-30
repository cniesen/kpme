Given(/^I am logged in as indiana hourly time keeping employee$/) do
  log_in 'inclock1'
  on ClockPage do |page|
    if (page.work_status.text != "Work Status : No previous clock information")
      @doc_id=page.document_id
      log_in 'admin'
      clear_existing_document(@doc_id)
      log_in 'inclock1'
    end
  end

end


When(/^I clock in and out hours$/) do

  @timeentry = create ClockTimeentryObject, :wait_time=>120
end

Then(/^time entry should be created in calendar page$/) do
  navigate_to_timedetail
  on KpmeCalendarPage do |page|

    @cal_date=@timeentry.initial_time.to_s[-42,2]
    @date_in=@timeentry.clock_in_work_sts.to_s[-42,2]
    @clock_in=@timeentry.clock_in_work_sts.to_s[-34,5]
    @in_noon=@timeentry.clock_in_work_sts.to_s[-25,2]
    @clock_out=@timeentry.clock_out_work_sts.to_s[-34,5]
    @out_noon=@timeentry.clock_out_work_sts.to_s[-25,2]

    page.calendar_text_entry(@cal_date).should include (@date_in)
    page.calendar_text_entry(@cal_date).should include ("IN-DEPT HR Work Area")
    page.calendar_text_entry(@cal_date).should include (@clock_in+" "+@in_noon+" - "+@clock_out+" "+@out_noon)

  end

end

And(/^I inquire the actual time entry$/) do
  @timeentry.inquire_time
end

Then(/^I should see all the exact time values$/) do
  on TimeInquiryPage do |page|

    calendar_date=@timeentry.clock_in_work_sts.to_s[33,@timeentry.clock_in_work_sts.length]
    calendar_date= Date.strptime(calendar_date, '%a, %B %d %Y %H:%M:%S %p, %Z').strftime('%m/%d/%Y')
    page.windows[1].use
    @list_no=(page.number_of_items)-2


    page.timeentry_table(@list_no).should include (calendar_date+" "+"IN-DEPT HR Work Area : $5.00 Rcd 0 IN-DEPT"+" "+@clock_in+" "+@in_noon+" "+calendar_date+" "+@clock_in+" "+@in_noon+" "+@clock_out+" "+@out_noon+" "+calendar_date+" "+@clock_out+" "+@out_noon)
    page.windows.last.close


  end

end