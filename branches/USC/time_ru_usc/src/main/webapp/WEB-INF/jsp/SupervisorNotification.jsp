<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ include file="/jsp/tlds.jsp"%>
<hr:page>
  <hr:entity nestingPath="supervisorNotification" columns="from,supervisors,subject,message" hiddenFields="networkId"/>
</hr:page>