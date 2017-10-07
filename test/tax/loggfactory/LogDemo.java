package tax.loggfactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogDemo {
	private Log log = LogFactory.getLog(getClass());
	
	@Test
	public void isit(){
		log.info("信息级别");
		log.debug("调试级别");
		log.warn("警告级别");
		log.error("错误级别");
		log.fatal("致命级别");
	}
	
	@Test
	public void isN(){
		try {
			int o = 1/0;
			System.out.println(o);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
