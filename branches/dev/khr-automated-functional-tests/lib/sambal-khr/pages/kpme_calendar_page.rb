class KpmeCalendarPage < BasePage

  action(:calendar_day) { |b| b.td(id: 'day_1').click }
  action(:delete_tb) { |curr_day,b| b.td(id: "day_#{curr_day}").img(class: 'event-delete').click }
  value(:assignment_count)  { |curr_day,b| b.td(id: "day_#{curr_day}").divs.size }


  element(:hours_entry) { |day_count,b| b.td(id: "day_#{day_count}").div(class: "event-content").text }
  action(:widget_entry) { |day_count,b| b.td(id: "day_#{day_count}").div(class: 'event-title-true').div(id: /show_./).click }

  element(:pay_period)  { |b| b.select(id: 'selectedPayPeriod').option(selected: 'selected').text }

  value(:assignment_type) { |curr_day,b| b.td(id: "day_#{curr_day}").div(index: 1).text }
  element(:amount_entry) { |day_count,b| b.td(id: "day_#{day_count}").div(class: /approvals-table/).text }

  value(:summary_assignment) { |no,b| b.tbody(id: 'weekSummaryWeek1').td(class: "assignment#{no}").text }
  value(:summary_fieldvalue) { |no,ec_type,curr_day,b| b.td(id: "day#{curr_day}_#{ec_type}_assignment#{no}").text }
  value(:summary_assignment_bgcolor) { |no,b| b.tbody(id: 'weekSummaryWeek1').td(class: "assignment#{no}").style 'background-color'}





  def calc_dates(day_start,day_end)
    start_pay = pay_period.split('-').first
    date1 = Date.strptime(start_pay,'%m/%d/%Y')

    start_dt = (date1 + day_start).to_s
    finalstart_dt = Date.strptime(start_dt, '%Y-%m-%d').strftime('%m/%d/%Y')
    end_dt =  (date1 + day_end).to_s
    finalend_dt = Date.strptime(end_dt, '%Y-%m-%d').strftime('%m/%d/%Y')

    return finalstart_dt, finalend_dt


  end
  



end