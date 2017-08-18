package app.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Champ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	//Da togliere unique
	@Column(name="name", unique=true)
	private String name;
	
	private int startYear;
	
	private int endYear;

	private String nation;
	
	
	
	public Champ() {
		super();
	}

	public Champ(String name, int startYear, int endYear, String nation) {
		this.name= name;
		this.startYear= startYear;
		this.endYear = endYear;
		this.setNation(nation);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	
	
}
