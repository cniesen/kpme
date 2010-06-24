package edu.iu.uis.hr.tk.directory.entity;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class RoleAudit extends AbstractRoleAudit implements PersistentMaintainedEntity, AuditedPersitentDatabaseMaintainedEntity {
	private static final Logger LOG = Logger.getLogger(RoleAudit.class);

	public RoleAudit() {
		super();
	}

}

