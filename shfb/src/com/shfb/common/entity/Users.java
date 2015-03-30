package com.shfb.common.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "admin_users")
@SequenceGenerator(name="sequsers",sequenceName="sequsersid",allocationSize=1)
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sequsers")
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String passwd;
	@Column(name = "truename")
	private String truename;
	@Column(name = "dept")
	private String dept;
	@Column(name = "attr")
	private String attr="0";
	@Column(name = "status")
	private String status="1";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
//	public String getChathead() {
//		return chathead;
//	}
//	public void setChathead(String chathead) {
//		this.chathead = chathead;
//	}

}
