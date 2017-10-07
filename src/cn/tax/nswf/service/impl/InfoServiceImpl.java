package cn.tax.nswf.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.dao.InfoDao;
import cn.tax.nswf.service.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	private InfoDao infoDao;

	public <T> void save(T entity) {
		infoDao.save(entity);
		
	}

	public <T> void update(T entity) {
		infoDao.update(entity);
		
	}

	public <T> void delete(Class<T> entityClass, Serializable id) {
		infoDao.delete(entityClass, id);
		
	}

	public <T> T findObjectById(Class<T> entityClass, Serializable id) {
		return infoDao.findObjectById(entityClass, id);
	}

	public <T> List<T> findObjects(Class<T> entityClass) {
		return infoDao.findObjects(entityClass);
	}

	public <T> List<T> findObject__(QueryUtil queryUtil) {
		return infoDao.findObject__(queryUtil);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Serializable[] ids) {
		infoDao.delete(entityClass, ids);
	}

	@Override
	public PageResult getPageResult(QueryUtil queryHelper, int pageNo,int pageSize) {
		return infoDao.getPageResult(queryHelper, pageNo, pageSize);
	}
	
	
}
