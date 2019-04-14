package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class Database {
	
	private static Database instance;
	private Connection connection = null;
	private final static String URL = "jdbc:mysql://localhost:3307/servletDb";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";

	private static ResultSet resultSet = null;
	private static Statement statement = null;
	private static PreparedStatement pst = null;
	private static final String SQL_GET_EMPLOYEES = "SELECT * FROM EmployeeTbl";
	private static final String SQL_SAVE_EMPLOYEE = "INSERT INTO EmployeeTbl values (?, ?, ?)";

	/**
	 * Method that loads the specified driver
	 * 
	 * @return void
	 **/

	private Database() throws ClassNotFoundException {

		Class.forName(DRIVER);
	
	}


	/**
	 * Static method that returns the instance for the singleton
	 * 
	 * @return {Connection} connection
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 **/
	public static Database getDatabase() throws ClassNotFoundException, SQLException {

		if (instance == null) {
			instance = new Database();
		}
		return instance;

	}
	
	public void openConnection() throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	/**
	 * Static method that close the connection to the database
	 * 
	 * @return void
	 **/
	public void closeConnection() {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection = null;

	}

	/**
	 * This method is used to retrieve the records from the Database
	 */
	public ResultSet getEmployeeList() {
		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_EMPLOYEES);

		} catch (Exception exObj) {
			exObj.printStackTrace();
		}
		return resultSet;
	}

	public void saveEmployee(HttpServletRequest request) {

		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String employeeSalary = request.getParameter("employeeSalary");

		try {
			pst = connection.prepareStatement(SQL_SAVE_EMPLOYEE);
			pst.setString(1, employeeId);
			pst.setString(2, employeeName);
			pst.setString(3, employeeSalary);

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
