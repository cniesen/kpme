class TimeInquiryPage < BasePage

  action(:close) { |b| b.div(id: 'actualTime').input(value: 'Close').click }
  element(:timeentry_table) { |list_no,b| b.div(id: 'actualTime').table(class: 'actualtime-table datatable-100').tbody.tr(index: list_no).text }
  element(:number_of_items) { |b| b.div(id: 'actualTime').table(class: 'actualtime-table datatable-100').rows.length }


end