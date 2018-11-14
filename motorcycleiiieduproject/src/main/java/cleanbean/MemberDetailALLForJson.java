package cleanbean;

public class MemberDetailALLForJson {
	private String  email ; 
	private String  phone;
	private String birthday ; 
	private String gender ;
	private String address ;
	private String signinDate;//註冊日期
	private Integer yeartotal;
	
	
	public MemberDetailALLForJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDetailALLForJson(String email, String phone, String birthday,
			String gender, String address, String signinDate,Integer yeartotal) {
		super();
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.signinDate = signinDate;
		this.yeartotal = yeartotal;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
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
	public String getSigninDate() {
		return signinDate;
	}
	public void setSigninDate(String signinDate) {
		this.signinDate = signinDate;
	}

	public Integer getYeartotal() {
		return yeartotal;
	}

	public void setYeartotal(Integer yeartotal) {
		this.yeartotal = yeartotal;
	}
	
}
