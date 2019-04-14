package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;

public class postController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Employee Details";
	private static final String PAGE_TITLE = "Servlet Database Connectivity Example";
	private static final String DOCTYPE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handlePost(request, response);
	}
	
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Database instance = null; 
		
		//read form fields
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String employeeSalary = request.getParameter("employeeSalary");
		
		try {
			
        	instance = Database.getDatabase();
        	instance.openConnection();
        	instance.saveEmployee(request);
        	
        	
		}catch(Exception exObj){
        	exObj.printStackTrace();
        }finally {
        	if(instance != null) 
        	instance.closeConnection();
	
        }
		
		RequestDispatcher rd = request.getRequestDispatcher("/getEmployee");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//handleRequest(request, response);

	}
	
	
}























