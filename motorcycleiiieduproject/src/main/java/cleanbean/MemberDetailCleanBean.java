package cleanbean;

public class MemberDetailCleanBean {


	private String  email ; 
	private String  password;
	private String  name;
	private String  phone;
	private String birthday ; 
	private String gender ;
	private String address ;
	
	
	
	
	
	public MemberDetailCleanBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDetailCleanBean(String email, String password, String name, String phone, String birthday,
			String gender, String address) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		
		return "MemberDetal = [ Email:"+email+",password:"+ password+",name:"+name+",phone:"+phone+",birthday:"+birthday+",gender:"+gender+",address:"+address+"]";
	}

	
	

	
	
}
