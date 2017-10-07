package cn.tax.nswf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tax.nswf.entity.Role;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.entity.UserRole;
import cn.tax.nswf.entity.UserRoleId;
import cn.tax.nswf.service.RoleService;
import cn.tax.nswf.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService ;
	
	/**
	 * @see 显示列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showListUser")
	public String showListUser(Model model){
		log.info("showListUser()");
		List<User> users = userService.findObjects();
		model.addAttribute("users",users);
		return "nsfw/user/listUser";
	}
	
	/**
	 * @see 转到修改用户页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doEdit")
	public String doEdit(@RequestParam String id,Model model){
		User user = userService.findObjectById(id);
		List<String> depts = new ArrayList<String>();
		depts.add("A部门");
		depts.add("B部门");
		depts.add("C部门");
		String imgSrc = user.getHeadImg();
		if (imgSrc!=null) {
			imgSrc = imgSrc.substring(imgSrc.lastIndexOf("\\")+1,imgSrc.length());
			imgSrc = "upload/"+imgSrc;
		}
		//user中存在的角色
		List<UserRole> list = userService.getUserRolesByUserId(user.getId());
//		String[] userRoleIds;
//		if(list != null && list.size() > 0){
//			userRoleIds = new String[list.size()];
//			for(int i = 0; i < list.size(); i++){
//				userRoleIds[i] = list.get(i).getId().getRole().getRoleId();
//			}
//		}
		List<Role> roles = roleService.findObjects();
		List<UserRole> list2 = new ArrayList<UserRole>();//所有角色
		for (Role role : roles) {
			list2.add(new UserRole(new UserRoleId(role,user.getId())));
		}
		list2.removeAll(list);
		
		
		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		model.addAttribute("imgSrc",imgSrc);
		model.addAttribute("user", user);
		model.addAttribute("depts",depts);
		return "nsfw/user/editUI";
	}
	
	/**
	 * @see 修改用户
	 * @return
	 */
	@RequestMapping(value="/updateUser")
	public String updateUser(@ModelAttribute User user,@RequestParam("file") MultipartFile file,HttpServletRequest request
			,@RequestParam String roleId){
		System.out.println("updateUser()"+roleId);
		try {
			if (file != null && file.getBytes().length > 1) {
				String imgURL = "";
				imgURL = cn.tax.core.tax.util.FileUtil.saveFile(file, request);
				user.setHeadImg(imgURL);
				if (user != null) {
					userService.update(user);//这没做
				}
			}
			String[] strings = roleId.split(",");
			if (strings.length>0) {
				userService.updateUserAndRole(user, strings);
			}else {
				userService.update_(user);
			}
		} catch (Exception e) {
			log.error("异常!");
			e.printStackTrace();
		}
		return "redirect:/showListUser";
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/doRemove")
	public String doRemove(@RequestParam String id){
		userService.delete(id);
		return "redirect:/showListUser";
	}
	
	/**
	 * @see 转到添加用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUser_")
	public String addUser_(Model model){
		List<String> depts = new ArrayList<String>();
		depts.add("A部门");
		depts.add("B部门");
		depts.add("C部门");
		model.addAttribute("depts",depts);
		model.addAttribute("roles",roleService.findObjects());
		model.addAttribute("userN", new User());
		return "nsfw/user/addUser";
	}
	
	/**
	 * @see 添加用户
	 * @return
	 */
	@RequestMapping(value="/addUser")
	public String addUser(@ModelAttribute User user,@RequestParam("file") MultipartFile file,HttpServletRequest request
			,@RequestParam String roleId){
		try {
			String imgURL = "";
			imgURL = cn.tax.core.tax.util.FileUtil.saveFile(file, request);
			user.setId(UUID.randomUUID().toString());
			user.setHeadImg(imgURL);
			System.out.println("..................roleId"+roleId);
			try {
				String [] roleIds = roleId.split(",");
				userService.saveUserAndRole(user, roleIds);
			} catch (Exception e) {
				System.out.println("失败!");
				userService.save(user);
			}
		} catch (Exception e) {
			log.error("exception!"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/showListUser";
	}
	
	/**
	 * Excel导出
	 */
	@RequestMapping(value="/addExportExcel")
	public ResponseEntity<byte[]> addExportExcel(HttpServletResponse response){
		String fileName = "用户列表.xlsx";
		ResponseEntity<byte[]> rEntity = userService.createResponse(fileName);
		return rEntity;
	}
	
	@RequestMapping(value="/doImportExcel",method=RequestMethod.POST)
	public String doImportExcel(@RequestParam("fileUser") MultipartFile file){
		if (file!=null) {
			userService.doImportExcelN(file);
			System.out.println("成功!");
		}
		return "redirect:/showListUser";
	}
	
	/*账号校验*/
	@RequestMapping(value="/user_verifyAccount",method=RequestMethod.POST)
	public @ResponseBody String user_verifyAccount(@RequestParam String account,@RequestParam(defaultValue="") String id){
		List<User> users = userService.findUserByAccountAndId(id, account);
		System.out.println("id:"+id+" account:"+account);
		if (users.size()>0) {
			return "false";
		}
		return "true";
	}
}
