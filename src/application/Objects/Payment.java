package application.Objects;

public class Payment {
	
	private String phoneNumber;
	private String studentName;
	private int courseID;
	private double payment;
	private String paymentDate;
	
	public Payment(String phoneNumber, String studentName, int courseID, double payment, String paymentDate) {
		super();
		this.phoneNumber = phoneNumber;
		this.studentName = studentName;
		this.courseID = courseID;
		this.payment = payment;
		this.paymentDate = paymentDate;
	}

	public Payment() {
		super();
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

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
