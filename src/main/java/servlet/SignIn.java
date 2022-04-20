package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Users;
import metier.FactoryUsers;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/signin")
public class SignIn extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher requestDispatcher;
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		FactoryUsers facU = new FactoryUsers();
		Users u = facU.constructUser(request);
		
		RequestDispatcher requestDispatcher;
		
		request.setAttribute("errors", facU.getErreurs());
		HttpSession session = request.getSession();
		
		if(facU.success) {
			session.setAttribute("user", u);
			response.sendRedirect("session/home");
		}else {
			session.setAttribute("user", null);
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
