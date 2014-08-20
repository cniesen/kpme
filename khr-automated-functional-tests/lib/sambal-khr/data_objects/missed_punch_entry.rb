class MissedPunchEntryObject < DataFactory

  include DateFactory
  include StringFactory
  include Workflows
  include Utilities


  attr_accessor  :punched_time,
                 :assignment_name,
                 :note,
                 :punched_date,
                 :missed_action




  def initialize (browser,opts={})
    @browser = browser
    defaults ={
    }
    set_options(defaults.merge(opts))
  end


  def create

    on MissedPunchPage do |page|

      page.assignment.pick @assignment_name
      page.missed_action.pick @missed_action
      page.date.send_keys @punched_date
      page.time.send_keys @punched_time
      page.note.set @note
      page.submit
      if page.alert.exists? == true
        page.alert.close
        sleep 3
      end


    end


  end

  def close
    on MissedPunchPage do |page|
      page.close
    end

  end

end


