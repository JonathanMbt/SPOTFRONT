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
 * Servlet implementation class Artist
 */
@WebServlet("/session/artist")
public class Artist extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Artist() 
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
		String id = request.getParameter("a");
		
		try 
		{
			
			Playlists[] lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			Artists a = HttpWrapper.getOneInstance(Artists.class,  "http://localhost/SPOTAPI/spotapi/artists/findByExactName/"+id);
			Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/findByArtist/"+id);
			
			
			request.setAttribute("playlists", lp);
			request.setAttribute("artist", a);
			request.setAttribute("musics", lm);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/artist.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int musicId = Integer.parseInt(request.getParameter("music"));
		String artistId = request.getParameter("a");
		
		if(request.getParameter("deleteArtist") != null)
		{
			try 
			{
				artistId = java.net.URLEncoder.encode(artistId, "UTF-8");
				HttpWrapper.deleteOneInstance(boolean.class, "http://localhost/SPOTAPI/spotapi/artists/delete/" + artistId);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}else
		{
			try 
			{
				HttpWrapper.deleteOneInstance(boolean.class, "http://localhost/SPOTAPI/spotapi/musics/delete/" + musicId);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		response.sendRedirect("./artist?a=" + artistId);
	}

}
