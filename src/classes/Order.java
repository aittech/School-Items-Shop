package classes;

import java.time.LocalDate;

public class Order {

	private int orderID; // represents the ID of the order
	private String studentID; // represents the ID of the student who made the order
	private LocalDate orderDate; // represents the date when the order was made
	private Boolean collected; // represents whether the order has been collected or not

	public Order(int orderID, String studentID, LocalDate orderDate, Boolean collected) {
		this.orderID = orderID; // initializes the order ID
		this.studentID = studentID; // initializes the student ID
		this.orderDate = orderDate; // initializes the order date
		this.collected = collected; // initializes whether the order has been collected or not
	}

	public int getOrderID() {
		return orderID; // returns the order ID
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID; // sets the order ID
	}

	public String getStudentID() {
		return studentID; // returns the student ID
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID; // sets the student ID
	}

	public LocalDate getOrderDate() {
		return orderDate; // returns the order date
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate; // sets the order date
	}

	public Boolean getCollected() {
		return collected; // returns whether the order has been collected or not
	}

	public void setCollected(Boolean collected) {
		this.collected = collected; // sets whether the order has been collected or not
	}
}
