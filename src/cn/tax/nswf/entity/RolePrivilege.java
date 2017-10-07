package cn.tax.nswf.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RolePrivilege implements Serializable {
	private RolePrivilegeId id;
	
	public RolePrivilege() {
	}

	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}

	
	
}
