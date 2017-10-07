package cn.tax.nswf.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserRole implements Serializable{
	private UserRoleId id;

	public UserRole() {
	}

	public UserRole(UserRoleId id) {
		this.id = id;
	}

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}
}
