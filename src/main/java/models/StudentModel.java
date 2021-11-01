package models;

import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import objects.Student;

public class StudentModel {
	
	private DataSource ds;
	
	public StudentModel(DataSource ds) {
		this.ds = ds;
	}
	
	public Student getStudent(int nia) throws Exception{
		Student foundStudent = null;//new Student();
		try {
			Connection con = ds.getConnection();
			String sqlInstr = "SELECT * FROM STUDENTS WHERE nia = ?";
			PreparedStatement sta = con.prepareStatement(sqlInstr);
			sta.setInt(1, nia);
			ResultSet res= sta.executeQuery();
			
			if(res.next()) {
				String name = res.getString("name");
				String surname1 = res.getString("surname1");
				String surname2 = res.getString("surname2");
				Date date = res.getDate("dateofbirth");
				JLabel image = getJLabel(res.getBlob("photo"));
				foundStudent = new Student(nia, name, surname1, surname2, date, image);
			}
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return foundStudent;
		
	}
	
	public int getLastNia(){
		int nia=100000000;//nia min
		try {
			Connection con = ds.getConnection();
			String sql = "SELECT MAX(nia) AS nia FROM STUDENTS";
			Statement sta = con.createStatement();
			ResultSet res = sta.executeQuery(sql);
			
			if(res.next() && res.getInt("nia") != 0) {
				nia = res.getInt("nia");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return nia;
	}
	
	private JLabel getJLabel(Blob blob) {
		JLabel image = new JLabel();
		try {//Fetch photo and casting from Blob into JLabel
			byte[] readBytes = blob.getBytes(1, (int) blob.length());
		    ImageIcon imageIcon = new ImageIcon(readBytes);
		    image.setIcon(imageIcon);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public boolean addNewStudent(Student newStudent) {
		try {
			Connection con = ds.getConnection();
			String sqlInstr = "INSERT INTO STUDENTS (nia, name, surname1, surname2, dateofbirth) VALUES (?,?,?,?,?)";
			PreparedStatement sta = con.prepareStatement(sqlInstr);
			sta.setInt(1, newStudent.getNia());
			sta.setString(2, newStudent.getName().toUpperCase());
			sta.setString(3, newStudent.getSurname1().toUpperCase());
			sta.setString(4, newStudent.getSurname2().toUpperCase());
			java.util.Date utilDate = newStudent.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			sta.setDate(5, sqlDate);
			sta.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
			
	}

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

	public boolean modifyStudent(Student student) {
		try {
			Connection con = ds.getConnection();
			String sql = "UPDATE STUDENTS SET name = ?, surname1 = ?, surname2 = ?, dateofbirth = ? WHERE nia = ?";
			PreparedStatement sta = con.prepareStatement(sql);
			sta.setString(1, student.getName().toUpperCase());
			sta.setString(2, student.getSurname1().toUpperCase());
			sta.setString(3, student.getSurname2().toUpperCase());
			java.util.Date utilDate = student.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			sta.setDate(4, sqlDate);
			sta.setInt(5, student.getNia());
			sta.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
