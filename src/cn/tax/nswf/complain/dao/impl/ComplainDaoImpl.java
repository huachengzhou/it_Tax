package cn.tax.nswf.complain.dao.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import cn.tax.core.dao.base.BaseDaoImpl;
import cn.tax.nswf.complain.dao.ComplainDao;

@ComponentScan(basePackages = { "cn.tax.nswf.complain.dao.impl" })
@Repository("complainDao")
public class ComplainDaoImpl extends BaseDaoImpl implements ComplainDao {

}
