package cn.tax.nswf.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable{
	private Integer pid; // ��ʾ������ ��hibernate�и��ݱ�ʾ������ʶ�𻺴��еĶ���
	private String name;
	private String sex;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Person() {
	}
	public Person(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}
}
