package cn.tax.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tax.core.common.Constant;
import cn.tax.core.permission.PermissionCheck;
import cn.tax.nswf.entity.User;

public class AuthorizedInterceptor implements HandlerInterceptor {
	/** ���岻��Ҫ���ص����� */
	private static final String[] IGNORE_URI = { "/login_", "/login",};

	/**
	 * �÷�����ҪpreHandle�����ķ���ֵΪtrueʱ�Ż�ִ�С� �÷������������������֮��ִ�У���Ҫ����������������Դ��
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("ͨ������!");
	}

	/**
	 * ���������preHandle��������ֵΪtrue��ʱ��Ż�ִ�С� ִ��ʱ�����ڴ��������д���֮
	 * ��Ҳ������Controller�ķ�������֮��ִ�С�
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1,
			Object arg2, ModelAndView mv) throws Exception {
		System.out.println("view name:"+mv.getViewName());
		System.out.println("������:"+mv.getModel());
		System.out.println("�����ַ"+request.getRequestURI());
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		/** Ĭ���û�û�е�¼ */
		boolean flag = false;
		/* ����Ĭ�ϵ�Servlet path */
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		/** �ж������Ƿ���Ҫ���� */
		for (String str : IGNORE_URI) {
			if (uri.contains(str)) {
				flag = true;
				System.out.println("���ڵ�¼�û�!");
				System.err.println("״̬:"+flag+"----------->");
				break;
			}
		}
		/** �������� */
		if (!flag) {
			/** 1.��ȡsession�е��û� */
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(Constant.USER_SESSION_NAME);
			/** 2:�ж��û��Ƿ��Ѿ���½ */
			if (user != null) {
				//�ٴ����ش���
				if (uri.contains("nsfw")) {
					System.out.println("��ʼ�����ر���!");
					//��ȡspring����
					WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
					PermissionCheck pc = ctx.getBean(PermissionCheck.class);
					if(pc.isAccessible(user, "nsfw")){
						//˵����Ȩ�ޣ�����
						flag = true;
					} else {
						//û��Ȩ�ޣ���ת��û��Ȩ����ʾҳ��
						flag = false;
						request.getRequestDispatcher("/WEB-INF/views/noPermissionUI.jsp").forward(request, response);
					}
				}else {
					/*�Ƿ�����˰������ϵͳ����ֱ�ӷ���*/
					flag = true;
				}
			} else {// û�е�¼
				request.setAttribute("error", "no login");
				request.getRequestDispatcher("/WEB-INF/views/loginUI.jsp").forward(request, response);
			}
		}
		return flag;
	}

}
