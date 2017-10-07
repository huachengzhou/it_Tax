package tax.text;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import tax.util.ApplicationUtils;

public class SessionFactoryTest {
	@Test
	public void name() {
		SessionFactory factory = ApplicationUtils.getContext().getBean(SessionFactory.class);
		Session session  = factory.openSession();
		System.out.println(session);
	}
}
