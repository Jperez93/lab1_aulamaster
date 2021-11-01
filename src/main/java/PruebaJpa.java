

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Master;

/**
 * Servlet implementation class PruebaJpa
 */
@WebServlet("/PruebaJpa")
public class PruebaJpa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext (unitName="lab1_aulamaster")
	EntityManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaJpa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		Query q1 =  em.createQuery("Select m from Master m");
		List list = q1.getResultList();
		
		for (Object o : list) {
			Master s = (Master) o;
			out.print("<p>"+s.getId()+" "+s.getName()+" "+s.getCampus()+" "+s.getYear()+"</p>");


		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
