package cn.tax.nswf.controller;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tax.core.common.Constant;
import cn.tax.core.common.RoleN;
import cn.tax.core.common.RolePrivilegeN;
import cn.tax.nswf.entity.Role;
import cn.tax.nswf.entity.RolePrivilege;
import cn.tax.nswf.entity.RolePrivilegeId;
import cn.tax.nswf.service.RoleService;

@Controller
public class RoleController {
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * @see 显示列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showListRole")
	public String showListRole(Model model){
		log.info("showListRole()");
		List<Role> roles = roleService.findObjects();
		List<RoleN> roleNsX = roleService.isChange(roles);
		model.addAttribute("roleNs",roleNsX);
		return "nsfw/role/listRole";
	}
	
	/**
	 * @see 转到修改用户页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doRoleEdit")
	public String doEdit(@RequestParam String roleId,Model model){
		Role role = roleService.findObjectById(roleId);
		List<RolePrivilegeN> rolePrivilegeNs = roleService.isChange(role);
		List<RolePrivilegeN> iPrivilegeNs = roleService.isChangeRemoveNs(role);
		model.addAttribute("role", role);
		model.addAttribute("rolePrivilegeNs", rolePrivilegeNs);
		model.addAttribute("iPrivilegeNs", iPrivilegeNs);
		return "nsfw/role/editUI";
	}
	
	/**
	 * @see 修改用户
	 * @return
	 */
	@RequestMapping(value="/updateRole")
	public String updateRole(@ModelAttribute Role role,@RequestParam String privilegeId){
		String[] privilegeIds = privilegeId.split(",");
		HashSet<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();
		for (String string : privilegeIds) {
			rolePrivileges.add(new RolePrivilege(new RolePrivilegeId(role,string)));
		}
		role.setRolePrivileges(rolePrivileges);
		try {
			System.out.println("updateRole() "+privilegeId+" role:");
			roleService.update_(role);
		} catch (Exception e) {
			System.out.println("没有更新成功!");
			try {
				roleService.update(role);
			} catch (Exception e2) {
				System.out.println("没有更新成功!");
			}
		}
		return "redirect:/showListRole";
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping(value="/doRoleDelete")
	public String doDelete(@RequestParam String roleId){
		roleService.delete(roleId);
		return "redirect:/showListRole";
	}
	
	/**
	 * @see 转到添加角色页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addRole_")
	public String addRole_(Model model){
		model.addAttribute("privilegeMap",Constant.PRIVILEGE_MAP);
		model.addAttribute("keyPRIVILEGE",Constant.keyPRIVILEGE);
		model.addAttribute("valuePRIVILEGE",Constant.valuePRIVILEGE);
		model.addAttribute("role", new Role());
		return "nsfw/role/addRole";
	}
	
	/**
	 * @see
	 * @return
	 */
	@RequestMapping(value="/addrole")
	public String addRole(@ModelAttribute Role role,@RequestParam String privilegeId){
		String[] privilegeIDs = privilegeId.split(",");
		HashSet<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();
		for (String string : privilegeIDs) {
			rolePrivileges.add(new RolePrivilege(new RolePrivilegeId(role,string)));
		}
		role.setRoleId(UUID.randomUUID().toString());
		role.setRolePrivileges(rolePrivileges);
		roleService.save(role);
		System.out.println("addRole "+role+" "+privilegeId);
		return "redirect:/showListRole";
	}
	
}
