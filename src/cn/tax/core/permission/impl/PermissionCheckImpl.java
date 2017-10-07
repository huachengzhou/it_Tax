package cn.tax.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.tax.core.permission.PermissionCheck;
import cn.tax.nswf.entity.Role;
import cn.tax.nswf.entity.RolePrivilege;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.entity.UserRole;
import cn.tax.nswf.service.UserService;

public class PermissionCheckImpl implements PermissionCheck{
	@Resource
	private UserService userService;

	@Override
	public boolean isAccessible(User user, String code) {
		//1����ȡ�û������н�ɫ
		List<UserRole> list = user.getUserRoles();
		if(list == null){
			list = userService.getUserRolesByUserId(user.getId());
		}
		
		//2������ÿ����ɫ���ڵ�����Ȩ�޽��жԱ�
		if(list != null && list.size()>0){
			for(UserRole ur: list){
				Role role = ur.getId().getRole();
				for(RolePrivilege rp: role.getRolePrivileges()){
					//�Ա��Ƿ���code��Ӧ��Ȩ��
					if(code.equals(rp.getId().getCode())){
						//˵����Ȩ�ޣ�����true
						return true;
					}
				}
			}
		}
		return false;
	}

}
