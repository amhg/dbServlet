package com.jcg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/servletDemo")
public class DbDemo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Employee Details";
	private static final String PAGE_TITLE = "Servlet Database Connectivity Example";
	private static final String DOCTYPE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleRequest(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handlePost(request, response);
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        out.println(DOCTYPE + "<html>\n" + "<head><title>" + PAGE_TITLE + "</title></head>\n");
        out.println("<body>" + TITLE);
        
        try {
        	ResultSet rs = Database.getEmployeeList();
        	
        	while(rs.next()) {
        		out.print("<p>" + rs.getString("emp_name") + "," + rs.getString("emp_salary") + "</p>");
        	}
        	
        }catch(Exception exObj){
        	exObj.printStackTrace();
        }finally {
        	Database.closeConnection();
        }
        
        out.println("</body></html>");
           	
	}
	
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//read form fields
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String employeeSalary = request.getParameter("employeeSalary");
		
		//do some processing here
		
		Database.saveEmployee(request);
		
		//get response writer
		PrintWriter writer = response.getWriter();
		
		//build html code
		String htmlRespone = "<html>";
        htmlRespone += "Your Id is: " + employeeId + "<br/>";
        htmlRespone += "Your name is: " + employeeName + "<br/>";
        htmlRespone += "Your salary is: " + employeeSalary;    
        htmlRespone += "</html>"; 
        
        //return response
        writer.println(htmlRespone);
	}
	
	
}























