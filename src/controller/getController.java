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

@WebServlet(urlPatterns = "/getEmployee")
public class getController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Employee Details";
	private static final String PAGE_TITLE = "Servlet Database Connectivity Example";
	private static final String DOCTYPE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleRequest(request, response);
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Database instance = null;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        out.println(DOCTYPE + "<html>\n" + "<head><title>" + PAGE_TITLE + "</title></head>\n");
        out.println("<body>" + TITLE);
        
        try {
        	instance = Database.getDatabase();
        	instance.openConnection();
        	ResultSet rs = instance.getEmployeeList();
        	
        	while(rs.next()) {
        		out.print("<p>" + rs.getString("emp_name") + "," + rs.getString("emp_salary") + "</p>");
        	}
        	
        }catch(Exception exObj){
        	exObj.printStackTrace();
        }finally {
        	if(instance != null) 
        	instance.closeConnection();
	
        }
        
        out.println("</body></html>");
           	
	}
	
}























