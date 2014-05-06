require 'watir-webdriver'
require 'page-object'

class Lookup

	def initialize(browser)
 		@browser = browser
 	end

	def EffectiveDateFromField
		@browser.text_field(:name => "lookupCriteria[rangeLowerBoundKeyPrefix_effectiveDate]")
	end

	def EffectiveDateToField
		@browser.text_field(:name => "lookupCriteria[effectiveDate]")
	end

	def GroupKeyField
		@browser.text_field(:name => "lookupCriteria[groupKeyCode]")
	end

	def InstitutionCodeField
		@browser.text_field(:name => "lookupCriteria[institutionCode]")
	end

	def InstitutionField
		@browser.text_field(:name => "lookupCriteria[institutionCode]")
	end

	def LocationField
		@browser.text_field(:name => "lookupCriteria[locationId]")
	end

	def PositionReportGroupSubCategoryField
		@browser.text_field(:name => "lookupCriteria[pstnRptGrpSubCat]")
	end

	def PositionReportGroupField
		@browser.text_field(:name => "lookupCriteria[positionReportGroup]")
	end

	def PositionNumberField
		@browser.text_field(:name => "lookupCriteria[positionNumber]")
	end

	def results
		@browser.text(:className => "dataTables_info")
	end

	def SearchButton
		@browser.btn(:xpath => "//button[contains(text(), 'Search')]")
	end

	def ClearValuesButton
		@browser.btn(:xpath => "//button[contains(text(), 'Clear Values')]")
	end

	def CancelButton
		@browser.btn(:xpath => "//button[contains(text(), 'Cancel')]")
	end

	def CloseButton
		@browser.btn(:xpath => "//button[contains(text(), 'Close')]")
	end


end #Lookup