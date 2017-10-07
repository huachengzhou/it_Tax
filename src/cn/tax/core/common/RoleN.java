package cn.tax.core.common;

import java.util.HashSet;
import java.util.Set;

import cn.tax.nswf.entity.Role;

public class RoleN {
	private Role role;
	private Set<RolePrivilegeN> rolePrivilegeNs = new HashSet<RolePrivilegeN>();
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Set<RolePrivilegeN> getRolePrivilegeN() {
		return rolePrivilegeNs;
	}
	public void setRolePrivilegeN(Set<RolePrivilegeN> rolePrivilegeN) {
		this.rolePrivilegeNs = rolePrivilegeN;
	}
}
