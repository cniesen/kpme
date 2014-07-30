class ClockPage < BasePage

  action(:timedetail_tab) { |b| b.li(id: 'timeDetail').link(text: 'Time Detail').click }
  element(:clock_in) { |b| b.input(id: 'clock-button') }
  element(:clock_out) { |b| b.input(id: 'clock-button',value: 'Clock Out') }
  element(:work_status) { |b| b.div(id: 'clock').tr(index: 2) }
  element(:assignment_name)  { |b| b.span(id: 'assignment-value') }
  element(:current_time) { |b| b.div(id: 'clock').div(class: 'jClock') }
  element(:missed_punch) { |b| b.div(id: 'clock').input(name: 'missedPunch') }
  element(:logout_link) { |b| b.link(text: 'Logout') }
  element(:document_id) { |b| b.div(id: 'tabs').div(class: 'person-info').table.tr(index: 3).td(index: 1).text }



end