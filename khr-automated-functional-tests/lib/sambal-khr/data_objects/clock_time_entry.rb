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
                   :clock_out_work_sts





 def initialize (browser,opts={})
   @browser = browser
   defaults ={
       wait_time: 120
   }
   set_options(defaults.merge(opts))
 end

 def create


   on ClockPage do |page|

     @assignment_name=page.assignment_name.text
     @initial_work_sts = page.work_status.text
     #@initial_time = Time.new.strftime('%a, %B %d %Y %H:%M:%S %p, %Z')
     page.clock_in.click
     @clock_in_work_sts = page.work_status.text
     sleep(@wait_time)
     page.clock_out.click
     @clock_out_work_sts = page.work_status.text



   end

 end


 def inquire_time

   on KpmeCalendarPage do |page|
     page.actual_time_inquiry
   end

 end


end