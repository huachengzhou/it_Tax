package cn.tax.nswf.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import cn.tax.core.service.BaseService;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.entity.UserRole;

public interface UserService {
	   //新增
		public void save(User user);
		//更新
		public void update(User user);
		public void update_(User user);
		//根据id删除O
		public void delete(Serializable id);
		//根据id查找
		public User findObjectById(Serializable id);
		//查找列表
		public List<User> findObjects();
		/*下载Excel*/
		public ResponseEntity<byte[]> createResponse(String fileName);
		/*上传Excel文件*/
		public void doImportExcelN(MultipartFile file);
		/*登陆*/
		public User login(String accountName);
		/**
		 * 根据帐号和用户id查询用户
		 * @param id 用户ID
		 * @param account 用户帐号
		 * @return 用户列表
		 */
		public List<User> findUserByAccountAndId(String id, String account);
		//保存用户及其对应的角色
		public void saveUserAndRole(User user, String... roleIds);
		//保存用户及其对应的角色
		public void updateUserAndRole(User user, String... roleIds);
		//根据用户id获取该用户对应的所有用户角色
		public List<UserRole> getUserRolesByUserId(String id);
		//根据用户的帐号和密码查询用户列表
		public List<User> findUserByAccountAndPass(String account, String password);
		public <T> List<T> findObject__(QueryUtil queryUtil);
}
