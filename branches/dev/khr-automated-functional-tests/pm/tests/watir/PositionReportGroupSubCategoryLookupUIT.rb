require 'watir-webdriver'
require 'page-object'
require_relative "../../../hub/pages/LoginPage.rb"
require_relative "../../pages/PositionReportGroupSubCategoryLookup.rb"

class PositionReportGroupSubCategoryLookupUIT

	# include PageObject::PageFactory
	# login = LoginPage.new(Watir::Browser)

	def browser
		return @b if @b
		@b = Watir::Browser.new :firefox
	end

	login = LoginPage.new(@browser)
	login.visit
	login.login("admin")



	# prgsc = PositionReportGroupSubCategoryLookup.new(Watir::Browser)
	# prgsc.gotoPage

	browser.close
end #PositionReportGroupSubCategoryLookupUIT
