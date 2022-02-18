package application.Objects;

public class StudentReport extends Student {
	
	private String courseName;
	private int payment;
	private char attendance1;
	private char attendance2;
	private char attendance3;
	private char attendance4;
	private char attendance5;
	private char attendance6;
	private char attendance7;
	private char attendance8;
	private char attendance9;
	private char attendance10;
	
	
	public StudentReport() {
		super();
		// TODO Auto-generated constructor stub
	}


	public StudentReport(String name, String phoneNumber, String college, String courseName, int payment,
			char attendance1, char attendance2, char attendance3, char attendance4, char attendance5, char attendance6,
			char attendance7, char attendance8, char attendance9, char attendance10) {
		super(name, phoneNumber, college);
		this.courseName = courseName;
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


	public int getPayment() {
		return payment;
	}


	public void setPayment(int payment) {
		this.payment = payment;
	}


	public char getAttendance1() {
		return attendance1;
	}


	public void setAttendance1(char attendance1) {
		this.attendance1 = attendance1;
	}


	public char getAttendance2() {
		return attendance2;
	}


	public void setAttendance2(char attendance2) {
		this.attendance2 = attendance2;
	}


	public char getAttendance3() {
		return attendance3;
	}


	public void setAttendance3(char attendance3) {
		this.attendance3 = attendance3;
	}


	public char getAttendance4() {
		return attendance4;
	}


	public void setAttendance4(char attendance4) {
		this.attendance4 = attendance4;
	}


	public char getAttendance5() {
		return attendance5;
	}


	public void setAttendance5(char attendance5) {
		this.attendance5 = attendance5;
	}


	public char getAttendance6() {
		return attendance6;
	}


	public void setAttendance6(char attendance6) {
		this.attendance6 = attendance6;
	}


	public char getAttendance7() {
		return attendance7;
	}


	public void setAttendance7(char attendance7) {
		this.attendance7 = attendance7;
	}


	public char getAttendance8() {
		return attendance8;
	}


	public void setAttendance8(char attendance8) {
		this.attendance8 = attendance8;
	}


	public char getAttendance9() {
		return attendance9;
	}


	public void setAttendance9(char attendance9) {
		this.attendance9 = attendance9;
	}


	public char getAttendance10() {
		return attendance10;
	}


	public void setAttendance10(char attendance10) {
		this.attendance10 = attendance10;
	}
	
}
