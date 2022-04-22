package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Musics;
import entities.Playlists;
import entities.Users;
import metier.HttpWrapper;

/**
 * Servlet implementation class LikedMusics
 */
@WebServlet("/session/likedMusics")
public class LikedMusics extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private Playlists[] lp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikedMusics() 
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
			
			HttpSession session = request.getSession();
			Users u = (Users) session.getAttribute("user");
			
			lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			String username = java.net.URLEncoder.encode(u.getUsername(), "UTF-8");
			Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/findMusicsLikedByUser/" + username);
			
			request.setAttribute("playlists", lp);
			request.setAttribute("musics", lm);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/likedMusics.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher requestDispatcher;
		
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		
		int id = Integer.parseInt(request.getParameter("id"));
		try 
		{
			
			Musics m = HttpWrapper.getOneInstance(Musics.class, "http://localhost/SPOTAPI/spotapi/musics/findById/" + id);
			m.removeUserLike(user.getUsername());
			HttpWrapper.postOneInstance(Musics.class, "http://localhost/SPOTAPI/spotapi/musics/update/", m);
		
			String username = java.net.URLEncoder.encode(user.getUsername(), "UTF-8");
			Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/findMusicsLikedByUser/" + username);
			
			request.setAttribute("playlists", lp);
			request.setAttribute("musics", lm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/likedMusics.jsp");
		requestDispatcher.forward(request, response);
	}

}
