package tax.text;


import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

import tax.util.ApplicationUtils;

public class DemoAppcontextTesyt {
	static ApplicationContext ctx = ApplicationUtils.getContext();
	public static void main(String[] args) {
		isit();
		isDataSource();
	}
	public static void isit(){
		SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
		System.out.println(sessionFactory.openSession());
	}
	public static void isDataSource(){
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource);
	}
}
