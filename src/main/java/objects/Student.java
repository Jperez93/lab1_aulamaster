package objects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

import javax.swing.JLabel;




public class Student {
	
	private int nia;
	private String name;
	private String surname1;
	private String surname2;
	private Date dateOfBirth;
	private byte[] image;
	
	public Student(int nia, String name, String surname1, String surname2, Date dateOfBirth, byte[] image) {
		super();
		this.nia = nia;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
	}
	
	public Student() {
		super();
		this.nia = 0;
		this.name = "";
		this.surname1 = "";
		this.surname2 = "";
		this.dateOfBirth = null;
		this.image = null;
	}

	public int getNia() {
		return nia;
	}

	public void setNia(int nia) {
		this.nia = nia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public URL getEncodedImage() {
		URL url = null;
		if(image != null) {
			String urlString = new String(image);
			try {
				URL url1 = new URL(urlString);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return url;


	}

	@Override
	public String toString() {
		return "Student [nia=" + nia + ", name=" + name + ", surname1=" + surname1 + ", surname2=" + surname2
				+ ", dateOfBirth=" + dateOfBirth + ", image=" + image + "]";
	}
	
	

}
 