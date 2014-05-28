class Login < PageFactory

  #page_url "#{$test_site}/portal.do?selectedTab=main"
  #page_url "#{$test_site}/kr-krad/launch?viewId=enrollmentHomeView&methodToCall=start"
  page_url "#{$test_site}"

  element(:username_field) { |b| b.text_field(:id=>"username") }
  # element(:password_field) { |b| b.text_field(:name=>"j_password") }
  element(:login_button) { |b| b.button(:value=>"LOGIN") }
  action(:logout) { |b| b.button(value: "Logout").click }

  def login_with username
    username_field.set username
    login_button.click
    sleep 5
  end

end