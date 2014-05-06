class LoginPage

 	#attr_accessor :text_field, :btn

  	URLS = { :kpme_login => "http://localhost:8080/kpme-dev" }

 	def initialize(browser)
 		@browser = browser

 	end

 	def visit
    	@browser.goto URLS[:kpme_login]
  	end

 	def userField
 		@browser.text_field(:name => "login_user")
 	end

 	def loginBtn
 		@browser.button(:id => "Rice-LoginButton")
 	end


 	def login(username)
 		enterLogin(username);
		clickLoginButton();
	end

	def enterLogin(username)
		userField.clear();
		userField.exists?
	    userField.set username
	    userField.value
	end

	def clickLoginButton
		loginBtn.exists?
		loginBtn.click();
	end
 end
