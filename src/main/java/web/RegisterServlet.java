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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserRepository repository;
	
	public void init(ServletConfig config) throws ServletException {
		repository = new DummyUserRepository();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		
		HttpSession session = request.getSession();
				
		
		User user = retrieveUserFromRequest(request);
		
		if (repository.getUserByUsername(user.getUsername()) != null)
		response.sendRedirect("/registration.jsp/?e=userAlreadyExists");
		else if (user.getPassword().equals(request.getParameter("passconfirm"))) {
			repository.add(user);
			session.setAttribute("freshmeat", true);
			response.sendRedirect("/?info=SuccessfullRegistration");
		}
		else response.sendRedirect("/registration.jsp/?e=passwordMismatch");
	}


	private User retrieveUserFromRequest(HttpServletRequest request) {
		User result = new User();
		result.setUsername(request.getParameter("username"));
		result.setEmail(request.getParameter("email"));
		result.setPassword(request.getParameter("pass"));
				
		return result;
		
	}

}
