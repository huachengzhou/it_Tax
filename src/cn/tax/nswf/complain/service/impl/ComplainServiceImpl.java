package cn.tax.nswf.complain.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import cn.tax.core.tax.util.PageResult;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.complain.dao.ComplainDao;
import cn.tax.nswf.complain.service.ComplainService;

@ComponentScan(basePackages = { "cn.tax.nswf.complain.service.impl" })
@Service("complainService")
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	private ComplainDao ComplainDao;

	@Override
	public <T> void save(T entity) {
		ComplainDao.save(entity);
	}

	@Override
	public <T> void update(T entity) {
		ComplainDao.update(entity);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Serializable id) {
		ComplainDao.delete(entityClass, id);
	}

	@Override
	public <T> T findObjectById(Class<T> entityClass, Serializable id) {
		return ComplainDao.findObjectById(entityClass, id);
	}

	@Override
	public <T> List<T> findObjects(Class<T> entityClass) {
		return ComplainDao.findObjects(entityClass);
	}

	@Override
	public <T> List<T> findObject__(QueryUtil queryUtil) {
		return ComplainDao.findObject__(queryUtil);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Serializable[] ids) {
		ComplainDao.delete(entityClass, ids);
	}

	@Override
	public PageResult getPageResult(QueryUtil queryHelper, int pageNo,
			int pageSize) {
		return ComplainDao.getPageResult(queryHelper, pageNo, pageSize);
	}

}
