package cn.tax.nswf.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String id;
	private String dept;
	private String account;
	private String name;
	private String password;
	
	private String headImg;
	private Boolean gender;
	private String state;
	private String mobile;
	private String email;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	private String memo;
	
	//图片处理传入的属性
	private String imgSrc = "";
	private List<UserRole> userRoles;
	
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	//用户状态
	/**
	 * 有效
	 */
	public static String USER_STATE_VALID = "1";
	/**
	 * 无效
	 */
	public static String USER_STATE_INVALID = "0";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", dept=" + dept + ", account=" + account
				+ ", name=" + name + ", password=" + password + ", headImg="
				+ headImg + ", gender=" + gender + ", state=" + state
				+ ", mobile=" + mobile + ", email=" + email + ", birthday="
				+ birthday + ", memo=" + memo + "]";
	}
	
}
