package com.dvops.maven.eclipse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Item = request.getParameter("Item"); //Step 2: Initialize a PrintWriter object to return the html values via the response
		String Quantity = request.getParameter("Quantity");

		String user = "bengyin";
		String secret = "GuessMe";
		
		PrintWriter writer = response.getWriter();
		// if (user.equals(username)) {
			// if (secret.equals(password)) {
				// block of code to be executed if the condition is true
				// writer.println("<h1>You have Successfully Login</h1>");
			// }
			// else {
				// writer.println("<h1>Wrong Credentials provided</h1>");
			// }	
		// }
		// else {
			// writer.println("<h1>Wrong Credentials provided</h1>");
		// }

		if (user.equals(Item) && secret.equals(Quantity)) {
			// block of code to be executed if the condition is true
			writer.println("<h1>You have Successfully Login</h1>");
		}
		else {
			writer.println("<h1>Wrong Credentials provided</h1>");
		}
		
		// writer.println("<h1>Username: " + username + " Password: " + password + " </h1>");
		writer.close();
		doGet(request, response);
	}

}
