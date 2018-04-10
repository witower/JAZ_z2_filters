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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

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
		
		String username = (String) session.getAttribute("user");
		User user = repository.getUserByUsername(username);
		
		String profileBody = "<p>" + user.toString() + "</p>";
		String premiumBody = "";
		String adminBody = "";
		
		
		
		if (user.isPremium()) 
			premiumBody += "<h1>Howdy Premium User!</h1>"
					+ "<p><a href=\"/premium.jsp\">Checkout your Premium stuff!</a></p>";
		
		if (user.isAdmin()) 
			adminBody += "<h1>Howdy Admin Allmighty!</h1>"
					+ "<p><a href=\"/premium.jsp\">Checkout your Premium stuff!</a></p>"
					+ "<p><a href=\"/admin\">Checkout your admin console!</a></p>";
		
		String htmlBody = profileBody + premiumBody + adminBody;
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(htmlBody);
		
		
		//response.getWriter().println(""
		//		+ "<p><a href=\"/logout\">Logout</a></p>");
		
	}


}
