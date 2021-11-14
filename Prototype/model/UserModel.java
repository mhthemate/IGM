package Model;

import java.sql.Date;

public class UserModel {
	private int id;
	private String username;
	private String password;
	private String email;
	private Date dob;
	private String country;
	
	public UserModel() {
		
	}
	
	public UserModel(int id, String username, String password, 
			String email, Date dob, String country) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.country = country;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public String getCountry() {
		return country;
	}
}
