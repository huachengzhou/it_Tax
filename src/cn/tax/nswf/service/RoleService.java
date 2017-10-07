package cn.tax.nswf.service;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.common.RoleN;
import cn.tax.core.common.RolePrivilegeN;
import cn.tax.nswf.entity.Role;

public interface RoleService {
    //����
	public void save(Role role);
	//����
	public void update(Role role);
	public void update_(Role role);
	//����idɾ��
	public void delete(Serializable id);
	//����id����
	public Role findObjectById(Serializable id);
	//�����б�
	public List<Role> findObjects();
	/**
	 * �ر���,�����Լ����õ�����ģ��Bean
	 * @return
	 */
	public List<RoleN> isChange(List<Role> roles);
	/**
	 * �ر���,�����Լ����õ�����ģ��Bean
	 * @param role
	 * @return
	 */
	public List<RolePrivilegeN> isChange(Role role) ;
	/**
	 * �޸Ľ�ɫҳ����������
	 * @param role
	 * @return
	 */
	public List<RolePrivilegeN> isChangeRemoveNs(Role role);
}
