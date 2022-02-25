package application.Objects;

public class Payment {
	
	private String phone;
	private String name;
	private String courseName;
	private int payment;
	private String paymentDate;
	
	public Payment() {
		super();
	}

	public Payment(String phone, String name, String courseName, int payment, String payemntDate) {
		super();
		this.phone = phone;
		this.name = name;
		this.courseName = courseName;
		this.payment = payment;
		this.paymentDate = payemntDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
