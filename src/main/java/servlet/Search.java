package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Artists;
import entities.Musics;
import entities.Playlists;
import metier.HttpWrapper;

/**
 * Servlet implementation class Search
 */
@WebServlet("/session/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher requestDispatcher;
		
		try {
			Playlists[] lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			request.setAttribute("playlists", lp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/search.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String search = request.getParameter("search");
		if(search != null)
		{
			search = java.net.URLEncoder.encode(search, "UTF-8");
			try {
				Playlists[] lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/findByName/" + search);
				Playlists[] lp2 = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");

				Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/findByTitle/" + search);			
				Artists[] la = HttpWrapper.getMultipleInstances(Artists[].class, "http://localhost/SPOTAPI/spotapi/artists/findByName/" + search);			
				
				request.setAttribute("playlists", lp2);
				request.setAttribute("playlists_search", lp);
				request.setAttribute("musics", lm);
				request.setAttribute("artists", la);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher requestDispatcher;
		
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/search.jsp");
		requestDispatcher.forward(request, response);
	}

}
