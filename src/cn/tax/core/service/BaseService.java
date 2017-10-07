package cn.tax.core.service;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;

public interface BaseService {
	/**
	 * 新增
	 * @param entity
	 */
	public <T> void save(T entity);
	/**
	 * 更新
	 * @param entity
	 */
	public <T> void update(T entity);
	/**
	 * 根据id删除
	 * @param entityClass
	 * @param id
	 */
	public <T> void delete(Class<T> entityClass,Serializable id);
	/**
	 * 根据id查找
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T findObjectById(Class<T> entityClass, Serializable id);
	/**
	 * 查找列表
	 */
	public <T> List<T> findObjects(Class<T> entityClass);
	
	/**条件查询实体列表--查询助手queryHelper*/
	public <T> List<T> findObject__(QueryUtil queryUtil);
	/**批量删除*/
	public <T> void delete(Class<T> entityClass,Serializable[] ids);
	public PageResult getPageResult(QueryUtil queryHelper, int pageNo,int pageSize);
}
