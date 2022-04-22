package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Playlists;
import metier.FactoryArtists;
import metier.HttpWrapper;

/**
 * Servlet implementation class ArtistsCreate
 */
@WebServlet("/session/artists")
public class ArtistsCreate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistsCreate() 
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
		
		try 
		{
			
			Playlists[] lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			
			request.setAttribute("playlists", lp);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/artists.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		FactoryArtists facA = new FactoryArtists();
		facA.constructArtist(request);
		
		RequestDispatcher requestDispatcher;
		
		request.setAttribute("errors", facA.getErreurs());
		
		if(facA.success) {
			response.sendRedirect("./home");
		}else {
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/artists.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
