class TimeInquiryPage < BasePage

  action(:close) { |b| b.div(id: 'actualTime').input(value: 'Close').click }
  element(:timeentry_table) { |list_no,b| b.div(id: 'actualTime').table(class: 'actualtime-table datatable-100').tbody.tr(index: list_no).text }
  element(:number_of_items) { |b| b.div(id: 'actualTime').table(class: 'actualtime-table datatable-100').rows.length }
  element(:time_value) { |row,no,b| b.div(id: 'actualTime').table(class: 'actualtime-table datatable-100').tbody.tr(index: row).td(index: no).text }



  def time_difference(list_no)
    page_begintime=DateTime.strptime(timeentry_table(list_no)[-37,8],"%I:%M %p").strftime("%H:%M")
    page_endtime=DateTime.strptime(timeentry_table(list_no)[-8,8],"%I:%M %p").strftime("%H:%M")
    time_diff=((DateTime.strptime(page_endtime,"%H:%M")-DateTime.strptime(page_begintime,"%H:%M"))*1440.0).to_i
    return time_diff
  end





end