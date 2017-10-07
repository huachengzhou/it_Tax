package cn.tax.nswf.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tax.core.common.Constant;
import cn.tax.core.common.RoleN;
import cn.tax.core.common.RolePrivilegeN;
import cn.tax.core.tax.util.DeleteUtil;
import cn.tax.nswf.dao.RoleDao;
import cn.tax.nswf.entity.Role;
import cn.tax.nswf.entity.RolePrivilege;
import cn.tax.nswf.entity.RolePrivilegeId;
import cn.tax.nswf.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	public void update(Role role) {
		// 1��ɾ���ý�ɫ���ڵ�����Ȩ��
		roleDao.delete(Role.class, role.getRoleId());
		// 2�����½�ɫ����Ȩ��
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(Role.class, id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		return roleDao.findObjectById(Role.class, id);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects(Role.class);
	}

	public List<RoleN> isChange(List<Role> roles) {
		HashMap<String, String> privilegeMap = Constant.PRIVILEGE_MAP;
		List<RoleN> roleNs = new ArrayList<RoleN>();
		for (Role role : roles) {
			RoleN roleN = new RoleN();
			Set<RolePrivilegeN> rolePrivilegeNs = new HashSet<RolePrivilegeN>();
			Set<RolePrivilege> rolePrivileges = role.getRolePrivileges();
			for (RolePrivilege rolePrivilege : rolePrivileges) {
				RolePrivilegeN rolePrivilegeN = new RolePrivilegeN();
				rolePrivilegeN.setRolePrivilege(rolePrivilege);
				RolePrivilege rolePrivilege2 = rolePrivilegeN
						.getRolePrivilege();
				RolePrivilegeId keyN = rolePrivilege2.getId();
				String key = keyN.getCode();
				System.out.println("key:" + key);
				String value = privilegeMap.get(key);
				rolePrivilegeN.setCodeN(value);
				System.out.println("value:" + value);
				System.out.println(rolePrivilegeN);
				rolePrivilegeNs.add(rolePrivilegeN);
				System.out.println(rolePrivilegeNs.size());
			}
			roleN.setRolePrivilegeN(rolePrivilegeNs);
			roleN.setRole(role);
			roleNs.add(roleN);
		}
		return roleNs;
	}

	public List<RolePrivilegeN> isChange(Role role) {
		Set<RolePrivilege> set = role.getRolePrivileges();
		List<RolePrivilegeN> rolePrivilegeNs = new ArrayList<RolePrivilegeN>();
		HashMap<String, String> privilegeMap = Constant.PRIVILEGE_MAP;
		for (RolePrivilege rolePrivilege : set) {
			RolePrivilegeN rolePrivilegeN = new RolePrivilegeN();
			String value =  privilegeMap.get(rolePrivilege.getId().getCode());
			rolePrivilegeN.setCodeW(rolePrivilege.getId().getCode());
			rolePrivilegeN.setCodeN(value);
			rolePrivilegeNs.add(rolePrivilegeN);
		}
		return rolePrivilegeNs;
	}
	
	public List<RolePrivilegeN> isChangeRemoveNs(Role role){
		List<RolePrivilegeN> aList = new ArrayList<RolePrivilegeN>();
		HashMap<String, String> privilegeMap = Constant.PRIVILEGE_MAP;
		Set<Entry<String,String>> sEntries = privilegeMap.entrySet();
		for (Entry<String, String> entry : sEntries) {
			String keyString = entry.getKey();
			String valueString = entry.getValue();
			RolePrivilegeN rolePrivilegeN = new RolePrivilegeN();
			rolePrivilegeN.setCodeN(valueString);
			rolePrivilegeN.setCodeW(keyString);
			aList.add(rolePrivilegeN);
		}
		System.out.println("size:"+aList.size());
		int num = aList.size();
		List<RolePrivilegeN> rolePrivilegeNs = isChange(role);
		System.out.println("��ɾ��size:"+rolePrivilegeNs.size());
		
		aList.removeAll(rolePrivilegeNs);
		System.out.println("ɾ����size:--"+aList.size());
		
		if (aList.size()==num) {//����ɾ��
			for (int j = 0; j < 6; j++) {//��������6��
				//..............
				for (int i = 0; i < aList.size(); i++) {
					try {//��Խ���,����û�й�ϵ
						RolePrivilegeN rolePrivilegeW = rolePrivilegeNs.get(i);
						if (aList.get(i).equals(rolePrivilegeW)) {
							aList.remove(i);//ɾ��ָ��λ��
							if (aList.get(i).equals(rolePrivilegeW)) {//�����ûɾ��
								//��ɾһ��
								aList.remove(aList.get(i));
							}
						}
					} catch (Exception e) {
						
					}
				}
				//.........................
			}
		}
		
		try {
			RolePrivilegeN[] arraysNs = new RolePrivilegeN[aList.size()];
			int i = 0;
			for (RolePrivilegeN rolePrivilegeN : aList) {
				arraysNs[i] = rolePrivilegeN;
				i++;
			}
			for (int j = 0; j < rolePrivilegeNs.size(); j++) {
				arraysNs = DeleteUtil.isDeleteNumber(arraysNs,rolePrivilegeNs.get(j));
			}
			List<RolePrivilegeN> aNs = new ArrayList<RolePrivilegeN>();
			for (RolePrivilegeN rolePrivilegeN : arraysNs) {
				aNs.add(rolePrivilegeN);
			}
			return aNs;
		} catch (Exception e) {
			
		}
		System.out.println("ɾ����size:^^"+aList.size());
		return aList;
	}

	public void update_(Role role) {
		roleDao.update(role);
	}

}
