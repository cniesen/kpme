$(function() {
	$("#tabs").tabs();
	
	var rowCount = $("#timeCaptureRowCount").val();
	for (var i = 0; i < rowCount; i++){
		// first set the onchange function on all of them ... 
		$(".timeTypeSelect1" + i).change(function(){
			var num = $(this).attr("class").substring(15); 
			if ($(this).val() == "TIME_IN_OUT") 
				$(".timeOutClass1" + num).css("display", "block");
			else {
				$(".timeOutClass1" + num).css("display", "none");
				// empty the values
				for (var j = 0; j < 6; j++)
					$('input[name="timeCaptureRows['+num+'].dates['+j+'].hours2"]').val("");
			}
		});
		// do it for the other tab too
		$(".timeTypeSelect2" + i).change(function(){
			var num = $(this).attr("class").substring(15); 
			if ($(this).val() == "TIME_IN_OUT") 
				$(".timeOutClass2" + num).css("display", "block");
			else {
				$(".timeOutClass2" + num).css("display", "none");
				// empty the values
				for (var j = 7; j < 14; j++)
					$('input[name="timeCaptureRows['+num+'].dates['+j+'].hours2"]').val("");
			}
		});
		
		// then check if any of them are already set to TIME_IN_OUT
		if ($(".timeTypeSelect1" + i).val() == "TIME_IN_OUT"){
			var num = $(".timeTypeSelect1" + i).attr("class").substring(15); 
			$(".timeOutClass1" + num).css("display", "block");
		}
		// do it for the other tab too
		if ($(".timeTypeSelect2" + i).val() == "TIME_IN_OUT"){
			var num = $(".timeTypeSelect2" + i).attr("class").substring(15); 
			$(".timeOutClass2" + num).css("display", "block");
		}
	}

	// add change event for timeInputs
	$(".ti").change(function(){
		calculateTotals();
	});
	// calculate the totals on start
	calculateTotals();
});	

function calculateTotals(){
	var rowCount = $("#timeCaptureRowCount").val();

	// go through every day
	for (var i = 0; i < 14; i++){
		var totalHours = 0.0;
		// for every day go through each line
		for (var j = 0; j < rowCount; j++){
			// get the type of timeEntry
			var aux = i > 6 ? "2" : "1";
			var timeType = $(".timeTypeSelect" + aux + j).val();
			if (timeType == "TIME_IN_OUT"){
				// if we have timeType in out we gotta check 2 values
				var value1 = $('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').val();
				var value2 = $('input[name="timeCaptureRows['+j+'].dates['+i+'].hours2"]').val();
				
				// check if the value2 is empty
				if (value1 == ""){
					// if the second value is not empty we need a first value todo calculations
					if (value2 != ""){
						// mark it red
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "red");
					// if second value is empty too make sure we remove any error markings
					} else {
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "black");
					}
				}
				
				// check if value2 is empty
				if (value2 == ""){
					// if the frist value is not empty we need a second value todo calculations
					if (value1 != ""){
						// mark it red
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours2"]').css("border-color", "red");
					// if first value is empty too make sure we remove any error markings
					} else {
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours2"]').css("border-color", "black");
					}
				}
				
				// if value1 and 2 are not empty do calcs
				if (value1 != "" && value2 != "") {
					var date1;
					try {
						// parse date1
						date1 = new Date();
						var time = value1.match(/(\d+)(?::(\d\d))?\s*(p?)/i);
						date1.setHours(parseInt(time[1]) + (time[3] ? 12 : 0) );
						date1.setMinutes(parseInt(time[2]) || 0 );
					} catch (err) {
						alert(value1 + " is not parsable.");
						date1 = null;
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "red");
					}
					var date2;
					try {
						// parse date2
						date2 = new Date();
						var time = value2.match(/(\d+)(?::(\d\d))?\s*(p?)/i);
						date2.setHours(parseInt(time[1]) + (time[3] ? 12 : 0) );
						date2.setMinutes(parseInt(time[2]) || 0 );
					} catch (err) {
						alert(value2 + " is not parsable.");
						date2 = null;
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours2"]').css("border-color", "red");
					}
					// if both dates were parsed fine calculate the hours now
					if (date1 != null && date2 != null){
						var differenceInMs = date2.getTime() - date1.getTime();
						if (differenceInMs < 0) {
							$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "red");
							$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours2"]').css("border-color", "red");
							alert("Negative Time Entry!");
							continue;
						}
						var hours = differenceInMs / 1000 / 60 / 60;
						totalHours += parseFloat(hours.toFixed(2));
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "black");
						$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours2"]').css("border-color", "black");
					}
				}
				
			}
			else {
				// if we have a different time type than in/out we only gotta check the first value
				var value = $('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').val();
				var parsedValue = parseFloat(value);
				if (value == ""){
					$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "black");
					continue;
				}
				else if (isNaN(parsedValue)){
					$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "red");
					continue;
				} else if (!isNaN(parsedValue)){
					$('input[name="timeCaptureRows['+j+'].dates['+i+'].hours1"]').css("border-color", "black");
					totalHours += parsedValue;
					continue;
				}
			}
		}
		$("#totalField" + i).val(totalHours);
	}
}