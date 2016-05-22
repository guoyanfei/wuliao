package com.wuliao.dandan.model;

public class Admin {

	private Long id;

	private String username;

	private String password;

	private Long auth;

	private String realname;

	private String sex;

	private Long age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAuth() {
		return auth;
	}

	public void setAuth(Long auth) {
		this.auth = auth;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", auth=" + auth + ", realname=" + realname + ", sex=" + sex + ", age=" + age + "]";
	}

	public Admin() {
		super();
	}

	public Admin(Long id, String username, String password, Long auth, String realname, String sex, Long age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.auth = auth;
		this.realname = realname;
		this.sex = sex;
		this.age = age;
	}

}
