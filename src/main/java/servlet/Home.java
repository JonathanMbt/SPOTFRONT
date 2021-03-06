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
 * Servlet implementation class Home
 */
@WebServlet("/session/home")
public class Home extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Playlists[] lp;
	private String[] lg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() 
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
			HashMap<String, Musics[]> lmg = new HashMap<String, Musics[]>();
			
			lp = HttpWrapper.getMultipleInstances(Playlists[].class, "http://localhost/SPOTAPI/spotapi/playlists/all");
			lg = HttpWrapper.getMultipleInstances(String[].class, "http://localhost/SPOTAPI/spotapi/musics/getGenres");
			for(String g : lg)
			{
				g = java.net.URLEncoder.encode(g, "UTF-8");
				Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/findByGenre/" + g);
				lmg.put(g, lm);
			}
			
			request.setAttribute("playlists", lp);
			request.setAttribute("genres", lg);
			request.setAttribute("musicsByGenre", lmg);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher requestDispatcher;
		
		List<Users> u = new ArrayList<Users>();
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		u.add(user);
		
		int id = Integer.parseInt(request.getParameter("id"));
		try 
		{
			HashMap<String, Musics[]> lmg = new HashMap<String, Musics[]>();
			
			Musics m = HttpWrapper.getOneInstance(Musics.class, "http://localhost/SPOTAPI/spotapi/musics/findById/" + id);
			m.addUsersLikes(u);
			HttpWrapper.postOneInstance(Musics.class, "http://localhost/SPOTAPI/spotapi/musics/update/", m);
		
			for(String g : lg)
			{
				g = java.net.URLEncoder.encode(g, "UTF-8");
				Musics[] lm = HttpWrapper.getMultipleInstances(Musics[].class, "http://localhost/SPOTAPI/spotapi/musics/findByGenre/" + g);
				lmg.put(g, lm);
			}
			
			request.setAttribute("playlists", lp);
			request.setAttribute("genres", lg);
			request.setAttribute("musicsByGenre", lmg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		requestDispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
		requestDispatcher.forward(request, response);
	}

}
