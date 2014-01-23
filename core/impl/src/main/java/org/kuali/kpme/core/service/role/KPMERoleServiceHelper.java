package org.kuali.kpme.core.service.role;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.kuali.rice.kim.api.role.RoleMember;
import org.springframework.cache.annotation.Cacheable;

public interface KPMERoleServiceHelper {
	
	@Cacheable(value=RoleMember.Cache.NAME, key="'{getRoleMembersCached}' + 'namespace=' + #p0 + '|' + 'roleName=' + #p1 + '|' + 'qualification=' + T(org.kuali.rice.core.api.cache.CacheKeyUtils).mapKey(#p2)  + '|' + 'asOfDate=' + #p3  + '|' + 'isActiveOnly=' + #p4")
	public List<RoleMember> getRoleMembersCached(String namespaceCode, String roleName, Map<String, String> qualification, DateTime asOfDate, boolean isActiveOnly);

}