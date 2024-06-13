package application;

public class Student {
	private String id;
	private String userName;
	private String phoneNumber;
	private String email;
	private String level;

	public Student(String id, String userName, String phoneNumber, String email, String level) {
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.setLevel(level);
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
