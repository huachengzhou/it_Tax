package cn.tax.nswf.dao;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.dao.BaseDao;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.entity.UserRole;

public interface UserDao extends BaseDao{
	public void update_(User user);
	public User login(String accountName);
	/**
	 * �����ʺź��û�id��ѯ�û�
	 * @param id �û�ID
	 * @param account �û��ʺ�
	 * @return �û��б�
	 */
	public List<User> findUserByAccountAndId(String id, String account);

	//�����û���ɫ
	public void saveUserRole(UserRole userRole);

	//�����û�idɾ�����û��������û���ɫ
	public void deleteUserRoleByUserId(Serializable id);
	//�����û�id��ȡ���û���Ӧ�������û���ɫ
	public List<UserRole> getUserRolesByUserId(String id);
	//�����û����ʺź������ѯ�û��б�
	public List<User> findUsersByAcccountAndPass(String account, String password);
	public <T> List<T> findObject__(QueryUtil queryUtil);
}
