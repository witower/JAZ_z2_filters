package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import repositories.UserRepository;
import repositories.DummyUserRepository;

@WebServlet({"/restricted/admin", "/restricted/admin/"})
public class AdminServlet extends HttpServlet {

	/**s
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserRepository repository;
	
	public void init(ServletConfig config) throws ServletException {
		repository = new DummyUserRepository();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		
		HttpSession session = request.getSession();
		
		List<User> db = ((DummyUserRepository) repository).getDb();
		
		if ((request.getParameter("p")!=null) && (request.getParameter("p")=="togglePremium")) {
			repository.togglePremium(request.getParameter("u"));
		}
		
		
		String usersTable = "<table><tr>"
				+ "<th>Username</th>"
				+ "<th>Password</th>"
				+ "<th>Email</th>"
				+ "<th>is Premium</th>"
				+ "<th>is Admin</th>"
				+ "<th>Toggle premium</th>"
				+ "</tr><tr>";
		
		for (User user : db) {
			usersTable += "<tr>" + user.toColumns()
					+ "<td><a href=\"?u=" + user.getUsername() + "&p=togglePremium\">Toggle premium</a></td></tr>";
		}
		
		usersTable += "</table>";
		
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("<h1>Oh Mighty Admin, please grant us some Premium access!</h1>");
		response.getWriter().println(usersTable);
		
		response.getWriter().println("<p><a href=\"/restricted/profile\">Go back to profile</a></p>");
		
	}
	
}
