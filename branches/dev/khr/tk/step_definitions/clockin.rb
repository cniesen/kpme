require 'watir-webdriver'
require_relative "../../hub/pages/LoginPage.rb"
require_relative "../pages/kpmeClock.rb"
require_relative "../pages/kpmeTimeDetail.rb"

 def browser
	return @b if @b
	@b = Watir::Browser.new :firefox
end


Given(/^that a clock user with multiple assignments logs into Time Managment$/) do 
	 login = LoginPage.new(browser)
	 login.visit
	 login.login("timecoll")
end

When(/^she clocks in to multiple assignments and clocks out of the assignments$/) do
	clock = ClockPage.new(browser)
	@clockin_text_array = Array.new

	@clockin_text_array << clock.clockIn(1)
	sleep(20)
	clock.clockOut

	@clockin_text_array << clock.clockIn(2)
	sleep(20)
	clock.clockOut

end

Then(/^the Time Detail displays the correct assignments on the correct timeblocks$/) do

    timeDetail = TimeDetailPage.new(browser)
    sleep(3)

    timeDetail.goToTimeDetail

    
    boolean = timeDetail.checkTimeBlock(@clockin_text_array)

    if boolean==false 
    	close
    	fail("The timeblocks did not match the assignments")
    end

    close
end




 def close
	browser.close
	browser = false
	return true
end