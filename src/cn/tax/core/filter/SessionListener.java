package cn.tax.core.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	private static SessionContext sessionContext = SessionContext.getSessionContext();
	
	/**
	 * 监听Session的创举
	 */
	public void sessionCreated(HttpSessionEvent event) {
		sessionContext.addSession(event.getSession());
	}

	/**
	 * 监听Session的销毁情况
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		sessionContext.removeSession(event.getSession());
	}
	

}
