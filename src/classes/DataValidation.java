package classes;

public class DataValidation {

	private boolean isValid; // a boolean variable to indicate if the data is valid or not

	public DataValidation() {
		isValid = true; // initializes isValid to true
	}

	public String checkString(String text) {
		// Presence check
		if (text.isEmpty()) {
			isValid = false; // set isValid to false as text is not present
			return "Please fill in"; // return an error message
		}

		// Length check
		if (text.length() < 2 || text.length() > 30) {
			isValid = false; // set isValid to false as text length is invalid
			return "Data length invalid"; // return an error message
		}

		// Letters only check
		String temp = text.replaceAll("[^A-Za-z ]", ""); // replace all non-letter characters with empty space
		if (temp.length() != text.length()) {
			isValid = false; // set isValid to false as text contains non-letter characters
			return "Must contain letters only"; // return an error message
		}

		return ""; // return empty string if no errors found
	}

	public String checkEmail(String text) {

		// Presence check
		if (text.isEmpty()) {
			isValid = false; // set isValid to false as text is not present
			return "Please fill in"; // return an error message
		}

		// Length check
		if (text.length() < 21 || text.length() > 50) {
			isValid = false; // set isValid to false as text length is invalid
			return "Data length invalid"; // return an error message
		}

		// Check if the email address is a St Mary's email address
		if (!text.endsWith("@stmarysschool.co.za")) {
			isValid = false; // set isValid to false as email address is not a St Mary's email address
			return "Must be a St Mary's email address"; // return an error message
		}

		return ""; // return empty string if no errors found
	}

	public String checkPrice(String sPrice) {

		if (sPrice.isEmpty()) {
			isValid = false; // set isValid to false as price is not present
			return "Please fill in"; // return an error message
		}

		try {
			double price = Double.parseDouble(sPrice); // convert sPrice to a double
		} catch (NumberFormatException e) {
			isValid = false; // set isValid to false as price is not a number
			return "Price must be a number"; // return an error message
		}

		return ""; // return empty string if no errors found
	}

	public boolean isValid() {
		return isValid; // return the value of isValid
	}
}
