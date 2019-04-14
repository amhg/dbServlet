
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>

<%
	String employeeId = request.getParameter("employeeId");
	String employeeName = request.getParameter("employeeName");
	String employeeSalary = request.getParameter("employeeSalary");

	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/servletDb", "root", "root");
		PreparedStatement pst = conn.prepareStatement("INSERT INTO EmployeeTbl values (?, ?, ?)");

		pst.setString(1, employeeId);
		pst.setString(2, employeeName);
		pst.setString(3, employeeSalary);

		pst.executeUpdate();

		out.println("Data is successfully inserted!");
	} catch (Exception e) {
		System.out.print(e);
		e.printStackTrace();
	}
%>
