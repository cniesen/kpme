class ClockTimeentryObject < DataFactory

  include DateFactory
  include StringFactory
  include Workflows
  include Utilities


    attr_accessor  :punch_intime,
                   :punch_outtime,
                   :assignment_name,
                   :initial_work_sts,
                   :doc_id,
                   :wait_time,
                   :clock_in_work_sts,
                   :clock_out_work_sts,
                   :clock_action





 def initialize (browser,opts={})
   @browser = browser
   defaults ={
       wait_time: 120,
       clock_action: "both"
   }
   set_options(defaults.merge(opts))
 end

 def create


   on ClockPage do |page|

     @assignment_name = page.assignment_name.text
     @initial_work_sts = page.work_status.text
     clock_entry(@clock_action)

   end

 end


 def inquire_time

   on KpmeCalendarPage do |page|
     page.actual_time_inquiry
   end

 end

  def clock_entry(action)
    #puts entry

    on ClockPage do |page|

      case action
        when "both"
          page.clock_in.click
          @clock_in_work_sts = page.work_status.text
          sleep(@wait_time)
          page.clock_out.click
          @clock_out_work_sts = page.work_status.text
        when "clock_out"
          sleep(@wait_time)
          page.clock_out.click
          @clock_out_work_sts = page.work_status.text
        when "clock_in"
          page.clock_in.click
          @clock_in_work_sts = page.work_status.text
          sleep(@wait_time)
      end

     end
  end


  def click_missedpunch
    on ClockPage do |page|
    page.missed_punch.click
    end
  end


end