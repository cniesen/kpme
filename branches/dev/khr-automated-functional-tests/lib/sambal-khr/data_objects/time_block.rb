class TimeBlockObject < DataFactory

  include DateFactory
  include StringFactory
  include Workflows
  include Utilities


  attr_accessor  :start_date,
                 :end_date,
                 :earn_code,
                 :in_time,
                 :out_time,
                 :assignment,
                 :hours,
                 :apply_time,
                 :amount,
                 :defer_add


  def initialize(browser, opts={})
    @browser = browser
    defaults ={
        #       end_date: "05/14/2014"
         defer_add: false
    }
    set_options(defaults.merge(opts))
  end

# creates all the data for a timeblock entry and clicks add
    def create

    check_existing_entry
    on TimeblockWidgetPage do |page|
      page.start_date.set @start_date
      page.end_date.set @end_date
      page.assignment.pick! @assignment
      page.earn_code.pick! @earn_code
      page.in_time.fit @in_time
      page.out_time.fit @out_time
      page.hours.fit @hours
      set_apply_time unless @apply_time.nil?
      page.amount.fit @amount
      page.add unless @defer_add
    end
  end

# checks for the existing time block and deletes
  def check_existing_entry
    on KpmeCalendarPage do |page|

        if @start_date!="" && @end_date!=""
          day_1 = Date.strptime(@start_date,'%m/%d/%Y')
          day_2 = Date.strptime(@end_date,'%m/%d/%Y')

          number_of_days = (day_2 - day_1).to_i + 1

          curr_day = 0
          while number_of_days > 0 do
            curr_day += 1
            delete_existing_entry(curr_day)
            number_of_days -= 1
          end

        end

      page.calendar_day
    end

  end

# clicks the checkbox based on the input received
  def set_apply_time
    on TimeblockWidgetPage do |page|
      page.across_days.fit checkbox_trans[@apply_time]
    end
end

# sets the checkbox based on the input received
  def checkbox_trans
    { "Yes" => :set, "No" => :clear }
  end

# updates the end date and start date based on the entry
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

# clicks the add button in the widget
  def add_time_block
    on TimeblockWidgetPage do |page|
      page.add
    end
  end

# clicks the day 1 i.e. first monday on calendar
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
       if page.assignment_type(curr_day) != ""
        page.delete_tb(curr_day)
        page.alert.ok
        sleep 5
       end
     end
  end



end