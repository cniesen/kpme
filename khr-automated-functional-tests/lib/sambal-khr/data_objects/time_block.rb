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
                 :defer_add,
                 :defer_check_entry,
                 :defer_pick_assignment,
                 :assignment_number,
                 :current_day,
                 :cal_day,
                 :edit_existing_entry,
                 :day_click


  def initialize(browser, opts={})
    @browser = browser
    defaults ={
        #       end_date: "05/14/2014"
         defer_add: false,
         defer_check_entry: false,
         defer_pick_assignment: false,
         current_day: 0,
         edit_existing_entry: "false",
         day_click: 1



    }
    set_options(defaults.merge(opts))
  end

# creates all the data for a timeblock entry and clicks add
  def create

    check_existing_entry unless @defer_check_entry
    #on(KpmeCalendarPage).calendar_day
    on(KpmeCalendarPage).calendar_day(@day_click)

    on TimeblockWidgetPage do |page|

      # send_keys is done so that the assignment gets loaded because the start date text does not have to be cleared
      if @start_date !=""
        page.start_date.send_keys([:control, 'a'])
        #page.start_date.send_keys([:command, 'a'])
        page.start_date.send_keys @start_date
      else
        page.start_date.set @start_date
      end

      if @end_date !=""
        page.end_date.send_keys([:control, 'a'])
        #page.start_date.send_keys([:command, 'a'])
        page.end_date.send_keys @end_date
      else
        page.end_date.set @end_date
      end

      page.start_date.click
      page.end_date.click

      if @defer_pick_assignment == false
        page.assignment.wait_until_present
        page.assignment.pick! @assignment  #some user profiles do not want assignment to be selected
      end
      page.earn_code.pick! @earn_code

      in_time_values unless @in_time.nil?
      out_time_values unless @out_time.nil?

      page.hours.fit @hours
      set_apply_time unless @apply_time.nil?
      page.amount.fit @amount
      page.add unless @defer_add
    end
  end

  def in_time_values
    on TimeblockWidgetPage do |page|
      if @in_time !=""
       page.in_time.send_keys([:control, 'a'])
       page.in_time.send_keys([:command, 'a'])
       page.in_time.send_keys @in_time
      else
       page.in_time.set @in_time
      end
   end
  end

  def out_time_values
    on TimeblockWidgetPage do |page|
      if @out_time !=""
        page.out_time.send_keys([:control, 'a'])
        page.out_time.send_keys([:command, 'a'])
        page.out_time.send_keys @out_time
      else
        page.out_time.set @out_time
      end
    end
  end


# checks for the existing time block for the entire pay period and deletes
  def check_existing_entry

        if @start_date!="" && @end_date!=""
          on KpmeCalendarPage do |page|
            pay_startdt = page.pay_period.split('-').first
            date_1 = Date.strptime(pay_startdt,'%m/%d/%Y')
            pay_enddt = page.pay_period.split('- ').last
            date_2 = Date.strptime(pay_enddt,'%m/%d/%Y')

          #day_1 = Date.strptime(@start_date,'%m/%d/%Y')
          #day_2 = Date.strptime(@end_date,'%m/%d/%Y')
          #number_of_days = (day_2 - day_1).to_i + 1
          #puts "no : #{number_of_days}"

          number_of_days = (date_2 - date_1).to_i + 1

          while number_of_days > 0  do
            delete_existing_entry(@current_day) if page.assignment_count(@current_day) > 2
            @current_day += 1
            number_of_days -= 1
          end

        end
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

# edits the entry of time block widget
  def edit (opts={})

    on(KpmeCalendarPage).widget_entry(opts[:cal_day])  if opts[:edit_existing_entry] == "true"

    on TimeblockWidgetPage do |page|

      page.end_date.set opts[:end_date] unless opts[:end_date].nil?
      page.start_date.set opts[:start_date] unless opts[:start_date].nil?
      page.in_time.fit opts[:in_time]
      page.out_time.fit opts[:out_time]
      page.assignment.pick! opts[:assignment]
      page.earn_code.pick! opts[:earn_code]
      page.across_days.fit checkbox_trans[opts[:apply_time]]
      page.hours.fit opts[:hours]

    end
    set_options(opts)
  end

# clicks the add button in the widget
  def add_time_block
    on TimeblockWidgetPage do |page|

      page.add if @edit_existing_entry == "false"
      page.update if @edit_existing_entry == "true"
    end
  end

# clicks the day 1 i.e. first monday on calendar
  def select_date
    on KpmeCalendarPage do |page|
      page.calendar_day(@day_click)
    end
  end

#removes the only timeblock for that day
  def remove_timeblock(cal_day,cancel)
    on KpmeCalendarPage do |page|
      if cancel == "false"
        page.delete_tb(cal_day)
        page.alert.ok
      else
        page.delete_tb(cal_day)
        page.alert.close
      end
      if page.alert.exists? == true
        #puts " server dialog present"
        page.alert.close
        sleep 5
      end
    end
  end



# deletes all the existing timeblock entry
  def delete_existing_entry(current_day)
    on KpmeCalendarPage do |page|
      while page.assignment_count(current_day) > 2  do
        #puts curr_day
        page.delete_tb(current_day)
        page.alert.ok
         if page.alert.exists? == true
            page.alert.close
            sleep 5        # this sleep is to prevent browser's dialog
         end
       end
      end
    end
  end