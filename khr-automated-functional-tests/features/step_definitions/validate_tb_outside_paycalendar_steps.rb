When(/^I select dates prior to the pay calendar$/) do

    on KpmeCalendarPage do |page|
      @finalstart_dt,@finalend_dt = page.calc_dates(-15,-14)
    end

    @timeblock = create TimeBlockObject, :start_date => @finalstart_dt,
                        :end_date => @finalend_dt,
                        :defer_check_entry => true,
                        :defer_add => true
end


Then(/^error message about pay dates should appear$/) do
  on TimeblockWidgetPage do |page|
    page.validation_text.should == "Both dates must fall within this pay period"
    #page.assignment.selected?("-- no assignments found --").should == true
  end
end


When(/^I select dates later to the pay calendar$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(15,16)
  end

  @timeblock = create TimeBlockObject, :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :defer_check_entry => true,
                      :defer_add => true
end


When(/^I select start date earlier to the current pay period$/) do

  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(-2,1)
  end

  @timeblock = create TimeBlockObject, :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :defer_check_entry => true,
                      :defer_add => true
end


When(/^I select end date later to the current pay period$/) do
  on KpmeCalendarPage do |page|
    @finalstart_dt,@finalend_dt = page.calc_dates(12,15)
  end

  @timeblock = create TimeBlockObject, :start_date => @finalstart_dt,
                      :end_date => @finalend_dt,
                      :day_click => 12,
                      :defer_check_entry => true,
                      :defer_add => true
end