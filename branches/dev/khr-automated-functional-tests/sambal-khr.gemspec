spec = Gem::Specification.new do |s|
  s.name = 'sambal-khr'
  s.version = '0.0.1'
  s.summary = %q{Automated test framework for testing the Kuali HR}
  s.description = %q{This gem is used for creating test scripts for the Kuali HR.}
  s.files = Dir.glob("**/**/**")
  s.authors = ["Drema Swader", "Prakash Unnikrishnan Nair", "Jeremy Hanson"]
  s.email = %w{"dswader@vivantech.com", "pkunnikr@iu.edu", "jjhanso@iastate.edu"}
  s.homepage = 'https://wiki.kuali.org/display/KPME/Automated+Functional+Testing'
  s.add_dependency 'test-factory', '>= 0.0.2'
  s.required_ruby_version = '>= 1.9.2'
end