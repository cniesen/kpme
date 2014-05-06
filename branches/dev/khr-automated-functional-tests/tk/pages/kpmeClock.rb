 class ClockPage

 	#attr_accessor :text_field, :btn

 	#URLS = { :kpme_login => "http://localhost:8080/kpme-dev" }

 	def initialize(browser)
 		@browser = browser

 	end

 	def page_title
    	@browser.title
  	end


 	def clockIn(list_no)
    # get clockin button
    btn = @browser.button(:id => "clock-button")

    # if clocked out then clock in
    btn_text = @browser.button(:id => "clock-button").value
    if btn_text == "Clock Out"
      btn.click
    end

    # verify button text is "Clock In"
    #assert btn_text.include? "Clock OIn"

    # select assignment info
    assignment = @browser.select_list(:id => 'selectedAssignment').options[list_no].click

    # click clockin button
    btn.click

    assignment_text = @browser.span(:id =>'assignment-value').text
    # puts assignment_text

   # collect status info
     # status = @browser.div(  :class =>'clock').
     # status = @browser.element_by_xpath("//div[@id='clock']/table/tbody/tr[3]/td[2]").value
     # status = @browser.element_by_xpath("//*[@id='assignment-value']")


    return assignment_text  
	end

  def clockOut
    # get clockout button
    btn = @browser.button(:id => "clock-button")
    # if clocked in then clock out
        btn_text = @browser.button(:id => "clock-button").value
    if btn_text == "Clock In"
     btn.click
    end
    # verify button text is "Clock Out"
    # click clockout button
      btn.click

  end
end
