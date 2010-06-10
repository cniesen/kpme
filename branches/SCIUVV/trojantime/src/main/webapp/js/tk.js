/**
 * If you need to change the theme base, the css file is: jquery-ui-1.8.1.custom.css
 */
$(document).ready(function() {

	// tabs
	/**
	 * This is the default tab function provided by jQuery
	 */
	// var $tabs = $('#tabs').tabs({ selected: 4});
	/**
	 * the default tab function provided by jQuery doesn't work well for us.
	 * It works very well if you have all the contents in the same page or load contents through ajax.
	 * Since we want the direct link for each tab, the function below takes advantages from the jQuery css theme/
	 *
	 * Noted that round corners for the tabs and the panel work in every browser except IE 7 and 8.
	 */
	$("#tabs li").hover(function() {
				$(this).addClass("ui-state-hover");
			}, function() {
				$(this).removeClass("ui-state-hover");
			});

	var tabId = $("#tabId").val();
	if (tabId != undefined) {
		$("#tabs ul > #" + tabId)
				.addClass("ui-tabs-selected ui-state-focus ui-state-active");
	}
	// end of tab

	// buttons
	$("input:button").button();

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	// calendar
	$('#cal').fullCalendar({
				theme : true,
				aspectRatio : 5, // the value here is just to match the height with the add time block panel
				allDaySlot : false,
				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'payPeriod'
				},
				events : [{
							title : 'RGN',
							start : new Date(y, m, d, 08, 00),
							end : new Date(y, m, d, 17, 00),
							allDay : false
						}, {
							title : 'Lunch',
							start : new Date(y, m, d, 12, 0),
							end : new Date(y, m, d, 13, 0),
							allDay : false
						}]
			});

 
	// datepicker
	$('#timesheet-beginDate, #timesheet-endDate').datepicker({
				changeMonth : true,
				changeYear : true,
				showOn : 'button',
				showAnim : 'fadeIn',
                buttonImage : 'kr/static/images/cal.gif',
                buttonImageOnly : true,
				buttonText : 'Choose a date',
				showButtonPanel : true,
				numberOfMonths :2,
				// set default month based on the current browsing month
				// appendText : '<br/>format: mm/dd/yyyy',
				constrainInput : false,
                minDate: -7,
                maxDate: +7
			});

    // select All
    $('#selectAll').click(function(){
        var checked_status = this.checked;
        $("input[name=selectedEmpl]").each(function()
        {
            this.checked = checked_status;
            $("input[name=selectedEmpl]").parent().parent().addClass("ui-state-focus");
        });

        if(checked_status == false) {
            $("input[name=selectedEmpl]").parent().parent().removeClass("ui-state-focus");
        }
    });

    // highlight row
    $("input[name=selectedEmpl]").click(function() {

        var checked_status = this.checked;

        if(checked_status) {
            $(this).parent().parent().addClass("ui-state-focus");
        }
        else {
            $(this).parent().parent().removeClass("ui-state-focus");
        }
    });
});