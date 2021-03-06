package model;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the master database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Master.findAll", query="SELECT m FROM Master m"),
	@NamedQuery(name="Master.findById", query="SELECT m FROM Master m WHERE m.id = :id"),
	@NamedQuery(name="Master.findByName", query="SELECT m FROM Master m WHERE m.name = :name"),
	@NamedQuery(name="Master.findByYear", query="SELECT m FROM Master m WHERE m.year = :year"),
	@NamedQuery(name="Master.findByCampus", query="SELECT m FROM Master m WHERE m.campus = :campus")


})
public class Master implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String campus;

	private String name;

	private int year;
	
	private String description;

	//bi-directional many-to-many association to Student
	@ManyToMany(mappedBy="masters")
	private List<Student> students;

	public Master() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getCampus() {
		return this.campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}