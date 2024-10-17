package com.ucb.apimoodle.entities;

public class Aluno {
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String telefone;
	private String shortname;
	private String datestart;
	private String dateend;
	private String group;
	private Integer role;
	
	public Aluno() {
	}

	public Aluno(String username, String firstname, String lastname, String password, String email, String telefone,
			String shortname, String datestart, String dateend, String group, Integer role) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.telefone = telefone;
		this.shortname = shortname;
		this.datestart = datestart;
		this.dateend = dateend;
		this.group = group;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getDatestart() {
		return datestart;
	}

	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}

	public String getDateend() {
		return dateend;
	}

	public void setDateend(String dateend) {
		this.dateend = dateend;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
