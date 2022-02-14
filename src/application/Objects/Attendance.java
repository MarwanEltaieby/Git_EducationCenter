package application.Objects;

import javafx.scene.control.CheckBox;

public class Attendance {

	private String phoneNumber;
	private String studentName;
	private int courseID;
	private int sessionNumber;
	private String sessionDate;
	private CheckBox isAttended;
	
	public Attendance() {
		super();
	}
	
	public Attendance(String phoneNumber, String studentName, int courseID, int sessionNumber, String sessionDate, CheckBox isAttended) {
		super();
		this.phoneNumber = phoneNumber;
		this.studentName = studentName;
		this.courseID = courseID;
		this.sessionNumber = sessionNumber;
		this.sessionDate = sessionDate;
		this.isAttended = isAttended;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public CheckBox getIsAttended() {
		return isAttended;
	}

	public void setIsAttended(CheckBox isAttended) {
		this.isAttended = isAttended;
	}
	
	
}
