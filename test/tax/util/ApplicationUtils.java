package tax.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationUtils {
	public static ApplicationContext getContext(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:beans.xml");
		return ctx;
	}
}
