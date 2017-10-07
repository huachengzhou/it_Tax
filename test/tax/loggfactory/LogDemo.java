package tax.loggfactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogDemo {
	private Log log = LogFactory.getLog(getClass());
	
	@Test
	public void isit(){
		log.info("��Ϣ����");
		log.debug("���Լ���");
		log.warn("���漶��");
		log.error("���󼶱�");
		log.fatal("��������");
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
