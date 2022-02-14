package application.Objects;

public class Student {

	private String name;
	private String phoneNumber;
	private String college;
	
	public Student() {
	}
	
	public Student(String name, String phoneNumber, String college) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.college = college;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}
