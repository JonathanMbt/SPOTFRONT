package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Artists;
import entities.Playlists;
import metier.FactoryArtists;
import metier.FactoryMusics;
import metier.HttpWrapper;

/**
 * Servlet implementation class MusicsCreate
 */
@WebServlet("/session/musics")
public class MusicsCreate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicsCreate() 
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
			Artists[] la = HttpWrapper.getMultipleInstances(Artists[].class, "http://localhost/SPOTAPI/spotapi/artists/all");

			request.setAttribute("playlists", lp);
			request.setAttribute("artists", la);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/musics.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		FactoryMusics facM = new FactoryMusics();
		facM.constructMusics(request);
		
		RequestDispatcher requestDispatcher;
		
		request.setAttribute("errors", facM.getErreurs());
		
		if(facM.success) {
			response.sendRedirect("./home");
		}else {
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/musics.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
