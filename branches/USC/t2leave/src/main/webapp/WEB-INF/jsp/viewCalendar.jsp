
    <div class="msg-excol" style="height: 48px;">

    	<%--  need error_global -- come back.. <kul:errors keyMatch="cal" errorTitle=" " styleId="error_global"/>
    	<kul:messages/> --%>


	<div style="margin-bottom: 0px; clear: both;">
		<a href="${Form.mappingPath}.do?methodToCall=view&targetMonth=${Form.month.previousMonth}&year=${Form.month.previousYear}" style="text-decoration: none;">
		    <img src="en/images/arrow_small_left.gif"/>
		</a>
	
			<span style="font-size: 14pt; font-weight: bold; padding: 0 50px 0px 50px; vertical-align: bottom;">
			${Form.month.name} ${Form.month.year}</span>
		<a href="${Form.mappingPath}.do?methodToCall=view&targetMonth=${Form.month.nextMonth}&year=${Form.month.nextYear}" style="text-decoration: none;">
		    <img class="nav-image" src="en/images/arrow_small_right.gif" />
		</a>
		
	</div>

	<br/>
	<!-- TODO come back and add security and others -->
     <div style="clear:both"></div>
    <table class="pto-calendar">
        <tr class="pto-calendar-tr">
            <td class="pto-calendar-th">Sunday</td>
            <td class="pto-calendar-th">Monday</td>
            <td class="pto-calendar-th">Tuesday</td>
            <td class="pto-calendar-th">Wednesday</td>
            <td class="pto-calendar-th">Thursday</td>
            <td class="pto-calendar-th">Friday</td>
            <td class="pto-calendar-th">Saturday</td>
        </tr>
        <c:if test="${CalendarForm.month.daysBeforeFirst} != '0'">
        	<tr class="pto-calendar-tr">
        </c:if>
        <tr class="pto-calendar-tr">
        <c:forEach var="i" begin="1" end="${CalendarForm.month.daysBeforeFirst}">
        	<td class="pto-calendar-td">
        		<div class="blank-day"></div>
        	</td>
        </c:forEach>
        <c:forEach var="day" items="${CalendarForm.month.days}" varStatus="dayStatus">
			<c:if test="${day.dayInWeek == '1'}">
            	<tr class="pto-calendar-tr">
            </c:if>

            <c:choose>
                <c:when test="${day.dayInWeek == '1' || day.dayInWeek == '7'}">
                    <td class="pto-calendar-td-weekend">
                </c:when>
                <c:when test="${Form.today.date == day.dayInMonth && CalendarForm.targetMonth == CalendarForm.month.currentMonth}">
                    <td class="pto-calendar-td-today">
                </c:when>
                <c:otherwise>
                    <td class="pto-calendar-td">
                </c:otherwise>
            </c:choose>

            <div class="day-header-generic" name="day-header">${day.dayInMonth}</div>
            <div class="day-field-generic">
            <table border=0 cellspacing=0 cellpadding=0>
			
				<c:forEach var="ledgerItem" items="${day.ledgerItems}" varStatus="ledgerStatus">
		            <tr>
		            	<td>

	                                    <b>${ledgerItem.leaveCodeDescription}</b>${desc}<br/>  />

						</td>
					</tr>
				</c:forEach>
			
			
			
			</table>
			
    	</div>
    	
    	</td>
         	<c:if test="${day.dayInWeek == '7'}">
            	</tr>
            </c:if>
         
    	</c:forEach>
    	
    	<c:forEach var="i" begin="1" end="${CalendarForm.month.daysAfterLast}">
        	<td class="pto-calendar-td">
        		<div class="blank-day"></div>
        	</td>
        </c:forEach>
        <c:if test="${CalendarForm.month.daysAfterLast} != '0'">
       		</tr>
       	</c:if>
    	
    </table>
    
        <div class="quiet" style="text-align: left;">* Indicates a correction.</div>
    <br/>
    
    
    