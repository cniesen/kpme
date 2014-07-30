class AdminMaintenancePage < BasePage

  action(:time_leave_link)  { |b| b.link(text: 'Time & Leave Admin').click }
  action(:admin_actions)  { |b| b.link(text: 'Admin Actions').click }






end