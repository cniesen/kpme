class MissedPunchPage < BasePage

  element(:frame_class)  { |b| b.form(id: 'kualiLightboxForm').frame(class: 'fancybox-iframe') }
  action(:close) { |b| b.frame_class.button(text: 'Close').click }
  action(:submit) { |b| b.frame_class.button(text: 'Submit').click }
  element(:assignment) { |b| b.frame_class.div(id: 'Uif-PageContentWrapper').select(name: 'missedPunch.assignmentKey') }
  element(:missed_action) { |b| b.frame_class.div(id: 'Uif-PageContentWrapper').select(name: 'missedPunch.clockAction') }
  element(:date) { |b| b.frame_class.div(id: 'Uif-PageContentWrapper').text_field(name: 'missedPunch.actionDate') }
  element(:time) { |b| b.frame_class.div(id: 'Uif-PageContentWrapper').text_field(name: 'missedPunch.actionTime') }
  element(:note) { |b| b.frame_class.div(id: 'Uif-PageContentWrapper').text_field(name: 'missedPunch.note') }
  element(:validation_header) { |b| b.frame_class.h3(id: 'pageValidationHeader') }
  element(:validation_message) { |b| b.frame_class.ul(id: 'pageValidationList') }


end