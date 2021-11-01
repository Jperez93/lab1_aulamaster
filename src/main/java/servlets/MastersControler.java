package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import model.Master;
import models.MasterModel;

/**
 * Servlet implementation class MastersControler
 */
@WebServlet("/MastersControler")
public class MastersControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MasterModel mastermodel;

	@PersistenceContext(unitName = "lab1_aulamaster")
	private EntityManager em;
	@Resource
	private UserTransaction ut;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MastersControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			mastermodel = new MasterModel(em, ut);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "Buscar Master":
			searchMaster(request, response);
			break;

		default:
			break;
		}

	}

	private void searchMaster(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Master> list = null;
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("year", request.getParameter("year"));
		parameters.put("campus", request.getParameter("campus"));
		parameters.put("name", request.getParameter("name"));
		String sql = "";
		try {
			list = mastermodel.getMastersList(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/searchMaster.jsp");
		request.setAttribute("LISTOFMASTERS", list);
		dispatcher.forward(request, response);

	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String jsp, String message)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		request.setAttribute("MESSAGE", message);
		dispatcher.forward(request, response);
	}

	/*
	 * private void dispatch(HttpServletRequest request, HttpServletResponse
	 * response,String jsp, String message) throws ServletException, IOException {
	 * request.setAttribute("STUDENT", student); dispatch(request, response, jsp,
	 * message); }
	 */
}
