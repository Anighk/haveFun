package com.shfb.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shfb.common.util.Constant;

@Entity
@Table(name = Constant.TABLE_MEMBERS)
public class Members implements Serializable {
	private static final long serialVersionUID = 8132269427379848317L;
	@Id
	private String user_id;
	private String nick_name;
	private String isvip;
	private String sales_manager;
	private Date reg_date;
	private Date overdue_date;
	private String province;
	private String city;
	private String company;
	private String job_title;
	private String contacts;
	private String tel;
	private String qq;
	private String equipment;
	private String remark;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getIsvip() {
		return isvip;
	}
	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}
	public String getSales_manager() {
		return sales_manager;
	}
	public void setSales_manager(String sales_manager) {
		this.sales_manager = sales_manager;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getOverdue_date() {
		return overdue_date;
	}
	public void setOverdue_date(Date overdue_date) {
		this.overdue_date = overdue_date;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
