package com.shfb.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "renew_info")
@SequenceGenerator(name="seqrenew",sequenceName="seqrenew",allocationSize=1)
public class Renew implements Serializable {
	private static final long serialVersionUID = 2241067498014268799L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seqrenew")
	private Integer id;
	private String user_id;
	private String nick_name;
	private Date reg_date;
	private Date renew_date;
	private String renew_year;
	private Date renew_to;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getRenew_date() {
		return renew_date;
	}
	public void setRenew_date(Date renew_date) {
		this.renew_date = renew_date;
	}
	public String getRenew_year() {
		return renew_year;
	}
	public void setRenew_year(String renew_year) {
		this.renew_year = renew_year;
	}
	public Date getRenew_to() {
		return renew_to;
	}
	public void setRenew_to(Date renew_to) {
		this.renew_to = renew_to;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
