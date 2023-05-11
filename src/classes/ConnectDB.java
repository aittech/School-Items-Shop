/**
This class represents a connection to a database and provides methods to execute queries and update the database.
*/

package classes;

import java.sql.*;

public class ConnectDB {

	private Connection conn; // Represents the connection to the database
	private Statement stmt; // Represents a statement to execute queries
	private ResultSet rs; // Represents a set of results returned by a query

	/**
	 * Constructor to establish the connection to the database.
	 */
	public ConnectDB() {
		try {
			String url = "jdbc:ucanaccess://PATDB.accdb"; // URL at which the database is stored on the computer and
															// driver to connect to the database
			conn = DriverManager.getConnection(url); // Establishes the connection to the database
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("Problem Connecting to database " + e);
		}
	}

	/**
	 * Method to execute a query and return a ResultSet of results.
	 * 
	 * @param qry the query to execute
	 * @return the ResultSet of results
	 */
	public ResultSet getResults(String qry) {
		try {
			rs = stmt.executeQuery(qry); // Executes the query passed in through the parameter and returns the resultset
		} catch (Exception e) {
			System.out.println("Error occurred: " + e);
		}
		return rs; // Returns the resultset to the user to be used
	}

	/**
	 * Method to execute an update query.
	 * 
	 * @param qry the update query to execute
	 * @throws SQLException if there is a problem updating the database
	 */
	public void UpdateDatabase(String qry) throws SQLException {
		stmt.executeUpdate(qry);
	}

	/**
	 * Getter for the Connection object.
	 * 
	 * @return the Connection object
	 */
	public Connection getConn() {
		return conn;
	}
}
