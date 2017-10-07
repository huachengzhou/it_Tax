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
	/** 定义不需要拦截的请求 */
	private static final String[] IGNORE_URI = { "/login_", "/login",};

	/**
	 * 该方法需要preHandle方法的返回值为true时才会执行。 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("通过拦截!");
	}

	/**
	 * 这个方法在preHandle方法返回值为true的时候才会执行。 执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行。
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1,
			Object arg2, ModelAndView mv) throws Exception {
		System.out.println("view name:"+mv.getViewName());
		System.out.println("参数名:"+mv.getModel());
		System.out.println("请求地址"+request.getRequestURI());
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		/** 默认用户没有登录 */
		boolean flag = false;
		/* 定义默认的Servlet path */
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		/** 判断请求是否需要拦截 */
		for (String str : IGNORE_URI) {
			if (uri.contains(str)) {
				flag = true;
				System.out.println("正在登录用户!");
				System.err.println("状态:"+flag+"----------->");
				break;
			}
		}
		/** 拦截请求 */
		if (!flag) {
			/** 1.获取session中的用户 */
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(Constant.USER_SESSION_NAME);
			/** 2:判断用户是否已经登陆 */
			if (user != null) {
				//再次拦截处理
				if (uri.contains("nsfw")) {
					System.out.println("开始拦截特别处理!");
					//获取spring容器
					WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
					PermissionCheck pc = ctx.getBean(PermissionCheck.class);
					if(pc.isAccessible(user, "nsfw")){
						//说明有权限，放行
						flag = true;
					} else {
						//没有权限，跳转到没有权限提示页面
						flag = false;
						request.getRequestDispatcher("/WEB-INF/views/noPermissionUI.jsp").forward(request, response);
					}
				}else {
					/*非访问纳税服务子系统，则直接放行*/
					flag = true;
				}
			} else {// 没有登录
				request.setAttribute("error", "no login");
				request.getRequestDispatcher("/WEB-INF/views/loginUI.jsp").forward(request, response);
			}
		}
		return flag;
	}

}
