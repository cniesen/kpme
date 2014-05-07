require 'test-factory'

$: << File.dirname(__FILE__)+'/sambal-khr'
require 'sambal-khr/kuali_base_page'
Dir["#{File.dirname(__FILE__)}/sambal-khr/*.rb"].each {|f| require f }
#Dir["#{File.dirname(__FILE__)}/sambal-khr/pages/*.rb"].each {|f| require f }
Dir["#{File.dirname(__FILE__)}/sambal-khr/pages/**/*.rb"].each {|f| require f }
Dir["#{File.dirname(__FILE__)}/sambal-khr/data_objects/*.rb"].each {|f| require f }

