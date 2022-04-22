package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Playlists;
import entities.Users;
import metier.FactoryPlaylists;
import metier.FactoryUsers;
import metier.HttpWrapper;

/**
 * Servlet implementation class PlaylistsCreate
 */
@WebServlet("/session/playlists")
public class PlaylistsCreate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaylistsCreate() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher requestDispatcher;
		
		try 
		{
			
			Playlists[] lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			
			request.setAttribute("playlists", lp);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/playlists.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		FactoryPlaylists facP = new FactoryPlaylists();
		facP.constructPlaylist(request);
		
		RequestDispatcher requestDispatcher;
		
		request.setAttribute("errors", facP.getErreurs());
		
		if(facP.success) {
			response.sendRedirect("./home");
		}else {
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/playlists.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
