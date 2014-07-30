# Helper methods that don't properly belong elsewhere. This is
# a sort of "catch all" Module.
module Workflows


  def navigate_to_timedetail
    on ClockPage do |page|
      page.timedetail_tab
    end
  end

  def clear_existing_document(doc_id)

    on AdminMaintenancePage do |page|
      page.time_leave_link
      page.admin_actions
    end

    on AdminOperationsPage do |page|
      page.document_no.set doc_id
      page.submit

    end


  end

=begin
  # Site Navigation helpers...
  def go_to_rollover_details
    visit Enrollment do |page|
      page.view_rollover_details
    end
  end

  def go_to_perform_rollover
    visit Enrollment do |page|
      page.perform_rollover
    end
  end

  def go_to_create_population
    visit Enrollment do |page|
      page.manage_populations
    end
    on ManagePopulations do |page|
      page.create_new
    end
  end

  def go_to_manage_population
    visit Enrollment do |page|
      page.manage_populations
    end
  end

  def go_to_manage_reg_windows
    visit Enrollment do |page|
      page.manage_registration_windows
    end
  end

  def go_to_manage_course_offerings
    visit Enrollment do |page|
      page.manage_course_offerings
    end
  end

  def go_to_display_schedule_of_classes
    visit Enrollment do |page|
      page.schedule_of_classes
    end

  end

  def go_to_holiday_calendar
    visit Enrollment do |page|
      page.create_holiday_calendar
    end
  end


  def go_to_academic_calendar
    visit Enrollment do |page|
      page.create_academic_calendar
    end
  end

  def go_to_calendar_search
    visit Enrollment do |page|
      page.search_for_calendar_or_term
    end
  end

  def go_to_create_course_offerings
    visit Enrollment do |page|
      page.create_course_offerings
    end
  end

  def go_to_manage_soc
    visit Enrollment do |page|
      page.manage_soc
    end
  end

  def go_to_krms_components
    visit PortalMenu do |page|
      page.krms_components
    end
  end

  def go_to_manage_co_agendas
    visit PortalMenu do |page|
      page.krms_manage_co_agendas
    end
  end

  def go_to_krms_manage_course_offerings
    visit PortalMenu do |page|
      page.manage_course_offerings
    end
  end

  def go_to_state_propagation
    visit PortalMenu do |page|
      page.test_state_propagation
    end
  end
=end

  def log_in(user)
    current_user = ""
    sleep 5
    if !$distributed_env then
      visit PortalMenu do |page|
        current_user = page.current_logged_in_user_id
        #puts current_user
        #puts "#{page.logout_admin.exists?}"
        if (page.logout_admin.exists? == true && current_user == :no_user)
          page.logout_admin.click
          visit PortalMenu
        end
        if current_user == :no_user
          page.login_with user
        elsif current_user != user
          page.logout
          visit Login do |page|
            page.login_with user
          end
        end
      end
    end
  end
end