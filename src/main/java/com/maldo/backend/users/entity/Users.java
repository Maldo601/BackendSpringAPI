package com.maldo.backend.users.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user")
public class Users
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, precision = 38)
	private Long id;

	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "PASSWORD", unique = true, nullable = false, length = 500)
	private String pwd;

	@Column(name = "EMAIL", nullable = false, length = 60)
	private String email;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "LASTNAME", nullable = false, length = 50)
	private String lastName;

	@Column(name = "SECONDLASTNAME", nullable = false, length = 50)
	private String secondLastName;

	@CreatedDate
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@LastModifiedDate
	@Column(name = "MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean active;

	@ManyToOne()
	@JoinColumn(name="ROLE_ID", updatable = false, insertable = false)
	private Roles roles;

	@Column(name = "ROLE_ID", nullable = false)
	private Long roleId;

}