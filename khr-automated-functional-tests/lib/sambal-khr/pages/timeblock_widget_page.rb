class TimeblockWidgetPage < BasePage

  element(:start_date) { |b| b.text_field(id: 'startDate') }
  element(:end_date) { |b| b.text_field(id: 'endDate') }
  action(:add) { |b| b.button(text: 'Add').click }
  value(:validation_text) { |b| b.p(id: 'validation').text}
  element(:earn_code) { |b| b.select(id: 'selectedEarnCode') }
  element(:across_days) { |b| b.checkbox(id: 'acrossDays') }
  action(:close) { |b| b.span(text: 'close').click }

  element(:assignment) { |b| b.select(id: 'selectedAssignment') }
  element(:in_time) { |b| b.text_field(id: 'startTimeHourMinute') }
  element(:out_time) { |b| b.text_field(id: 'endTimeHourMinute') }

  element(:hours) { |b| b.text_field(id: 'hours') }
  element(:amount) { |b| b.text_field(id: 'amount') }

  element(:earn_code_cal) { |b| b.select(id: 'selectedEarnCode').option(value: 'CAL') }




end