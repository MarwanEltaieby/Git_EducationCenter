package application.Objects;

public class Session {
	
	private String instructor;
	private int courseID;
	private int sessionNumber;
	private String sessionDate;
	
	public Session() {
	}

	public Session(String instructor, int courseID, int sessionNumber, String sessionDate) {
		this.instructor = instructor;
		this.courseID = courseID;
		this.sessionNumber = sessionNumber;
		this.sessionDate = sessionDate;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
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

}
