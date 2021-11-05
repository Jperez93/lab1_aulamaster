package servlets;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import models.StudentModel;
import objects.Student;

/**
 * Servlet implementation class StudentsControler
 */
@MultipartConfig
@WebServlet("/StudentsControler")
public class StudentsControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentModel studentmodel;

	private byte[] castInputStreamPartIntoBytes(Part part) {
		InputStream input;
		byte[] bytes = null;
		try {
			input = part.getInputStream();
			bytes = input.readAllBytes();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	@Resource(name = "jdbc/tidw")
	private DataSource ds;

	String formView = "/studentsForm1.jsp";
	String searchView = "/lookForNia.jsp";
	String errorView = "/errorHandler.jsp";

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			studentmodel = new StudentModel(ds);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentsControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "Crear Alumno":
			addStudent(request, response);
			break;
		case "Eliminar Alumno":
			deleteStudent(request, response);
			break;
		case "Buscar Alumno":
			lookForStudent(request, response);
			break;
		case "Modificar Alumno":
			modifyStudent(request, response);
			break;
		case "Atras":
			dispatch(request, response, "/lookForNia.jsp", null);
			break;
		default:
			dispatch(request, response, "/errorHandler.jsp", null);
			break;
		}
	}

	private void modifyStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nia = Integer.parseInt(request.getParameter("nia"));
		String name = request.getParameter("name");
		String surname1 = request.getParameter("surname1");
		String surname2 = request.getParameter("surname2");
		if (name.isEmpty() || surname1.isEmpty()) {
			name = null; // If any field is empty, provoke an error in the model.
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		byte[] image = null;
		boolean result = false;
		Student student = null;
		try {
			student = studentmodel.getStudent(nia);
			date = dateformat.parse(request.getParameter("dateOfBirth"));

			Part imagePart = request.getPart("image");
			if (imagePart.getSize() == 0.0) {// If the image is not modify, don't change it
				image = student.getImage();
			} else {
				image = castInputStreamPartIntoBytes(imagePart);
			}
			student = new Student(nia, name, surname1, surname2, date, image);
			result = studentmodel.modifyStudent(student);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String message = null;
		if (!result) {
			message = "Error al modificar el estudiante";
		} else {
			message = "Estudiante modificado correctamente";
		}
		dispatch(request, response, formView, message, student);
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nia = Integer.parseInt(request.getParameter("nia"));
		String message = "Fallo al eliminar el registro";
		if (studentmodel.deleteStudent(nia))
			message = "Registro Eliminado";
		dispatch(request, response, formView, message, new Student());

	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nia = studentmodel.getLastNia() + 1;
		String name = request.getParameter("name");
		String surname1 = request.getParameter("surname1");
		String surname2 = request.getParameter("surname2");
		Part imagePart = request.getPart("image");
		byte[] image = castInputStreamPartIntoBytes(imagePart);

		if (name.isEmpty() || surname1.isEmpty()) {
			name = null; // If any field is empty, provoke an error in the model.
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateformat.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result;
		Student newStudent = new Student(nia, name, surname1, surname2, date, image);
		result = studentmodel.addNewStudent(newStudent);
		String message = null;
		if (!result) {
			message = "Registro Incorrecto";
			newStudent = new Student();
		} else {
			message = "Registro Correcto";
		}
		dispatch(request, response, formView, message, newStudent);

	}

	private void lookForStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		String snia = request.getParameter("niaToLook");
		if (!snia.isEmpty()) {
			int nia = Integer.parseInt(snia);
			try {
				student = studentmodel.getStudent(nia);
				if (student == null) {
					dispatch(request, response, searchView, "No se ha encontrado al estudiante");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		dispatch(request, response, formView, null, student);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String jsp, String message)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		request.setAttribute("MESSAGE", message);
		dispatcher.forward(request, response);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String jsp, String message,
			Student student) throws ServletException, IOException {
		request.setAttribute("STUDENT", student);
		dispatch(request, response, jsp, message);
	}

}
