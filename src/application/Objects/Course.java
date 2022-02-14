package application.Objects;

public class Course {
	
	private String courseName;
	private String instructor;
	private int coursePrice;

	public Course() {
	}
	
	public Course(String courseName, String instructor, int coursePrice) {
		this.courseName = courseName;
		this.instructor = instructor;
		this.coursePrice = coursePrice;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public int getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
}
