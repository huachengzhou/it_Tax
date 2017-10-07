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
	   //����
		public void save(User user);
		//����
		public void update(User user);
		public void update_(User user);
		//����idɾ��O
		public void delete(Serializable id);
		//����id����
		public User findObjectById(Serializable id);
		//�����б�
		public List<User> findObjects();
		/*����Excel*/
		public ResponseEntity<byte[]> createResponse(String fileName);
		/*�ϴ�Excel�ļ�*/
		public void doImportExcelN(MultipartFile file);
		/*��½*/
		public User login(String accountName);
		/**
		 * �����ʺź��û�id��ѯ�û�
		 * @param id �û�ID
		 * @param account �û��ʺ�
		 * @return �û��б�
		 */
		public List<User> findUserByAccountAndId(String id, String account);
		//�����û������Ӧ�Ľ�ɫ
		public void saveUserAndRole(User user, String... roleIds);
		//�����û������Ӧ�Ľ�ɫ
		public void updateUserAndRole(User user, String... roleIds);
		//�����û�id��ȡ���û���Ӧ�������û���ɫ
		public List<UserRole> getUserRolesByUserId(String id);
		//�����û����ʺź������ѯ�û��б�
		public List<User> findUserByAccountAndPass(String account, String password);
		public <T> List<T> findObject__(QueryUtil queryUtil);
}
