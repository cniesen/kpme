<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ include file="/jsp/tlds.jsp"%>

<hr:channelPage>
	<hr:channelEntity nestingPath="userPreferences" columns="preferences" />
	<hr:channelEntity nestingPath="userPreferences" columns="locationPreference" />
</hr:channelPage>
