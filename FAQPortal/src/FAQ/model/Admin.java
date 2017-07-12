package FAQ.model;

public class Admin {
	String UID,name,email,department,phoneNo,password;
	int adminFlag;
	public Admin() {
		this.adminFlag = 1;
	}
	public Admin(String uID, String name, String email, String department, String phoneNo, String password) {
		
		UID = uID;
		this.name = name;
		this.email = email;
		this.department = department;
		this.phoneNo = phoneNo;
		this.password = password;
		this.adminFlag = 1;
	}
	
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(int adminFlag) {
		this.adminFlag = adminFlag;
	}
}
