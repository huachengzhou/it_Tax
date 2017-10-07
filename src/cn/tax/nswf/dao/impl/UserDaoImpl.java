package cn.tax.nswf.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.tax.core.dao.base.BaseDaoImpl;
import cn.tax.nswf.dao.UserDao;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.entity.UserRole;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	@Resource
	private SessionFactory sessionFactory;
   
	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	public void update_(User user) {
		User user2 = (User) getSession().get(User.class,user.getId());
		String imgSrc = user2.getHeadImg();
		user.setHeadImg(imgSrc);
		getSession().evict(user2);
		getSession().update(user);
	}
	
	public User login(String accountName){
		String hql = "from User"+" u where u.account = :name";
		Query query = getSession().createQuery(hql);
		query.setString("name",accountName);
		User user = (User) query.uniqueResult();
		return user;
	}
	public List<User> findUserByAccountAndId(String id, String account) {
		String hql = "FROM User WHERE account = ?";
		if (StringUtils.isEmpty(id)) {
			hql += " AND id!=?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0,account);
		if (StringUtils.isEmpty(id)) {
			query.setParameter(1,id);
		}
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		return users;
	}
	public void saveUserRole(UserRole userRole) {
		getSession().save(userRole);
	}
	public void deleteUserRoleByUserId(Serializable id) {
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRolesByUserId(String id) {
		Query query = getSession().createQuery("FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<User> findUsersByAcccountAndPass(String account, String password) {
		Query query = getSession().createQuery("FROM User WHERE account=? AND password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}
	

}
