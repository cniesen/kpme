class TimeBlockObject < DataFactory

  include DateFactory
  include StringFactory
  include Workflows
#  include Utilities
  #require 'date'

  attr_accessor  :start_date, :end_date, :earn_code, :in_time, :out_time, :assignment, :hours, :apply_time


  def initialize(browser, opts={})
    @browser = browser
    defaults ={
        #       end_date: "05/14/2014"
    }
    set_options(defaults.merge(opts))
  end

# creates all the data for a timeblock entry and clicks add
    def create

    on KpmeCalendarPage do |page|
      day_1 = Date.strptime(@start_date,'%m/%d/%Y')
      day_2 = Date.strptime(@end_date,'%m/%d/%Y')

      number_of_days = (day_2 - day_1).to_i + 1
      initial_day = 1
      puts "number_of_days is #{number_of_days}"

      for curr_day in initial_day..number_of_days
        puts "current day #{curr_day}"
        delete_existing_entry(curr_day)
      end

      page.calendar_day
    end


    on TimeblockWidgetPage do |page|
      page.start_date.set @start_date
      page.end_date.set @end_date
      page.assignment.pick! @assignment
      page.earn_code.pick! @earn_code
      page.hours.set @hours
      set_apply_time unless @apply_time.nil?
      page.add
    end
  end

  def set_apply_time
    on TimeblockWidgetPage do |page|
      page.across_days.fit checkbox_trans[@apply_time]
    end
end

  def checkbox_trans
    { "Yes" => :set, "No" => :clear }
  end

# Updates the end date and start date based on the entry
  def edit (opts={})
    on TimeblockWidgetPage do |page|
      page.end_date.set opts[:end_date] unless opts[:end_date].nil?
      page.start_date.set opts[:start_date] unless opts[:start_date].nil?
      page.in_time.fit opts[:in_time]
      page.out_time.fit opts[:out_time]
      page.assignment.pick! opts[:assignment]
      page.earn_code.pick! opts[:earn_code]
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

# deletes the existing timeblock entry

  def delete_existing_entry(curr_day)
    on KpmeCalendarPage do |page|
      puts curr_day
      if page.assignment_type(curr_day) != ""
        puts "I am going to delete"
        page.delete_tb(curr_day)
        page.alert.ok
        sleep 5

       end
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