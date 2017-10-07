package cn.tax.core.service;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;

public interface BaseService {
	/**
	 * ����
	 * @param entity
	 */
	public <T> void save(T entity);
	/**
	 * ����
	 * @param entity
	 */
	public <T> void update(T entity);
	/**
	 * ����idɾ��
	 * @param entityClass
	 * @param id
	 */
	public <T> void delete(Class<T> entityClass,Serializable id);
	/**
	 * ����id����
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T findObjectById(Class<T> entityClass, Serializable id);
	/**
	 * �����б�
	 */
	public <T> List<T> findObjects(Class<T> entityClass);
	
	/**������ѯʵ���б�--��ѯ����queryHelper*/
	public <T> List<T> findObject__(QueryUtil queryUtil);
	/**����ɾ��*/
	public <T> void delete(Class<T> entityClass,Serializable[] ids);
	public PageResult getPageResult(QueryUtil queryHelper, int pageNo,int pageSize);
}
