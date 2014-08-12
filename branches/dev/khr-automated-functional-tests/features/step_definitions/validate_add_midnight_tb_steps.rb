Given(/^I am logged in as iowa non\-exempt timekeeping employee$/) do
  log_in 'iadetail1'
end


When(/^I add a midnight time block to the start of pay period$/) do

  @cal_day = "0"
  @timeblock = make TimeBlockObject
  @timeblock.delete_existing_entry(@cal_day)
  @timeblock.select_date :@day_click => @cal_day
  @pay_stdt,@pay_enddt=@timeblock.retrieve_dates
  #puts @pay_stdt,@pay_enddt
  @timeblock.edit :edit_existing_entry => "false",
                  :in_time => "12:00 AM",
                  :out_time => "02:00 AM"
  @timeblock.add_time_block

end


Then(/^the time block should be created$/) do


   on KpmeCalendarPage do |page|

       if @cal_day =="0"
         Date.strptime(@pay_stdt, '%Y-%m-%d').strftime('%d').should include page.calendar_date(@cal_day)
       else
         Date.strptime(@pay_enddt, '%Y-%m-%d').strftime('%d').should include page.calendar_date(@cal_day)
       end

      page.widget_entry(@cal_day)
    end

    on TimeblockWidgetPage do |page|
       if @cal_day == "0"
         page.start_date.value[3,2].should==Date.strptime(@pay_stdt, '%Y-%m-%d').strftime('%d')
       else
         page.start_date.value[3,2].should==Date.strptime(@pay_enddt, '%Y-%m-%d').strftime('%d')
       end

      page.assignment.selected?("IA-DEPT NE Work Area : $5.00 Rcd 0 IA-DEPT").should == true
      page.earn_code.selected?("RGN : Regular Pay Non-Exempt").should == true
      page.in_time.value.should == @timeblock.in_time
      page.out_time.value.should == @timeblock.out_time
      page.close
    end



end


When(/^I add a midnight time block to the end of pay period$/) do


  on KpmeCalendarPage do |page|
    @start_dt,@end_dt = page.calc_dates(0,0)
  end
   pay_dt=@start_dt[3,2]
     if  pay_dt > "15"
       if @start_dt[0,2]<"10"
        month=@start_dt[1,1].to_i
       else
        month=@start_dt[0,2].to_i
       end
     days=Date.new(Time.now.year,month,-1).day
    @cal_day=days-(pay_dt.to_i)
  else
    @cal_day = "14"
  end


  @timeblock = make TimeBlockObject
  @timeblock.delete_existing_entry(@cal_day)
  @timeblock.select_date :@day_click => @cal_day
  @pay_stdt,@pay_enddt=@timeblock.retrieve_dates
  #puts @pay_stdt,@pay_enddt
  @timeblock.edit :edit_existing_entry => "false",
                  :in_time => "10:00 PM",
                  :out_time => "12:00 AM"
  @timeblock.add_time_block
end