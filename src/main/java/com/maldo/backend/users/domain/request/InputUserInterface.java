package com.maldo.backend.users.domain.request;

import java.util.Date;

public interface InputUserInterface
{
	Long getId();
	String getUsername();
	String getPwd();
	String getEmail();
	String getName();
	String getLastName();
	String getSecondLastName();
	Boolean getActive();
	Date getModifiedDate();
	Date getCreateDate();
	Long getRoleId();
	void setId(Long id);
	void setUsername(String username);
	void setPwd(String pwd);
	void setEmail(String email);
	void setName(String name);
	void setLastName(String lastName);
	void setSecondLastName(String secondLastName);
	void setCreateDate(Date createDate);
	void setModifiedDate(Date modifiedDate);
	void setActive(Boolean active);
	void setRoleId(Long roleId);
}
