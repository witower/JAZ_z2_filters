package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//HttpSession session = request.getSession(); //czy trzeba inicjować, jeśli jesteśmy pomiędzy dwoma servletami, które wymagają danych sesji?
		
		String htmlBegin = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>Authorization required</title>\r\n" + 
				"<style>\r\n" + 
				"label {display:block}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>";
		
		String htmlEnd = "</body>\r\n"
				+ "</html>";
		
		String usersTable = "<table><tr>"
				+ "<th>Username</th>"
				+ "<th>Password</th>"
				+ "<th>Email</th>"
				+ "<th>is Premium</th>"
				+ "<th>is Admin</th>"
				+ "<th>Toggle premium</th>"
				+ "</tr><tr>";
		
		if ((request.getParameter("p")!=null) && (request.getParameter("p").equalsIgnoreCase("togglePremium"))) {
			if (repository.getUserByUsername(request.getParameter("u")) != null) {
				repository.togglePremium(request.getParameter("u"));
			}
		}
		
		List<User> db = ((DummyUserRepository) repository).getDb();
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(htmlBegin);
		
		
		
		
		for (User user : db) {
			usersTable += "<tr>" + user.toColumns()
					+ "<td><a href=\"?u=" + user.getUsername() + "&p=togglePremium\">Toggle premium</a></td></tr>";
		}
		
		usersTable += "</table>";
		
		
		
		response.getWriter().println("<h1>Oh Mighty Admin, please grant us some Premium access!</h1>");
		response.getWriter().println(usersTable);
		
		response.getWriter().println("<p><a href=\"/restricted/profile\">Go back to profile</a></p>");
		
		response.getWriter().println(htmlEnd);
		
	}
	
}
