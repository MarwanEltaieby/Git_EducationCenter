package application.Objects;


import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegisteredStudent extends Student {
	
	private String courseName;
	private int previousPayment;
	private TextField payment;
	private CheckBox attendance1;
	private CheckBox attendance2;
	private CheckBox attendance3;
	private CheckBox attendance4;
	private CheckBox attendance5;
	private CheckBox attendance6;
	private CheckBox attendance7;
	private CheckBox attendance8;
	private CheckBox attendance9;
	private CheckBox attendance10;
	
	
	public RegisteredStudent() {
		super();
	}
	
	public RegisteredStudent(String name, String phoneNumber, String college, int phone, String courseName,
			int previousPayment, TextField payment, CheckBox attendance1,
			CheckBox attendance2, CheckBox attendance3, CheckBox attendance4, CheckBox attendance5, CheckBox attendance6,
			CheckBox attendance7, CheckBox attendance8, CheckBox attendance9, CheckBox attendance10) {
		super(name, phoneNumber, college);
		this.courseName = courseName;
		this.previousPayment = previousPayment;
		this.payment = payment;
		this.attendance1 = attendance1;
		this.attendance2 = attendance2;
		this.attendance3 = attendance3;
		this.attendance4 = attendance4;
		this.attendance5 = attendance5;
		this.attendance6 = attendance6;
		this.attendance7 = attendance7;
		this.attendance8 = attendance8;
		this.attendance9 = attendance9;
		this.attendance10 = attendance10;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public int getPreviousPayment() {
		return previousPayment;
	}


	public void setPreviousPayment(int previousPayment) {
		this.previousPayment = previousPayment;
	}


	public TextField getPayment() {
		return payment;
	}


	public void setPayment(TextField payment) {
		this.payment = payment;
	}

	public CheckBox getAttendance1() {
		return attendance1;
	}

	public void setAttendance1(CheckBox attendance1) {
		this.attendance1 = attendance1;
	}
	

	public CheckBox getAttendance2() {
		return attendance2;
	}

	public void setAttendance2(CheckBox attendance2) {
		this.attendance2 = attendance2;
	}

	public CheckBox getAttendance3() {
		return attendance3;
	}

	public void setAttendance3(CheckBox attendance3) {
		this.attendance3 = attendance3;
	}

	public CheckBox getAttendance4() {
		return attendance4;
	}

	public void setAttendance4(CheckBox attendance4) {
		this.attendance4 = attendance4;
	}

	public CheckBox getAttendance5() {
		return attendance5;
	}

	public void setAttendance5(CheckBox attendance5) {
		this.attendance5 = attendance5;
	}

	public CheckBox getAttendance6() {
		return attendance6;
	}

	public void setAttendance6(CheckBox attendance6) {
		this.attendance6 = attendance6;
	}

	public CheckBox getAttendance7() {
		return attendance7;
	}

	public void setAttendance7(CheckBox attendance7) {
		this.attendance7 = attendance7;
	}

	public CheckBox getAttendance8() {
		return attendance8;
	}

	public void setAttendance8(CheckBox attendance8) {
		this.attendance8 = attendance8;
	}

	public CheckBox getAttendance9() {
		return attendance9;
	}

	public void setAttendance9(CheckBox attendance9) {
		this.attendance9 = attendance9;
	}

	public CheckBox getAttendance10() {
		return attendance10;
	}

	public void setAttendance10(CheckBox attendance10) {
		this.attendance10 = attendance10;
	}

}
