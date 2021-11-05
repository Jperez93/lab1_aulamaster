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
		case "Ver Detalles":
			showMasterDetails(request, response, null);
			break;
		case "Inscribir Alumno":
			updateMasterInscriptions(request, response, true);
			break;
		case "Eliminar Alumno":
			updateMasterInscriptions(request, response, false);
			break;
		default:
			break;
		}

	}

	private void extracted(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(message);
	}
	

	private void updateMasterInscriptions(HttpServletRequest request, HttpServletResponse response, boolean insert)
			throws IOException, ServletException {
		int nia = Integer.parseInt(request.getParameter("nia"));
		int masterId = Integer.parseInt(request.getParameter("masterId"));
		boolean fail = true;
		String message = null;
		try {
			if(mastermodel.UpdateMasterInscriptions(nia, masterId, insert)) {
				if(insert) {
					message = "No se pudo inscribir al estudiante, compruebe si ya está inscrito en este master o si no está registrado";
				}else {
					message = "No se pudo eliminar al estudiante, compruebe si el estudiante está inscrito o si no está registrado"; //Shuldn't send this message, Should it be unreachable
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Algo no fue bien, intentelo más tarde";
		}

		showMasterDetails(request, response, message);
	}

	private void showMasterDetails(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException, ServletException {
		Master master = new Master();
		int masterId = Integer.parseInt(request.getParameter("masterId"));
		try {
			master = mastermodel.getMaster(masterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispatch(request, response, "/Info-Master.jsp",message, master);
		}

	private void searchMaster(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Master> list = null;
		HashMap<String, String> parameters = new HashMap<String, String>();
		String year =request.getParameter("year");
		String campus =request.getParameter("campus");
		String name =request.getParameter("name");
		parameters.put("year", request.getParameter("year"));
		parameters.put("campus", request.getParameter("campus"));
		parameters.put("name", request.getParameter("name"));
		try {
			list = mastermodel.getMastersList(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispatch(request, response, "/SearchMasters.jsp", null, list);

	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String jsp, String message)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		request.setAttribute("MESSAGE", message);
		dispatcher.forward(request, response);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String jsp, String message, Master master) throws ServletException, IOException {
		request.setAttribute("MASTER", master);
		dispatch(request, response, jsp, message);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String jsp, String message, List<Master> listOfMasters) throws ServletException, IOException {
		request.setAttribute("LISTOFMASTERS", listOfMasters);
		dispatch(request, response, jsp, message);
	}
}
