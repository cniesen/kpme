Given(/^I am logged in as indiana hourly time keeping employee$/) do
  log_in 'inclock1'


 on ClockPage do |page|
  if (page.work_status.text[0,24] == "Work Status : Clocked in")
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

    @cal_date=@timeentry.clock_in_work_sts.to_s[-42,2]
    @cal_date = @cal_date[1,1] if @cal_date < "10"
    @date_in=@timeentry.clock_out_work_sts.to_s[-42,2]
    @date_in = @date_in[1,1] if @date_in < "10"
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

    current_time_in=@timeentry.current_time_in.to_s[0,5]
    current_in_meridian=(@timeentry.current_time_in.to_s[9,2]).upcase
    current_time_out=@timeentry.current_time_out.to_s[0,5]
    current_out_meridian=(@timeentry.current_time_out.to_s[9,2]).upcase

    page.timeentry_table(@list_no).should include(calendar_date+" "+"IN-DEPT HR Work Area : $5.00 Rcd 0 IN-DEPT"+" "+@clock_in+" "+@in_noon+" "+calendar_date+" "+current_time_in+" "+current_in_meridian+" "+@clock_out+" "+@out_noon+" "+calendar_date+" "+current_time_out+" "+current_out_meridian)
    ((page.time_value(@list_no,2)[3,2].to_i)%6).should==0
    ((page.time_value(@list_no,4)[3,2].to_i)%6).should==0
    page.windows.last.close


  end

end