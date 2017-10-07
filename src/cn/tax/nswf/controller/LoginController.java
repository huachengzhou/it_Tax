package cn.tax.nswf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import words.CalendarUtil;
import cn.tax.core.common.Constant;
import cn.tax.core.controller.BaseController;
import cn.tax.core.filter.SessionContext;
import cn.tax.core.filter.SessionListener;
import cn.tax.core.tax.util.ImgUtil;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.service.UserService;

@Controller
public class LoginController extends BaseController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private UserService userService;
	
	/**
	 * 登陆
	 * @param model
	 * @param password
	 * @param account
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model,@RequestParam String password,@RequestParam String account,HttpServletRequest request){
		System.out.println("password:"+password+" account:"+account);
		if (!StringUtils.isEmpty(account)&&!StringUtils.isEmpty(password)) {
			List<User> users = userService.findUserByAccountAndPass(account, password);
			if (users.size()>0&&users.get(0)!=null) {
				User user = users.get(0);
				System.out.println(user);
				if (!StringUtils.isEmpty(user.getAccount())&&!StringUtils.isEmpty(user.getPassword())) {
					String imgSrc = ImgUtil.headImgUtil(user);
					user.setImgSrc(imgSrc);
					SessionContext sessionContext = SessionContext.getSessionContext();
					if (sessionContext.getSessionMap().size()==0) {
						request.getSession().setAttribute(Constant.USER_SESSION_NAME,user);
						sessionContext.addSession(request.getSession());
						log.info("用户:"+user.getName()+"于:"+CalendarUtil.time()+"登陆系统");
						return "home/sysHome";
					}else {
						sessionContext.removeSession(request.getSession());
						sessionContext.addSession(request.getSession());
						request.getSession().setAttribute(Constant.USER_SESSION_NAME,user);
						log.info("用户:"+user.getName()+"于:"+CalendarUtil.time()+"登陆系统");
						return "home/sysHome";
					}
				}else {
					model.addAttribute(Constant.USER_ERROR,"用户名或者密码错误!");
					return "loginUI";
				}
			}else {
				model.addAttribute(Constant.USER_ERROR,"登陆失败!");
				return "loginUI";
			}
		}
		return "";
	}
	
	@RequestMapping(value="/login_")
	public String login_(Model model){
		model.addAttribute("user",new User());
		return "loginUI";
	}
	
	@RequestMapping(value="/logoutUser")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.USER_SESSION_NAME);
		return "loginUI";
	}
	
	
	
}
