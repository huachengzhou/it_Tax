package cn.tax.nswf.service;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.common.RoleN;
import cn.tax.core.common.RolePrivilegeN;
import cn.tax.nswf.entity.Role;

public interface RoleService {
    //新增
	public void save(Role role);
	//更新
	public void update(Role role);
	public void update_(Role role);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public Role findObjectById(Serializable id);
	//查找列表
	public List<Role> findObjects();
	/**
	 * 特别处理,用了自己设置的数据模型Bean
	 * @return
	 */
	public List<RoleN> isChange(List<Role> roles);
	/**
	 * 特别处理,用了自己设置的数据模型Bean
	 * @param role
	 * @return
	 */
	public List<RolePrivilegeN> isChange(Role role) ;
	/**
	 * 修改角色页面所用数据
	 * @param role
	 * @return
	 */
	public List<RolePrivilegeN> isChangeRemoveNs(Role role);
}
