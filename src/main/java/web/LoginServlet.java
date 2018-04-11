package web;

import java.io.IOException;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserRepository repository;
	
	public void init(ServletConfig config) throws ServletException {
		repository = new DummyUserRepository();
		User sample1 = new User();
		sample1.setUsername("user");
		sample1.setPassword("pass");
		repository.add(sample1);
		User sample2 = new User();
		sample2.setUsername("premium");
		sample2.setPassword("premium");
		sample2.setPremium(true);
		repository.add(sample2);
		User sample3 = new User();
		sample3.setUsername("admin");
		sample3.setPassword("admin");
		sample3.setAdmin(true);
		repository.add(sample3);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		
		HttpSession session = request.getSession();
		
		//response.getWriter().println("loginservlethello");
		
		User credentials = retrieveCredentialsFromRequest(request);
		
		if (repository.isAuthorized(credentials.getUsername(), credentials.getPassword())) {
			session.setAttribute("user", credentials.getUsername());
			session.setAttribute("isAuthorized", true);
			session.setAttribute("isPremium", false);
			session.setAttribute("isAdmin", false);
			response.sendRedirect("/restricted/profile");
		}
		else response.sendRedirect("/?e=wrongCredentials");
	}


	private User retrieveCredentialsFromRequest(HttpServletRequest request) {
		User result = new User();
		result.setUsername(request.getParameter("username"));
		result.setPassword(request.getParameter("pass"));
				
		return result;
		
	}

}
