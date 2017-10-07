package cn.tax.home.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/sysHome")
	public String sysHome(Model model,HttpServletRequest request){
		return "home/sysHome";
	}
	
	@RequestMapping(value="/sysComplainAddUI")
	public String complainAddUI(Model model){
		List<String> depts = new ArrayList<String>();
		depts.add("A部门");
		depts.add("B部门");
		depts.add("C部门");
		model.addAttribute("depts", depts);
		return "home/complainAddUI";
	}
	
	@RequestMapping(value="/getUserJson")
	public @ResponseBody List<User> getUserJson(@RequestParam String dept){
		QueryUtil queryHelper = new QueryUtil(User.class,"u");
		queryHelper.addCondition("u.dept like ?", "%" + dept);
		//2、根据部门查询用户列表
		List<User> userList = userService.findObject__(queryHelper);
		return userList;
	}
}
