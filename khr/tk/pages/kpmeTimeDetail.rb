 class TimeDetailPage

 	#attr_accessor :text_field, :btn

 	#URLS = { :kpme_login => "http://localhost:8080/kpme-dev" }

 	def initialize(browser)
 		@browser = browser

 	end

 	def page_title
    	@browser.title
  	end


  def goToTimeDetail
    @browser.goto 'http://localhost:8080/kpme-dev/TimeDetail.do'
  end

 	def checkTimeBlock(array)
    getTimeBlocks

    # puts @blocks.size
    counter = 0
    boolean = false

    array.each do |assignment|
      assignment_first = (assignment.partition(':').first).strip!
      assignment_short = assignment_first + "-Task" + assignment.partition('Task').last

      @blocks.each do |block|
        if assignment_short == block
          counter += 1
           if counter == array.size
            boolean = true
          end
         end
      end
    end
    return boolean
	end

  def getTimeBlocks
    @blocks = Array.new
    @browser.divs(:id, /show_.*/).each do |div|
      @blocks << div.text
    end
  end
end
