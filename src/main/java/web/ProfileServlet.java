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

@WebServlet({"/restricted/profile", "/restricted/profile/"})
public class ProfileServlet extends HttpServlet {

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
		
		String username = (String) session.getAttribute("user");
		User user = repository.getUserByUsername(username);
		
		session.setAttribute("isPremium", user.isPremium());
		session.setAttribute("isAdmin", user.isAdmin());
		
		String profileBody = "<p>" + user.toString() + "</p>";
		String premiumBody = "";
		String adminBody = "";
		
		if (user.isAdmin()) 
			adminBody += "<h1>Howdy Admin Allmighty!</h1>"
					+ "<p><a href=\"premium.jsp\">Checkout your Premium stuff!</a></p>"
					+ "<p><a href=\"admin\">Checkout your admin console!</a></p>";
		else if (user.isPremium()) 
			premiumBody += "<h1>Howdy Premium User!</h1>"
					+ "<p><a href=\"premium.jsp\">Checkout your Premium stuff!</a></p>";
		
		String htmlBody = profileBody + premiumBody + adminBody;
		
		htmlBody += "<p><a style='display:button' href='/logout'>Log out.</a></p>";
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(htmlBody);
		
	}


}
