package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nia;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	private String name;

	@Lob
	private byte[] photo;

	private String surname1;

	private String surname2;

	//bi-directional many-to-many association to Master
	@ManyToMany
	@JoinTable(
		name="inscriptions"
		, joinColumns={
			@JoinColumn(name="student")
			}
		, inverseJoinColumns={
			@JoinColumn(name="master")
			}
		)
	private List<Master> masters;

	public Student() {
	}

	public int getNia() {
		return this.nia;
	}

	public void setNia(int nia) {
		this.nia = nia;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getSurname1() {
		return this.surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return this.surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public List<Master> getMasters() {
		return this.masters;
	}

	public void setMasters(List<Master> masters) {
		this.masters = masters;
	}

}