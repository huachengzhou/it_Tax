package cn.tax.core.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	private static SessionContext sessionContext = SessionContext.getSessionContext();
	
	/**
	 * ����Session�Ĵ���
	 */
	public void sessionCreated(HttpSessionEvent event) {
		sessionContext.addSession(event.getSession());
	}

	/**
	 * ����Session���������
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		sessionContext.removeSession(event.getSession());
	}
	

}
