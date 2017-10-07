package cn.tax.nswf.dao.impl;


import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.tax.core.dao.base.BaseDaoImpl;
import cn.tax.nswf.dao.RoleDao;
@Repository("roleDao")
public class RoleDaoimpl extends BaseDaoImpl implements RoleDao  {
	@Resource
	private SessionFactory sessionFactory;
   
	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
