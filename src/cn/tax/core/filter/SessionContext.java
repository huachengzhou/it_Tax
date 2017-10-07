package cn.tax.core.filter;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
/**
 * 
 * @author Blake.Zhou
 * ����ģʽ
 */
public class SessionContext {
	private static SessionContext instance = null;  
    private HashMap<String,HttpSession> sessionMap; 
    
    private SessionContext(){
    	/*��ʼ��*/
    	sessionMap = new HashMap<String,HttpSession>();  
    }
    public static SessionContext getSessionContext(){
    	if (instance!=null) {
			return instance;
		}else {
			instance = new SessionContext();
		}
    	return instance;
    }
    /**
     * ���Ⲣ����� synchronized
     * @param session
     */
    public synchronized void addSession(HttpSession session){
    	if (session!= null) {
    		sessionMap.put(session.getId(),session);
		}
    }
    
    /**
     * ɾ�� HttpSession
     * @param session
     */
    public synchronized void removeSession(HttpSession session){
    	if (session!=null) {
			sessionMap.remove(session.getId());
			if (session.getAttribute("user")!=null) {
				session.removeAttribute("user");
			}
		}
    }
    
    /**
     * ��ȡsession
     * @param id
     * @return
     */
    public synchronized HttpSession getSession(String id){
    	if (id==null) {
			return null;
		}
    	return sessionMap.get(id);
    }
	public HashMap<String, HttpSession> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(HashMap<String, HttpSession> sessionMap) {
		this.sessionMap = sessionMap;
	}
    
}
