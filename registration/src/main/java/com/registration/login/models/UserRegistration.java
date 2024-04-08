package com.registration.login.models;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="first_name")
	private String firstName;
	
	@Column(name ="last_name")
	private String lastName;
	
	
	@Column(name ="email",unique=true)
	private String email;
	
	@Column(name ="password")
	private String password;
	
	@Column(name="confirm_password")
	private String confirm_password;
	
	//to maintain many to many mapping between role and user
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_Id", referencedColumnName = "id"))
	
	//create collection here, because it has 
	private Collection<Login> roles;

	



	public UserRegistration(String firstName, String lastName, String email, String password,
			String confirm_password, Collection<Login> roles) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm_password = confirm_password;
		this.roles = roles;
	}


	

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Login> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Login> roles) {
		this.roles = roles;
	}


	public String getConfirm_password() {
		return confirm_password;
	}


	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
}
