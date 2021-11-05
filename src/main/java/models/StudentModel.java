package models;

import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import objects.Student;

public class StudentModel {
	/**
	 * This model establishes the connection with the database and obteins all the information requested by the Students Controler
	 */

	private DataSource ds;

	public StudentModel(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * 
	 * @param nia
	 * @return
	 * @throws Exception
	 * 
	 * Returns a student instance with the information of the register whit the same nia, given in the input
	 */
	public Student getStudent(int nia) throws Exception {
		Student foundStudent = null;// new Student();
		try {
			Connection con = ds.getConnection();
			String sqlInstr = "SELECT * FROM STUDENTS WHERE nia = ?";
			PreparedStatement sta = con.prepareStatement(sqlInstr);
			sta.setInt(1, nia);
			ResultSet res = sta.executeQuery();

			if (res.next()) {
				String name = res.getString("name");
				String surname1 = res.getString("surname1");
				String surname2 = res.getString("surname2");
				Date date = res.getDate("dateofbirth");
				byte[] image = castBlobtoByte(res.getBlob("photo"));
				foundStudent = new Student(nia, name, surname1, surname2, date, image);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return foundStudent;

	}

	/**
	 * 
	 * @param blob
	 * @return
	 * 
	 * This is an auxiliary method which casts the information of type Blob (an image stored in the database) to an array of bytes
	 */
	private byte[] castBlobtoByte(Blob blob) {
		byte[] bytes = null;
		if (blob != null) {
			try {
				int blobLength = (int) blob.length();
				bytes = blob.getBytes(1, blobLength);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bytes;
	}

	/**
	 * 
	 * @return
	 * 
	 * This is an auxiliary method which returns the max nia stored in the database
	 */
	public int getLastNia() {
		int nia = 100000000;// nia min
		try {
			Connection con = ds.getConnection();
			String sql = "SELECT MAX(nia) AS nia FROM STUDENTS";
			Statement sta = con.createStatement();
			ResultSet res = sta.executeQuery(sql);

			if (res.next() && res.getInt("nia") != 0) {
				nia = res.getInt("nia");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nia;
	}

	/**
	 * 
	 * @param newStudent
	 * @return
	 * 
	 * Given an instance of the Entity Student, it creates a new register with the information in the database
	 */
	public boolean addNewStudent(Student newStudent) {
		try {
			Connection con = ds.getConnection();
			String sqlInstr = "INSERT INTO STUDENTS (nia, name, surname1, surname2, dateofbirth, photo) VALUES (?,?,?,?,?,?)";
			PreparedStatement sta = con.prepareStatement(sqlInstr);
			sta.setInt(1, newStudent.getNia());
			sta.setString(2, newStudent.getName().toUpperCase());
			sta.setString(3, newStudent.getSurname1().toUpperCase());
			sta.setString(4, newStudent.getSurname2().toUpperCase());
			java.util.Date utilDate = newStudent.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			sta.setDate(5, sqlDate);
			Blob blob = null;
			blob = new SerialBlob(newStudent.getImage());
			sta.setBlob(6, blob);
			sta.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/*
	 * Given a nia, it deletes the register in the database with the same key attribute
	 */
	public boolean deleteStudent(int nia) {
		try {
			Connection con = ds.getConnection();
			String sql = "DELETE FROM STUDENTS WHERE nia = ?";
			PreparedStatement sta = con.prepareStatement(sql);
			sta.setInt(1, nia);
			sta.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	/*
	 * 
	 * Given an instance of the Entity Student, it overwrites the information of the register with the same nia in the database
	 */
	public boolean modifyStudent(Student student) {
		try {
			Connection con = ds.getConnection();
			String sql = "UPDATE STUDENTS SET name = ?, surname1 = ?, surname2 = ?, dateofbirth = ?, photo = ? WHERE nia = ?";
			PreparedStatement sta = con.prepareStatement(sql);
			sta.setString(1, student.getName().toUpperCase());
			sta.setString(2, student.getSurname1().toUpperCase());
			sta.setString(3, student.getSurname2().toUpperCase());
			java.util.Date utilDate = student.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			sta.setDate(4, sqlDate);
			Blob blob = null;
			blob = new SerialBlob(student.getImage());
			sta.setBlob(5, blob);
			sta.setInt(6, student.getNia());
			sta.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
