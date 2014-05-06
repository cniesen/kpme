 require 'watir-webdriver'
 require 'page-object'
 require_relative "../../hub/pages/Lookup.rb"

 class PositionReportGroupSubCategoryLookup < Lookup

 	#attr_accessor :text_field, :btn

 	URL = { :kpme_login => "http://localhost:8080/kpme-dev/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.kpme.pm.pstnrptgrpsubcat.PositionReportGroupSubCategoryBo&showMaintenanceLinks=true" }

 	def initialize(browser)
 		@browser = browser
 	end

	def PositionReportGroupSubCategoryLookupTitle
		@browser.label(:xpath => "//*span[matches(.,'Position Report Group Sub Category Lookup')]")
	end

	def EffectiveDateLabel
		@browser.label(:xpath => "//*label[matches(.,'Effective Date:')]")
	end

	def GroupKeyLabel
		@browser.label(:xpath =>"//*label[matches(.,'Group Key:')]")
	end

	def GroupKeyLookupButton
		@browser.btn(:xpath => "//input[@name='lookupCriteria[groupKeyCode]']/following-sibling::input[1]")
	end

	def InstitutionLabel
		@browser.label(:xpath => "//*label[matches(.,'Institution:')]")
	end

	def InstitutionLookupButton
		@browser.btn(:xpath => "//input[@name='lookupCriteria[institutionCode]']/following-sibling::input[1]")
	end

	def LocationLabel
		@browser.label(:xpath => "//*label[matches(.,'Location:')]")
	end

	def LocationLookupButton
		@browser.btn(:xpath => "//input[@name='lookupCriteria[locationId]']/following-sibling::input[1]")
	end

	def PositionReportGroupSubCategoryLabel
		@browser.label(:xpath => "//*label[matches(.,'Position Report Group Sub Category:')]")
	end

	def PositionReportGroupLabel
		@browser.label(:xpath => "//*label[matches(.,'Position Report Group:')]")
	end
	
	def PositionReportGroupLookupButton
		@browser.btn(:xpath => "//input[@name='lookupCriteria[positionReportGroup]']/following-sibling::input[1]")
	end

	def PositionReportSubCategoryLabel
		@browser.label(:xpath => "//*label[matches(.,'Position Report Sub Category:')]")
	end

	def PositionReportSubCategoryField
		@browser.text_field(:name => "lookupCriteria[positionReportSubCat]")
	end

	def PositionReportSubCategoryLookupButton
		@browser.btn(:xpath => "//input[@name='lookupCriteria[positionReportSubCat]']/following-sibling::input[1]")
	end

	def ShowHistoryLabel
		@browser.label(:xpath => "//*label[matches(.,'Show History:')]")
	end

  def gotoPage
		driver.gotoPage URL
	end

end #PositionReportGroupSubCategoryLookup
