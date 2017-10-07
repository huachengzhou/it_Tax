package cn.tax.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;

public interface BaseDao {
	    //����
		public <T> void save(T entity);
		//����
		public <T> void update(T entity);
		public <T> void delete(Class<T> entityClass,Serializable[] ids);
		//����idɾ��
		public <T> void delete(Class<T> entityClass,Serializable id);
		//����id����
		public <T> T findObjectById(Class<T> entityClass, Serializable id);
		//�����б�
		public <T> List<T> findObjects(Class<T> entityClass);
		
		@Deprecated
		/**��ѯ ����*/
		public <T> List<T> findObjecTs_(String hql,List<Object> params);
		
		/**������ѯʵ���б�--��ѯ����queryHelper*/
		public <T> List<T> findObject__(QueryUtil queryUtil);
		public PageResult getPageResult(QueryUtil queryHelper, int pageNo, int pageSize);
}
