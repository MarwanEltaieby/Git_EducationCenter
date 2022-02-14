package application.Objects;

import java.time.LocalDate;

public class RegisteredStudent extends Student {
	
	private int course_id;
	private LocalDate registrationDate;
	
	public RegisteredStudent() {
		super();
		registrationDate = LocalDate.now();
	}
	public RegisteredStudent(String name, String phoneNumber, String college, int course_id) {
		super(name, phoneNumber, college);
		this.course_id = course_id;
		registrationDate = LocalDate.now();
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
}
