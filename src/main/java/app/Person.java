package app;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;

	 @ManyToOne
	 private Team supportTeam; 
	 
	 @OneToMany(cascade = {CascadeType.ALL})
	 private List<Phone> phoneList;
	
	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;;
		this.lastName = lastName;;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public Team getSupportTeam() {
		return supportTeam;
	}

	public void setSupportTeam(Team supportTeam) {
		this.supportTeam = supportTeam;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}
	
	
	
}
