/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.core.role.proxy.service;

import static org.kuali.rice.core.api.criteria.PredicateFactory.equal;
import static org.kuali.rice.core.api.criteria.PredicateFactory.greaterThan;
import static org.kuali.rice.core.api.criteria.PredicateFactory.isNull;
import static org.kuali.rice.core.api.criteria.PredicateFactory.lessThanOrEqual;
import static org.kuali.rice.core.api.criteria.PredicateFactory.or;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.rice.core.api.criteria.LookupCustomizer;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMember;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.type.KimType;
import org.kuali.rice.kim.api.type.KimTypeInfoService;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.kuali.rice.kim.framework.type.KimTypeService;
import org.kuali.rice.kim.impl.common.attribute.AttributeTransform;
import org.kuali.rice.kim.impl.role.RoleMemberBo;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

@SuppressWarnings("deprecation")
public class KpmeRoleProxyDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
	
	private static final Logger LOG = Logger.getLogger(KpmeRoleProxyDerivedRoleTypeServiceImpl.class);
	
	private RoleService roleService;
	private KimTypeInfoService kimTypeInfoService;
	private GroupService groupService;
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
	public KimTypeInfoService getKimTypeInfoService() {
		return kimTypeInfoService;
	}

	public void setKimTypeInfoService(KimTypeInfoService kimTypeInfoService) {
		this.kimTypeInfoService = kimTypeInfoService;
	}
	
	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	
	@Override
	public boolean performMatch(Map<String, String> inputAttributes, Map<String, String> storedAttributes) {
		// accept any qualifiers
		return true;
	}
	
	 @Override
	public List<String> getQualifiersForExactMatch() {
		// no pre-specified qualifiers for match
		return Collections.emptyList();
	}
	
	@Override
	public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
		// need membership results to be cached
		return true;
	}
	
	@Override
	public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String, String> qualification) {
		// get the role instance based on the name and name-space passed in via the qualification
		String proxiedNamespaceCode =  qualification.remove("KpmeProxiedRoleNamespaceCode");
		String proxiedRoleName =  qualification.remove("KpmeProxiedRoleRoleName");		
		Role proxiedRole = getRoleService().getRoleByNamespaceCodeAndName(proxiedNamespaceCode, proxiedRoleName);
		
		// get the as-of date and the active flag values
		DateTime asOfDate = LocalDate.now().toDateTimeAtStartOfDay();
		String asOfDateString = qualification.remove("KpmeProxiedRoleAsOfDate");
		if(asOfDateString != null) {
			asOfDate = DateTime.parse(asOfDateString);
		}
		
		boolean activeOnly = true;
		String activeOnlyString = qualification.remove("KpmeProxiedRoleIsActiveOnly");
		if(activeOnlyString != null) {
			activeOnly = Boolean.parseBoolean(activeOnlyString);
		}		
		
		return convertToRoleMemberships(getRoleMembers(proxiedRole, qualification, asOfDate, activeOnly));
	}
		
	
	
	private List<RoleMembership> convertToRoleMemberships(List<RoleMember> roleMembers) {
		List<RoleMembership> roleMemberships = new ArrayList<RoleMembership>();
		for(RoleMember roleMember: roleMembers) {
			RoleMembership roleMembership = RoleMembership.Builder.create(
                	roleMember.getRoleId(),
                	roleMember.getId(),
                	roleMember.getMemberId(),
                	roleMember.getType(),
                	roleMember.getAttributes()).build();
			roleMemberships.add(roleMembership);
		}
		return roleMemberships;
	}
		
		
	/**
	 * Recursive helper method to search for role members.
	 * 
	 * @param role The role
	 * @param qualification The map of role qualifiers
	 * @param asOfDate The effective date of the role
	 * @param  activeOnly or not to get only active role members
	 * 
	 * @return the list of role members in {@code role}.
	 */
	private List<RoleMember> getRoleMembers(Role role, Map<String, String> qualification, DateTime asOfDate, boolean activeOnly) {
		List<RoleMember> primaryRoleMembers = new ArrayList<RoleMember>();
		if (role != null) {
			RoleTypeService roleTypeService = getRoleTypeService(role);
			// use predicate based filtering only on non-derived role.
			if (roleTypeService == null || !roleTypeService.isDerivedRoleType()) {
				List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(equal(KimConstants.PrimaryKeyConstants.SUB_ROLE_ID, role.getId()));
                if (activeOnly) {
                    predicates.add(or(isNull("activeFromDateValue"), lessThanOrEqual("activeFromDateValue", asOfDate)));
                    predicates.add(or(isNull("activeToDateValue"), greaterThan("activeToDateValue", asOfDate)));
                }

                LookupCustomizer.Builder<RoleMemberBo> builder = LookupCustomizer.Builder.create();
                builder.setPredicateTransform(AttributeTransform.getInstance());
                LookupCustomizer<RoleMemberBo> lookupCustomizer = builder.build();
                // get the keys (name) of the qualifiers needed for membership in this role
                List<String> attributesForExactMatch = roleTypeService.getQualifiersForExactMatch();
                if(CollectionUtils.isNotEmpty(attributesForExactMatch)) { 
	                for (Map.Entry<String, String> qualificationEntry : qualification.entrySet()) {
	                	// do not add a qualification predicate for an attribute unless it is required for matching
	                	if(attributesForExactMatch.contains(qualificationEntry.getKey())) {
		                    Predicate predicate = equal("attributes[" + qualificationEntry.getKey() + "]", qualificationEntry.getValue());
		                    predicates.add(lookupCustomizer.getPredicateTransform().apply(predicate));
	                	}
	                }
                }
                primaryRoleMembers = getRoleService().findRoleMembers(QueryByCriteria.Builder.fromPredicates(predicates.toArray(new Predicate[] {}))).getResults();
			}
			else {
				// for derived roles just add the as-of date and active only flag to a copy of the qualification
				Map<String, String> derivedRoleQualification = new HashMap<String, String>(qualification);
				derivedRoleQualification.put("asOfDate", asOfDate.toString());
				derivedRoleQualification.put("activeOnly", String.valueOf(activeOnly));
				List<RoleMembership> derivedRoleMembers = roleTypeService.getRoleMembersFromDerivedRole(role.getNamespaceCode(), role.getName(), derivedRoleQualification);
				// convert the role memberships into role members
				for (RoleMembership derivedRoleMember : derivedRoleMembers) {
					RoleMember roleMember = RoleMember.Builder.create(derivedRoleMember.getRoleId(), derivedRoleMember.getId(), derivedRoleMember.getMemberId(), 
							derivedRoleMember.getType(), null, null, derivedRoleMember.getQualifier(), role.getName(), role.getNamespaceCode()).build();
				                        
					primaryRoleMembers.add(roleMember);
				}
			}
		}
		
		// finally, recursively flatten any nested roles in the primary members into their 
		// constituent group and principal role members
		List<RoleMember> flattenedRoleMembers = new ArrayList<RoleMember>();
		for (RoleMember primaryRoleMember : primaryRoleMembers) {
			if (MemberType.PRINCIPAL.equals(primaryRoleMember.getType())) {
				flattenedRoleMembers.add(primaryRoleMember);
			} 
			else if (MemberType.GROUP.equals(primaryRoleMember.getType())) {
				flattenedRoleMembers.add(primaryRoleMember);
			} 
			else if (MemberType.ROLE.equals(primaryRoleMember.getType())) {
				Role nestedRole = getRoleService().getRole(primaryRoleMember.getMemberId());
				// recursive call to get role members
				Map<String, String> copiedQualification = new HashMap<String, String>(qualification);
				copiedQualification.putAll(primaryRoleMember.getAttributes());
				flattenedRoleMembers.addAll(getRoleMembers(nestedRole, copiedQualification, asOfDate, activeOnly));
			}
		}
		
		return flattenedRoleMembers;
	}
	
		
	
	/**
	 * Gets the derived role service for {@code role}.
	 * 
	 * @param role the role
	 * 
	 * @return the derived role service name for {@code role}.
	 */
    protected RoleTypeService getRoleTypeService(Role role) {
    	RoleTypeService roleTypeService = null;
    	
        if (role != null) {
        	String serviceName = getDerivedRoleServiceName(role.getKimTypeId());
        	
        	if (serviceName != null) {
                try {
                    KimTypeService service = (KimTypeService) GlobalResourceLoader.getService(QName.valueOf(serviceName));
                    if (service != null && service instanceof RoleTypeService) {
                        return (RoleTypeService) service;
                    }
                } catch (Exception ex) {
                    LOG.error("Unable to find role type service with name: " + serviceName, ex);
                }
            }
        }
        
        return roleTypeService;
    }

	/**
	 * Gets the derived role service name for {@code kimTypeId}.
	 * 
	 * @param kimTypeId the KIM type id
	 * 
	 * @return the derived role service name for {@code kimTypeId}.
	 */
	protected String getDerivedRoleServiceName(String kimTypeId) {
		KimType kimType = getKimTypeInfoService().getKimType(kimTypeId);
		
		return kimType != null ? kimType.getServiceName() : null;
	}
	
		
}
