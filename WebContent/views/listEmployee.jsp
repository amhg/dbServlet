<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
	String driver = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3307/servletDb";
	String user = "root";
	String password = "root";
	
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
%>
<!DOCTYPE html>
<html>
<body>

<h1>Retrieve Data</h1>
<table border="1">
<tr>
<td>Name</td>
<td>Salary</td>
</tr>
<%
try{
connection = DriverManager.getConnection(URL, user, password);
statement=connection.createStatement();
String sql ="SELECT * FROM EmployeeTbl";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td><%=resultSet.getString("emp_name") %></td>
<td><%=resultSet.getString("emp_salary") %></td>
</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>