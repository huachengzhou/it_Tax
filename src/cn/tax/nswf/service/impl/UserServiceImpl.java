package cn.tax.nswf.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.tax.core.tax.util.ExcelUtil;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.dao.UserDao;
import cn.tax.nswf.entity.Role;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.entity.UserRole;
import cn.tax.nswf.entity.UserRoleId;
import cn.tax.nswf.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(Serializable id) {
		userDao.delete(User.class,id);
	}

	public User findObjectById(Serializable id) {
		return userDao.findObjectById(User.class,id);
	}

	public List<User> findObjects() {
		return userDao.findObjects(User.class);
	}

	public void update_(User user) {
		userDao.update_(user);
	}

	public ResponseEntity<byte[]> createResponse(String fileName) {
		List<User> users = userDao.findObjects(User.class);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> responseEntity = null;
		try {
			byte[] bodys = ExcelUtil.excelUtil(users);
			String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
			//解决下载显示的文件名问题
			headers.setContentDispositionFormData("attachment",downloadFileName);
			//二进制流数据(最常见的文件下载)
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			responseEntity = new ResponseEntity<byte[]>(bodys, headers,HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("异常!"+e.getMessage());
			e.printStackTrace();
		}
		return responseEntity;
	}

	public void doImportExcelN(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			//1、读取工作簿
			try {
				Workbook workbook = null;
				try {
					workbook = is03Excel ?new XSSFWorkbook(file.getInputStream()):new HSSFWorkbook(file.getInputStream());
				} catch (Exception e) {
					workbook = is03Excel ?new HSSFWorkbook(file.getInputStream()):new XSSFWorkbook(file.getInputStream());
				}
				//2、读取工作表
				Sheet sheet = workbook.getSheetAt(0);
				//3、读取行
				if(sheet.getPhysicalNumberOfRows() > 2){
					User user = null;
					for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
						//4、读取单元格
						Row row = sheet.getRow(k);
						user = new User();
						//用户名
						Cell cell0 = row.getCell(0);
						user.setName(cell0.getStringCellValue());
						//帐号
						Cell cell1 = row.getCell(1);
						user.setAccount(cell1.getStringCellValue());
						//所属部门
						Cell cell2 = row.getCell(2);
						user.setDept(cell2.getStringCellValue());
						//性别
						Cell cell3 = row.getCell(3);
						user.setGender(cell3.getStringCellValue().equals("男"));
						//手机号
						String mobile = "";
						Cell cell4 = row.getCell(4);
						try {
							mobile = cell4.getStringCellValue();
						} catch (Exception e) {
							double dMobile = cell4.getNumericCellValue();
							mobile = BigDecimal.valueOf(dMobile).toString();
						}
						user.setMobile(mobile);
						
						//电子邮箱
						Cell cell5 = row.getCell(5);
						user.setEmail(cell5.getStringCellValue());
						//生日
						Cell cell6 = row.getCell(6);
						if(cell6.getDateCellValue() != null){
							user.setBirthday(cell6.getDateCellValue());
						}
						//默认用户密码为 123456
						user.setPassword("123456");
						//默认用户状态为 有效
						user.setState(User.USER_STATE_VALID);
						//5、保存用户
						User user2 = login(user.getAccount());
						if (user2==null) {
							userDao.save(user);
						}
						workbook.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public User login(String accountName) {
		return userDao.login(accountName);
	}

	public List<User> findUserByAccountAndId(String id, String account) {
		return userDao.findUserByAccountAndId(id, account);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		//1、保存用户
		save(user);
		//2、保存用户对应的角色
		if(roleIds != null){
			for(String roleId: roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
			}
		}
		
	}

	public void updateUserAndRole(User user, String... roleIds) {
		//1、根据用户删除该用户的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2、更新用户
		update(user);
		//3、保存用户对应的角色
		if(roleIds != null){
			for(String roleId: roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
			}
		}
	}

	public List<UserRole> getUserRolesByUserId(String id) {
		return userDao.getUserRolesByUserId(id);
	}

	public List<User> findUserByAccountAndPass(String account, String password) {
		return userDao.findUsersByAcccountAndPass(account, password);
	}

	@Override
	public <T> List<T> findObject__(QueryUtil queryUtil) {
		return userDao.findObject__(queryUtil);
	}

}
