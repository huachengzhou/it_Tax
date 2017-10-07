package cn.tax.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;

public interface BaseDao {
	    //新增
		public <T> void save(T entity);
		//更新
		public <T> void update(T entity);
		public <T> void delete(Class<T> entityClass,Serializable[] ids);
		//根据id删除
		public <T> void delete(Class<T> entityClass,Serializable id);
		//根据id查找
		public <T> T findObjectById(Class<T> entityClass, Serializable id);
		//查找列表
		public <T> List<T> findObjects(Class<T> entityClass);
		
		@Deprecated
		/**查询 搜索*/
		public <T> List<T> findObjecTs_(String hql,List<Object> params);
		
		/**条件查询实体列表--查询助手queryHelper*/
		public <T> List<T> findObject__(QueryUtil queryUtil);
		public PageResult getPageResult(QueryUtil queryHelper, int pageNo, int pageSize);
}
