package cn.tax.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tax.core.dao.BaseDao;
import cn.tax.core.service.BaseService;
import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;

public class BaseServiceImpl implements BaseService{
	@Autowired
    private BaseDao baseDao;
    
	public <T> void save(T entity) {
		baseDao.save(entity);
	}

	public <T> void update(T entity) {
		baseDao.update(entity);
	}

	public <T> void delete(Class<T> entityClass, Serializable id) {
		baseDao.delete(entityClass, id);
	}

	public <T> T findObjectById(Class<T> entityClass, Serializable id) {
		return baseDao.findObjectById(entityClass, id);
	}

	public <T> List<T> findObjects(Class<T> entityClass) {
		return baseDao.findObjects(entityClass);
	}

	@Override
	public <T> List<T> findObject__(QueryUtil queryUtil) {
		return baseDao.findObject__(queryUtil);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

	@Override
	public PageResult getPageResult(QueryUtil queryHelper, int pageNo,int pageSize) {
		return baseDao.getPageResult(queryHelper, pageNo, pageSize);
	}
}
