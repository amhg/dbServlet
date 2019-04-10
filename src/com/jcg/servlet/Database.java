package com.jcg.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;


public class Database {

	private static Connection connection = null;
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
     * @return void
     **/
	private static void loadDriver() {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			System.out.println("Failed to load the driver " + DRIVER + e.getMessage());
		}
	}

	/**
     * Method that loads the connection into the right property
     * @return void
     **/
	private static void loadConnection() {

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
	/**
     * Static method that returns the instance for the singleton
     * @return {Connection} connection
     **/
	public static Connection getConnection() {
		
		if(connection == null) {
			loadDriver();
			loadConnection();
		}
		return connection;

	}
	
	/**
     * Static method that close the connection to the database
     * @return void
     **/
	public static void closeConnection() {
		
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
    public static ResultSet getEmployeeList() {             
        try {
        	
        	statement = getConnection().createStatement();
            resultSet = statement.executeQuery(SQL_GET_EMPLOYEES);
            
        } catch (Exception exObj) {
            exObj.printStackTrace();
        }
        return resultSet;
    }
    
    public static void saveEmployee(HttpServletRequest request) {
    	
    	String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String employeeSalary = request.getParameter("employeeSalary");
    	
    	try {
    		pst = getConnection().prepareStatement(SQL_SAVE_EMPLOYEE);
    		pst.setString(1, employeeId);
    		pst.setString(2, employeeName);
    		pst.setString(3, employeeSalary);
    		
    		pst.executeUpdate();
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
