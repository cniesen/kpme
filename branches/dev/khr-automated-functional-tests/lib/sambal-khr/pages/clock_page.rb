class ClockPage < BasePage

  action(:timedetail_tab) { |b| b.li(id: 'timeDetail').link(text: 'Time Detail').click }
end