// declare that this class belongs to the package "classes"
package classes;

import screens.LoginScreen;
import screens.StudentScreen;

//define the public class "App"
public class App {

	// The main method is the entry point of the application.
	public static void main(String[] args) {

		// Creates a new instance of LoginScreen and sets it visible.
		new LoginScreen().setVisible(true);

	}

}
