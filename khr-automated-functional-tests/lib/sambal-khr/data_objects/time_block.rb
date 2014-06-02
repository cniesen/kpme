class TimeBlockObject < DataFactory

  include DateFactory
  include StringFactory
  include Workflows
#  include Utilities
  #require 'date'

  attr_accessor  :start_date, :end_date, :earn_code, :in_time, :out_time, :assignment, :hours


  def initialize(browser, opts={})
    @browser = browser
    defaults ={
        #       end_date: "05/14/2014"
    }
    set_options(defaults.merge(opts))
  end

# Updates the end date and start date based on the entry
  def edit (opts={})
    on TimeblockWidgetPage do |page|
      page.end_date.set opts[:end_date] unless opts[:end_date].nil?
      page.start_date.set opts[:start_date] unless opts[:start_date].nil?
      page.in_time.fit opts[:in_time]
      page.out_time.fit opts[:out_time]
      page.earn_code.pick! opts[:earn_code]
      page.assignment.pick! opts[:assignment]
      page.hours.fit opts[:hours]

    end
    set_options(opts)
  end

# Clicks the add button in the widget
  def add_time_block
    on TimeblockWidgetPage do |page|
      page.add
    end
  end

# Clicks the day 1 i.e. first monday on calendar
  def select_date
    on KpmeCalendarPage do |page|
      page.calendar_day
    end
  end

# selects the time detail tab
  def select_timedetail
    on ClockPage do |page|
      page.timedetail_tab
    end
  end



# Adds the dates from page object and calls edit
=begin
  def add_dates
    on TimeblockPage do |page|

      ed_dt = page.end_date.value
      puts "ed dt in date #{ed_dt}"
      date1 = Date.strptime(ed_dt ,'%m/%d/%Y')
      st_dt = (date1 + 1).to_s
      puts "st_dt is #{st_dt}"
      new_startdate = Date.strptime(st_dt, '%Y-%m-%d').strftime("%m/%d/%Y")
      #new_startdate
     edit :start_date => new_startdate

      end

  end
=end




end