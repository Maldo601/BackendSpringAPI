package com.maldo.backend.users.domain.response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class UserResponse implements Serializable
{
	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = 4658238405701821799L;
	private Long id;
	private String username;
	private String pwd;
	private String email;
	private String name;
	private String lastName;
	private String secondLastName;
	private Date createDate;
	private Date modifiedDate;
	private Boolean active;
	private Long roleId;

	public Long getId()
	{
		return id;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPwd(){return pwd;}
	public void setPwd(String pwd){this.pwd = pwd;}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getEmail()
	{
		return email;
	}
	public String getName()
	{
		return name;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getSecondLastName()
	{
		return secondLastName;
	}
	public Boolean getActive()
	{
		return active;
	}
	public Long getRoleId() {return roleId;}
	public void setId(Long id)
	{
		this.id = id;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setSecondLastName(String secondLastName)
	{
		this.secondLastName = secondLastName;
	}
	public void setActive(Boolean active)
	{
		this.active = active;
	}
	public void setRoleId(Long roleId) {this.roleId = roleId;}
}
