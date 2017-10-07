package cn.tax.core.common;

import cn.tax.nswf.entity.RolePrivilege;

public class RolePrivilegeN {
	private String codeN;
	private String codeW;
	private RolePrivilege rolePrivilege;
	public String getCodeN() {
		return codeN;
	}
	public void setCodeN(String codeN) {
		this.codeN = codeN;
	}
	public RolePrivilege getRolePrivilege() {
		return rolePrivilege;
	}
	public void setRolePrivilege(RolePrivilege rolePrivilege) {
		this.rolePrivilege = rolePrivilege;
	}
	
	public String getCodeW() {
		return codeW;
	}
	public void setCodeW(String codeW) {
		this.codeW = codeW;
	}
	@Override
	public String toString() {
		return "RolePrivilegeN [codeN=" + codeN + ", codeW=" + codeW
				+ ", rolePrivilege=" + rolePrivilege + "]";
	}
}
