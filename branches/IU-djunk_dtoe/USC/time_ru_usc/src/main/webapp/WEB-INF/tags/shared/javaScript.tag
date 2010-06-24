<%@ include file="/jsp/tlds.jsp"%>

<script language="javascript">

var helpParams = "'toolbar=no,status=yes,location=no,menubar=no,scrollbars=yes,resizable=yes,directories=no,left=0,top=0,width=500,height=500";
var windowParameters = "toolbar=no,status=yes,location=no,menubar=no,scrollbars=yes,resizable=yes,directories=no,left=0,top=0,height=" + screen.availHeight + ",width=" + screen.availWidth;
var open_file 	= 'jsp/images/tinybutton-hide.gif';
var closed_file	= 'jsp/images/tinybutton-show.gif';
img1 = new Image();
img1.src = closed_file;
img2 = new Image();
img2.src = open_file;

function openNewWindow(url) {
    var activeWindow = window.open(url, "_blank", windowParameters);
    activeWindow.focus();
}
function openNewWindow(url,name) {
    var activeWindow = window.open(url, name, windowParameters);
    activeWindow.focus();
}

function openHelpWindow(url) {
    var activeWindow = window.open(url, "_blank", helpParams);
    activeWindow.focus();
}
  
<!--expands/collapses tab panel and keeps track of its status-->
function toggleTab(obj,cc,nestingPath,recordIndex){
    fullPath=nestingPath+"["+recordIndex+"].tabStatus";
    try{
      if (eval('document.forms[0]["'+fullPath+'"].value') == "true"){
           eval('document.forms[0]["'+fullPath+'"].value="false"');
       } else {
           eval('document.forms[0]["'+fullPath+'"].value="true"');
       }
    }catch(e){}

    var len = ((String)(obj.id)).indexOf('-',0)-1;
    if (len == -2)
      len = ((String)(obj.id)).length;
    var index = ((String)(obj.id)).substr(1, len);

    if(document.getElementById) {
      var grpIdx = document.getElementById("G"+index);
      var fldIdx = document.getElementById("F"+index);
      var lnkIdx = document.getElementById("A"+index);
    } else if (document.all) {
      var grpIdx = eval("document.all.G"+index);
      var fldIdx = eval("document.all.F"+index);
      var lnkIdx = eval("document.all.A"+index);
    } else {
      alert('This browser is not supported by this tree...');
      return;
    }
        if (grpIdx.style.display == "none") {
            grpIdx.style.display = "";
            fldIdx.src = open_file;
        } else {
           grpIdx.style.display = "none";
           fldIdx.src = closed_file;
        }
    return;
}

var total_G_Sections = 150; //estimated value

function expandAllTabs() {
   for (index=1; index < total_G_Sections; index++) {
       if (document.getElementById) {
         var grpIdx = document.getElementById("G"+index);
       } else if (document.all) {
         var grpIdx = eval("document.all.G"+index);
        }
        try {
          expandTab(index);
        } catch(e){}
    }
    for(i=0; i<document.forms[0].elements.length; i++){
          if (document.forms[0].elements[i].name.indexOf('tabStatus')>=0) {
             document.forms[0].elements[i].value='true';
           }
     }     
}

function collapseAllTabs() {
   for (index=1; index < total_G_Sections; index++) {
       if (document.getElementById) {
         var grpIdx = document.getElementById("G"+index);
        } else if (document.all) {
         var grpIdx = eval("document.all.G"+index);
       }
        try {
          collapseTab(index);
        } catch(e){}
    }
    for(i=0; i<document.forms[0].elements.length; i++){
          if (document.forms[0].elements[i].name.indexOf('tabStatus')>=0) {
             document.forms[0].elements[i].value='false';
           }
     }    
}

<!--Collapses a specific tab based on id-->
function collapseTab(index) {
      if (document.getElementById) {
            var grpIdx = document.getElementById("G" + index);
            var fldIdx = document.getElementById("F" + index);
            var lnkIdx = document.getElementById("A" + index);
        } else {
            if (document.all) {
                var grpIdx = eval("document.all.G" + index);
                var fldIdx = eval("document.all.F" + index);
                var lnkIdx = eval("document.all.A" + index);
            } else {
                alert("This browser is not supported by this tree...");
                return;
            }
        }
       grpIdx.style.display = "none";
       fldIdx.src = closed_file;      
}
<!--Expand tab based on id-->
function expandTab(index) {
      if (document.getElementById) {
            var grpIdx = document.getElementById("G" + index);
            var fldIdx = document.getElementById("F" + index);
            var lnkIdx = document.getElementById("A" + index);
        } else {
            if (document.all) {
                var grpIdx = eval("document.all.G" + index);
                var fldIdx = eval("document.all.F" + index);
                var lnkIdx = eval("document.all.A" + index);
            } else {
                alert("This browser is not supported by this tree...");
                return;
            }
        }
            grpIdx.style.display = "";
            fldIdx.src = open_file;       
}

function rend(obj, cc) {
    var len = ((String)(obj.id)).indexOf('-',0)-1;
    if (len == -2)
      len = ((String)(obj.id)).length;
    var index = ((String)(obj.id)).substr(1, len);

    if(document.getElementById) {
      var grpIdx = document.getElementById("G"+index);
      var fldIdx = document.getElementById("F"+index);
      var lnkIdx = document.getElementById("A"+index);
    } else if (document.all) {
      var grpIdx = eval("document.all.G"+index);
      var fldIdx = eval("document.all.F"+index);
      var lnkIdx = eval("document.all.A"+index);
    } else {
      alert('This browser is not supported by this tree...');
      return;
    }

    if (grpIdx.style.display == 'none') {
      grpIdx.style.display = '';
      if(cc){
        fldIdx.src = open_file_cc;
      } else {
        fldIdx.src = open_file;
      }
    } else {
      grpIdx.style.display = 'none';
      if(cc){
        fldIdx.src = closed_file_cc;
      } else {
        fldIdx.src = closed_file;
      }
    }
    return;
}


function doedit(obj) { return }


<!--timesheet related javascript { -->

 <jsp:useBean id="serverTimeBean" class="java.util.Date"/>
  var clientTimeAtLoad = new Date();
  var serverTimeAtLoad = 0;
  var clientLocationRawOffset = clientTimeAtLoad.getTimezoneOffset()*60*1000;
  var serverLocationRawOffset = 0;
  var clientServerOffset = 0;
  var assignmentOffsetFromServerTime = 0;
  var dayNames=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
  var monthNames=new Array('January','February','March','April','May','June','July','August','September','October','November','December');
 
 function displayServerTime() {
    var clientNow = new Date();    
    var millisSinceLoad = (clientNow - clientTimeAtLoad);
	var serverNow = new Date(parseInt(serverTimeAtLoad) + parseInt(millisSinceLoad) + parseInt(assignmentOffsetFromServerTime) + parseInt(clientServerOffset));

    try {
    document.getElementById('clock').innerHTML=' '
                   +dayNames[serverNow.getDay()]+' '
                   +monthNames[serverNow.getMonth()]+' '
                   +leadingZero(serverNow.getDate())+', '
                   +fixYear4(serverNow.getYear())+' '
                   +twelveHour(serverNow.getHours())+':'
                   +leadingZero(serverNow.getMinutes())+' '
                   +amPMsymbol(serverNow.getHours()); 
   } catch (e) {}

    setTimeout('displayServerTime()',1000); 
 }
 
 function initiateDisplayTimeOffset() {
    var offset = 0;
	if (document.getElementById("timesheetDocument.clock.clockAssignment")) {
	    if (document.getElementById("timesheetDocument.clock.clockAssignment").type == "select-one") {
		    var index  = document.getElementById("timesheetDocument.clock.clockAssignment").selectedIndex;
		    if (index < 0) {
		        offset = document.getElementById("timesheetDocument.userLocationPreferenceOffset").value;
		    } else {
		    	offset = document.getElementById("timesheetDocument.userLocationPreferenceOffset").value;
		    }
	    } else {
	        offset = getAssignmentTimeOffset(document.getElementById("timesheetDocument.clock.clockAssignment").value);
	    }
		
		serverLocationRawOffset = document.getElementById("timesheetDocument.serverLocationTimeOffset").value;
		serverTimeAtLoad = document.getElementById("timesheetDocument.serverTimeMillisAtLoad").value;
		assignmentOffsetFromServerTime =  parseInt(offset) - parseInt(serverLocationRawOffset);
		clientServerOffset = parseInt(serverLocationRawOffset) + parseInt(clientLocationRawOffset);

	}
 }
 
 function calculateDisplayTimeOffset(assignment) {
    var dropDownBox = document.getElementById(assignment);
    var boxValue = dropDownBox.options[dropDownBox.selectedIndex].value;
	var assignmentTimeOffset = getAssignmentTimeOffset(boxValue);
	var serverLocationTimeOffset = document.getElementById("timesheetDocument.serverLocationTimeOffset").value;	
	assignmentOffsetFromServerTime = parseInt(assignmentTimeOffset) - parseInt(serverLocationTimeOffset);	
 }

 function getAssignmentTimeOffset(assignment) {
	var assignmentTimeOffsets = document.getElementById("timesheetDocument.assignmentTimeOffsets").value;
	var assignmentTimeOffsetsList = assignmentTimeOffsets.split("|");
	for(i=0;i<assignmentTimeOffsetsList.length;i+=2) {
		if (assignmentTimeOffsetsList[i] == assignment) {	return assignmentTimeOffsetsList[i+1]; }
	}
	return "0";
 }
 
 function leadingZero(x){
    return (x>9)?x:'0'+x;
 }
 function twelveHour(x){
    if(x==0){
       x=12;
    }
    return (x>12)?x-=12:x;  
 }
 function amPMsymbol(x){
    return (x>11)?'PM':'AM';  
 }
 function fixYear4(x){
    return (x<500)?x+1900:x;
 }
  
 
function reloadHourglass() {
    document.images[0].src=""
    document.images[0].src="config/Hourglass.gif"
 } 
 function leadingZero(x){
    return (x>9)?x:'0'+x;
 }
 function twelveHour(x){
    if(x==0){
       x=12;
    }
    return (x>12)?x-=12:x;  
 }
 function amPMsymbol(x){
    return (x>11)?'PM':'AM';  
 }
 function fixYear4(x){
    return (x<500)?x+1900:x;
 }

var assignmentEarnCodes = new Array();

function getEarnCodes(assignment)
{
	var assignmentEarnCodes = document.getElementById("timesheetDocument.assignmentEarnCodes").value;
	var assignmentEarnCodesList = assignmentEarnCodes.split("|");
	for(i=0;i<assignmentEarnCodesList.length;i+=2) {
		//alert("this.assignment=" + assignment + " next in list=" + assignmentEarnCodesList[i] + " equal? " + (assignmentEarnCodesList[i] == assignment));
		if (assignmentEarnCodesList[i] == assignment) {	return assignmentEarnCodesList[i+1]; }
	}
	return "No Earn Code Found";
}

<!-- this needs to get a list of earnCodes based on selected assignment key -->
function populateEarnCodes(name)
{
	var assignmentSelectBox = document.getElementById(name);
	var earnCodeSelectBox = document.getElementById(name.substring(0,name.lastIndexOf("."))+".earnCode");
	try 
	{
		var assignmentKey = assignmentSelectBox.options[assignmentSelectBox.selectedIndex].value;
	} catch (e) { var assignmentKey = assignmentSelectBox.value; }

	if (!assignmentKey) 
	{
		earnCodeSelectBox.options.length = 0;
		earnCodeSelectBox.options[0] = new Option("<< Select Assignment First","");
		return;
	}
	var earnCodeCSV = getEarnCodes(assignmentKey);
	var list = earnCodeCSV.split(",");
	var selectedEarnCode = earnCodeSelectBox.options[earnCodeSelectBox.selectedIndex].value;
	var foundSelectedEarnCode = false;
	earnCodeSelectBox.disabled = false;
	earnCodeSelectBox.options.length = 0;
	earnCodeSelectBox.options[0] = new Option("Select Earn Code","");

	if (assignmentKey == '') return;
	var i = 0;
	for(;i<list.length;i+=2)
	{
		earnCodeSelectBox.options[(i/2)+1] = new Option(list[i+1],list[i]);
		if (list[i] == selectedEarnCode) 
		{ 
			earnCodeSelectBox.selectedIndex = (i/2)+1; 
			foundSelectedEarnCode = true;
		}
	}
	
	if (!foundSelectedEarnCode && selectedEarnCode != "")
	{
		earnCodeSelectBox.options[(i/2)+2] = new Option(selectedEarnCode, selectedEarnCode);
		earnCodeSelectBox.selectedIndex = (i/2)+2; 
		setEditableFieldsByParameters(name.substring(0,name.lastIndexOf(".")), selectedEarnCode, foundSelectedEarnCode);
	} else {
		setEditableFieldsByParameters(name.substring(0,name.lastIndexOf(".")), selectedEarnCode, true);
	}
	

}

function setEditableFields(name)
{
	var earnCodeSelectBox = document.getElementById(name);
	var earnCodeValue = earnCodeSelectBox.options[earnCodeSelectBox.selectedIndex].value;
	setEditableFieldsByParameters(name.substring(0,name.lastIndexOf(".")), earnCodeValue, true)
}

function setEditableFieldsByParameters(nestingPath, earnCode, foundSelectedEarnCode)
{
	var hoursEntryEarnCodes = document.getElementById("timesheetDocument.hoursEntryEarnCodes").value;
	var hoursEntryEarnCodesList = hoursEntryEarnCodes.split(",");
	var hoursEntryFound = false;
	var beginHour 	= document.getElementById(nestingPath + ".beginTime.hour");
	var beginMinute = document.getElementById(nestingPath + ".beginTime.minute");
	var endHour 	= document.getElementById(nestingPath + ".endTime.hour");
	var endMinute 	= document.getElementById(nestingPath + ".endTime.minute");
	var hours   	= document.getElementById(nestingPath + ".hours");
	for(i=0;i<hoursEntryEarnCodesList.length;i++) {
		if (hoursEntryEarnCodesList[i] == earnCode) 
		{
		hoursEntryFound = true;
		i = 999;
		}
	}

	if (beginHour.readOnly != hoursEntryFound) 
	{
	beginHour.value = "12";
	beginMinute.value = "00";
	endHour.value = "12";
	endMinute.value = "00";
	}
	beginHour.readOnly 	 = hoursEntryFound;
	beginMinute.readOnly = hoursEntryFound;
	endHour.readOnly 	 = hoursEntryFound;
	endMinute.readOnly 	 = hoursEntryFound;
	hours.readOnly 		 = !hoursEntryFound;

	if (!foundSelectedEarnCode) //higher level entry - lock down the row
	{
		beginHour.readOnly 	= true;
		beginMinute.readOnly = true;
		endHour.readOnly 	= true;
		endMinute.readOnly 	= true;
		hours.readOnly 		= true;
		
		//clear all but selected option
		var assignmentSelectBox = document.getElementById(nestingPath + ".assignment");
		var selectedAssignmentOption = assignmentSelectBox.options[assignmentSelectBox.selectedIndex];
		assignmentSelectBox.options.length = 0;
		assignmentSelectBox.options[0] = selectedAssignmentOption;
		assignmentSelectBox.selectedIndex = 0;
		
		var earningSelectBox = document.getElementById(nestingPath + ".earnCode");
		earningSelectBox.options.length = 0;
		earningSelectBox.options[0] = new Option(earnCode + ": Clock/TIME Approver Only", earnCode);
		earningSelectBox.selectedIndex = 0;
		
		var beginAmPmSelectBox 	= document.getElementById(nestingPath + ".beginTime.amPm");
		var selectedBeginAmPm = beginAmPmSelectBox.options[beginAmPmSelectBox.selectedIndex];
		beginAmPmSelectBox.options.length = 0;
		beginAmPmSelectBox.options[0] = selectedBeginAmPm;
		beginAmPmSelectBox.selectedIndex = 0;
		
		var endAmPmSelectBox = document.getElementById(nestingPath + ".endTime.amPm");
		var selectedEndAmPm = endAmPmSelectBox.options[endAmPmSelectBox.selectedIndex];
		endAmPmSelectBox.options.length = 0;
		endAmPmSelectBox.options[0] = selectedEndAmPm;
		endAmPmSelectBox.selectedIndex = 0;
	}	
}

function initializeEarnCodeSelection(name){
	var earnCodeSelectBox = document.getElementById(name.substring(0,name.lastIndexOf("."))+".earnCode");
	earnCodeSelectBox.selectedIndex	= 0;
}

function initializeEarnCodeDropdowns()
{
	for(outer=0;outer<14;outer++)
	{
		for(inner=0;inner<99;inner++)
		{
			try {
			populateEarnCodes("timesheetDocument.hours.hoursDetail[" + outer + "].hourDetail[" + inner + "].assignment");
			} catch(e) { inner = 99; }
		}
	}
}

function setLinksToNotRegisterUnload(){
	if(navigator.appName == "Microsoft Internet Explorer"){
		var x = document.getElementsByTagName('A');
		 for (var i=0;i<x.length;i++)
		 {		
			x[i].attachEvent('onclick', onClickFunc);
		 }		
	}
} 

function onBeforeUnloadEventBrowserTest(){
	if (navigator.appName == "Microsoft Internet Explorer"){
		if (linkWasClicked == true){
			linkWasClicked = false;
		}else{
			return confirmSave();
		}
	}
	else if (navigator.appName == "Netscape"){
		return confirmSave();	
	}
	return;
	
}

var subProp = null;

function confirmSave(){
  <c:if test="${StrutsActionForm.mode.editable && StrutsActionForm.modeApplicable}">
	if(subProp != null){
		subProp = null;
		return;
	}else
		return "Please use the Save or Close button to exit the Timesheet.";
  </c:if>
}

var linkWasClicked = false;

function onClickFunc(){
	linkWasClicked = true;
}
<!-- } timesheet related javascript -->

function scrollToCoordinates() { 
   if(document.forms[0].scrollx && document.forms[0].scrollx.value != null && document.forms[0].scrollx.value.length >0){
	   window.scrollTo(document.forms[0].scrollx.value, document.forms[0].scrolly.value); 
  }
} 

function setScrollCoordinates() { 
 if(document.forms[0].scrollx){ 
  document.forms[0].scrollx.value = (document.all)?document.documentElement.scrollLeft:window.pageXOffset; 
  document.forms[0].scrolly.value = (document.all)?document.documentElement.scrollTop:window.pageYOffset; 
 }
} 

function postToOperation(operation){
		document.forms[0].action = operation;
		document.forms[0].onSubmit = setScrollCoordinates();
		subProp = operation; 
		if (typeof finishedLoadingPage == "undefined" || finishedLoadingPage) {
	       document.forms[0].submit();
	    }
}

function detectBrowser() {
/*
Script Name: Full Featured Javascript Browser/OS detection
Authors: Harald Hope, Tapio Markula, Websites: http://techpatterns.com/
http://www.nic.fi/~tapio1/Teaching/index1.php3
Script Source URI: http://techpatterns.com/downloads/javascript_browser_detection.php
Version 4.2.3
Copyright (C) 20 November 2006

// Original function modified to return an array with the browser's name and version. 
// To access these values use browser['name'] and browser['version'] respectively.
***************************************************************/

//initialization, browser, os detection
var d, dom, nu='', brow='', ie, ie4, ie5, ie5x, ie6, ie7;
var ns4, moz, moz_rv_sub, release_date='', moz_brow, moz_brow_nu='', moz_brow_nu_sub='', rv_full=''; 
var mac, win, old, lin, ie5mac, ie5xwin, konq, saf, op, op4, op5, op6, op7;

d=document;
n=navigator;
nav=n.appVersion;
nan=n.appName;
nua=n.userAgent;
old=(nav.substring(0,1)<4);
mac=(nav.indexOf('Mac')!=-1);
win=( ( (nav.indexOf('Win')!=-1) || (nav.indexOf('NT')!=-1) ) && !mac)?true:false;
lin=(nua.indexOf('Linux')!=-1);
// begin primary dom/ns4 test
// this is the most important test on the page
if ( !document.layers )
{
	dom = ( d.getElementById ) ? d.getElementById : false;
}
else { 
	dom = false; 
	ns4 = true;// only netscape 4 supports document layers
}
// end main dom/ns4 test

op=(nua.indexOf('Opera')!=-1);
saf=(nua.indexOf('Safari')!=-1);
konq=(!saf && (nua.indexOf('Konqueror')!=-1) ) ? true : false;
moz=( (!saf && !konq ) && ( nua.indexOf('Gecko')!=-1 ) ) ? true : false;
ie=((nua.indexOf('MSIE')!=-1)&&!op);
if (op)
{
	str_pos=nua.indexOf('Opera');
	nu=nua.substr((str_pos+6),4);
	brow = 'Opera';
}
else if (saf)
{
	str_pos=nua.indexOf('Safari');
	nu=nua.substr((str_pos+7),5);
	brow = 'Safari';
}
else if (konq)
{
	str_pos=nua.indexOf('Konqueror');
	nu=nua.substr((str_pos+10),3);
	brow = 'Konqueror';
}
// this part is complicated a bit, don't mess with it unless you understand regular expressions
// note, for most comparisons that are practical, compare the 3 digit rv nubmer, that is the output
// placed into 'nu'.
else if (moz)
{
	// regular expression pattern that will be used to extract main version/rv numbers
	pattern = /[(); \n]/;
	// moz type array, add to this if you need to
	moz_types = new Array( 'Firebird', 'Phoenix', 'Firefox', 'Iceweasel', 'Galeon', 'K-Meleon', 'Camino', 'Epiphany', 'Netscape6', 'Netscape', 'MultiZilla', 'Gecko Debian', 'rv' );
	rv_pos = nua.indexOf( 'rv' );// find 'rv' position in nua string
	rv_full = nua.substr( rv_pos + 3, 6 );// cut out maximum size it can be, eg: 1.8a2, 1.0.0 etc
	// search for occurance of any of characters in pattern, if found get position of that character
	rv_slice = ( rv_full.search( pattern ) != -1 ) ? rv_full.search( pattern ) : '';
	//check to make sure there was a result, if not do  nothing
	// otherwise slice out the part that you want if there is a slice position
	( rv_slice ) ? rv_full = rv_full.substr( 0, rv_slice ) : '';
	// this is the working id number, 3 digits, you'd use this for 
	// number comparison, like if nu >= 1.3 do something
	nu = rv_full.substr( 0, 3 );
	for (i=0; i < moz_types.length; i++)
	{
		if ( nua.indexOf( moz_types[i]) !=-1 )
		{
			moz_brow = moz_types[i];
			break;
		}
	}
	if ( moz_brow )// if it was found in the array
	{
		str_pos=nua.indexOf(moz_brow);// extract string position
		moz_brow_nu = nua.substr( (str_pos + moz_brow.length + 1 ) ,3);// slice out working number, 3 digit
		// if you got it, use it, else use nu
		moz_brow_nu = ( isNaN( moz_brow_nu ) ) ? moz_brow_nu = nu: moz_brow_nu;
		moz_brow_nu_sub = nua.substr( (str_pos + moz_brow.length + 1 ), 8);
		// this makes sure that it's only the id number
		sub_nu_slice = ( moz_brow_nu_sub.search( pattern ) != -1 ) ? moz_brow_nu_sub.search( pattern ) : '';
		//check to make sure there was a result, if not do  nothing
		( sub_nu_slice ) ? moz_brow_nu_sub = moz_brow_nu_sub.substr( 0, sub_nu_slice ) : '';
	}
	if ( moz_brow == 'Netscape6' )
	{
		moz_brow = 'Netscape';
	}
	else if ( moz_brow == 'rv' || moz_brow == '' )// default value if no other gecko name fit
	{
		moz_brow = 'Mozilla';
	} 
	if ( !moz_brow_nu )// use rv number if nothing else is available
	{
		moz_brow_nu = nu;
		moz_brow_nu_sub = nu;
	}
	if (n.productSub)
	{
		release_date = n.productSub;
	}
}
else if (ie)
{
	str_pos=nua.indexOf('MSIE');
	nu=nua.substr((str_pos+5),3);
	brow = 'IE';
}
// default to navigator app name
else 
{
	brow = nan;
}
op5=(op&&(nu.substring(0,1)==5));
op6=(op&&(nu.substring(0,1)==6));
op7=(op&&(nu.substring(0,1)==7));
op8=(op&&(nu.substring(0,1)==8));
op9=(op&&(nu.substring(0,1)==9));
ie4=(ie&&!dom);
ie5=(ie&&(nu.substring(0,1)==5));
ie6=(ie&&(nu.substring(0,1)==6));
ie7=(ie&&(nu.substring(0,1)==7));
// default to get number from navigator app version.
if(!nu) 
{
	nu = nav.substring(0,1);
}
/*ie5x tests only for functionavlity. dom or ie5x would be default settings. 
Opera will register true in this test if set to identify as IE 5*/
ie5x=(d.all&&dom);
ie5mac=(mac&&ie5);
ie5xwin=(win&&ie5x);

var browser = new Array();
if (brow != '') {
  browser['name'] = brow;
  browser['version'] =  nu.substring(0,1);
} else {
   if (moz_brow != '') {
     browser['name'] = moz_brow;
     browser['version'] =  moz_brow_nu;
   }
 }
return browser;
}//end function

function checkBrowserSupport(){
  //Array with supported browsers along with the minimum version required. 
  //Browser's name must match name retrieved from the detectBrowser() function
  var supported_browsers = [ "IE:5", "Firefox:1", "Netscape:8", "Safari:1", "Camino:1" ];
  browser = detectBrowser();
  for ( i = 0; i < supported_browsers.length; i++) {
     supported_browser = supported_browsers[i].substring(0,supported_browsers[i].indexOf(":"));
     supported_version = supported_browsers[i].substring(supported_browsers[i].indexOf(":")+1);
     if (browser['name'] == supported_browser) {
          if (browser['version'] >= supported_version) {
             return; //browser and version supported;
          } else {
              alert("\n\n Please, upgrade your browser to access the TIME system. \n\n The minimum version supported for " + browser['name'] + " is " + supported_version + ". \n\n Supported browsers and minimum versions are: IE 5.5, Firefox 1, Netscape 8, Safari 1.3, Camino 1 \n\n");
              return;
          }
     }
  }
  alert("Please use one of the following browsers to access the TIME system. \n\n Supported browsers and minimum versions are: IE 5.5, Firefox 1, Netscape 8, Safari 1.3, Camino 1");
}

	 function showActualTimeInquiry(documentId){
	   windowParameters = customizeWindowParameters("centered");
	   openCustomizedWindow("ActualTimeInquiry.do?method=search&hideSearchInput=true&searchCriteria.documentId="+documentId,windowParameters);
	 }
	 
	  function customizeWindowParameters(windowOptions){
			   var windowParameters = "toolbar=no,status=yes,location=no,menubar=no,scrollbars=yes,resizable=yes,directories=no";
			   if ( windowOptions.indexOf("centered") >= 0) {
			   var top = screen.availHeight / 3;
			   var left = screen.availWidth / 4;
			   var bottom = screen.availHeight - 1.5 * top;
			   var right = screen.availWidth - 2 * left;
		       windowParameters += "left="+left+",top="+top+",height="+bottom+",width="+right;
		    }
	        return windowParameters;
	   }
	 
	 function openCustomizedWindow(url,windowParameters) {
            var activeWindow = window.open(url, "_blank", windowParameters);
             activeWindow.focus();
         }


</script>

