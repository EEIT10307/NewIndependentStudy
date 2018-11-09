package projectbean;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"} ) , @UniqueConstraint(columnNames = {"phone"} )})

public class MemberDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer memberSerialNum ; 
	private String  email ; 
	private String  password;
	private String  name;
	private String  phone;
	private java.util.Date birthday ; 
	private String gender ;
	private String address ;
	private java.util.Date  signinDate; 
	private java.util.Date  lastLoginDate ; 
	private Blob profilePhoto ;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getMemberSerialNum() {
		return memberSerialNum;
	}
	public void setMemberSerialNum(Integer memberSerialNum) {
		this.memberSerialNum = memberSerialNum;
	}
	@Column(nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable = false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public java.util.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(nullable = false)
	public java.util.Date getSigninDate() {
		return signinDate;
	}
	public void setSigninDate(java.util.Date signinDate) {
		this.signinDate = signinDate;
	}
	@Column(nullable = false)
	public java.util.Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(java.util.Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
//	@Column(nullable = false)
	public Blob getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(Blob profilePhoto) {
		this.profilePhoto = profilePhoto;
	} 
	
	
	

}
