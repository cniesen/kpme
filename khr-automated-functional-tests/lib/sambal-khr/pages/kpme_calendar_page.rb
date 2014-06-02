class KpmeCalendarPage < BasePage

  action(:calendar_day) { |b| b.td(id: 'day_1').click }
  action(:delete_tb) { |b| b.td(id: 'day_1').img(class: 'event-delete').click }

  element(:hours_entry) { |b| b.td(id: 'day_1').div(class: 'event-content').text }
  action(:widget_entry) { |b| b.td(id: 'day_1').div(class: 'event-title-true').div(id: /show_/).click }



end