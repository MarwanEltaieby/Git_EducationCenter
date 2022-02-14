package application.Objects;

public class Secretary {
	
	private String username;
	private String password;
	private String instructorUsername;
	
	public Secretary() {
		
	}
	
	public Secretary(String instructorUsername, String username, String password) {
		this.instructorUsername = instructorUsername;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getInstructorUsername() {
		return instructorUsername;
	}

	public void setInstructorUsername(String instructorUsername) {
		this.instructorUsername = instructorUsername;
	}
	
}
