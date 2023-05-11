// Define the Student class in the "classes" package
package classes;

// A class named Student
public class Student {

	// Define private fields for the student's ID, first name, surname, email, and
	// password
	private int studentID;
	private String firstname;
	private String surname;
	private String email;
	private String password;

	// Constructor that takes all fields as input parameters
	public Student(int studentID, String firstname, String surname, String email, String password) {

		// Assign the input values to the corresponding fields
		this.studentID = studentID;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	// Constructor that takes only the first name, surname, and email as input
	// parameters
	public Student(String firstname, String surname, String email) {
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
	}

	// Constructor that takes the student ID, first name, surname, and email as
	// input parameters
	public Student(int studentID, String firstname, String surname, String email) {
		this.studentID = studentID;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
	}

	// Getter method for the student's ID
	public int getStudentID() {
		return studentID;
	}

	// Setter method for the student's ID
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	// Getter method for the student's first name
	public String getFirstname() {
		return firstname;
	}

	// Setter method for the student's first name
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// Getter method for the student's surname
	public String getSurname() {
		return surname;
	}

	// Setter method for the student's surname
	public void setSurname(String surname) {
		this.surname = surname;
	}

	// Getter method for the student's email
	public String getEmail() {
		return email;
	}

	// Setter method for the student's email
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter method for the student's password
	public String getPassword() {
		return password;
	}

	// Setter method for the student's password
	public void setPassword(String password) {
		this.password = password;
	}
}
