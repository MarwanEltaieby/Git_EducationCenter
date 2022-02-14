package application.Objects;

public class Course {
	
	private String courseName;
	private int courseID;
	private double coursePrice;
	public static int numberOfCourses;
	
	public Course() {
		numberOfCourses++;
	}
	
	public Course(String courseName, int courseID, double coursePrice) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.coursePrice = coursePrice;
		numberOfCourses++;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(double coursePrice) {
		this.coursePrice = coursePrice;
	}
}
