<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="startClock" required="false"%>
<%@ attribute name="columnCount" %>

</div>
	  <div align="center">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="t3" summary="">
          <tbody>
            <tr>
              <td align="left" ><img src="jsp/images/pixel_clear.gif" alt="" width="12" height="12" class="tl3"></td>
              <td align="right"><img src="jsp/images/pixel_clear.gif" alt="" width="12" height="12" class="tr3"></td>
            </tr>
          </tbody>
        </table>
      </div>
<div id="workarea">
	<div class="tab-container" align="center">
		<hr:tabH2Div tabTitle="${StrutsActionForm.labels[nestingPath]}" nestingPath="${nestingPath}" />
		<hr:entity nestingPath="${nestingPath}" columns="${columns}" columnCount="${columnCount}" />
		<c:if test="${!empty startClock}">
			<b><span id="clock">&nbsp;</span></b>
		</c:if>
	</div>


	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
		<tr>
			<td align="left" class="footer">
				<img src="jsp/images/pixel_clear.gif" alt="" width="12" height="14" class="bl3">
			</td>
			<td align="right" class="footer-right">
				<img src="jsp/images/pixel_clear.gif" alt="" width="12" height="14" class="br3">
			</td>
		</tr>
	</table>

	<hr:hidden nestingPath="${nestingPath}" hiddenFields="${hiddenFields}" />