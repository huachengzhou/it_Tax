package tax.text;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import tax.util.ApplicationUtils;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.entity.Info;
import cn.tax.nswf.service.InfoService;

public class Demo2 {
	SessionFactory factory = ApplicationUtils.getContext().getBean(SessionFactory.class);
	Session session  = factory.openSession();
	
	@SuppressWarnings("unchecked")
	@Test
	public void isit(){
		String hql = "FROM "+Info.class.getSimpleName() +" as i where i.title like ? ORDER BY i.createTime";
		Query query = session.createQuery(hql);
		query.setParameter(0,"%标题%");
		List<Info> infos = query.list();
		for (Info info : infos) {
			System.out.println(info);
		}
	}
	
	@Test
	public void isN(){
		QueryUtil queryUtil = new QueryUtil(Info.class, "i");
		queryUtil.addCondition("i.title like ?","%信息%");
		queryUtil.addOrderByProperty("i.createTime", queryUtil.ORDER_BY_ASC);
		List<Object> objects = queryUtil.getParameters();
		String hql = queryUtil.getQueryListHql();
		Query query = session.createQuery(hql);
		for (int i = 0; i < objects.size(); i++) {
			query.setParameter(i,objects.get(i));
		}
		List<Info> infos = query.list();
		for (Info info : infos) {
			System.out.println(info);
		}
		System.out.println(hql);
	}
	
	@Test
	public void isM(){
		InfoService  infoService = ApplicationUtils.getContext().getBean(InfoService.class);
		QueryUtil queryUtil = new QueryUtil(Info.class, "i");
		queryUtil.addCondition("i.title like ?","%信息%");
		queryUtil.addOrderByProperty("i.createTime", queryUtil.ORDER_BY_ASC);
		List<Info> infos = infoService.findObject__(queryUtil);
		for (Info info : infos) {
			System.out.println(info);
		}
	}
}
