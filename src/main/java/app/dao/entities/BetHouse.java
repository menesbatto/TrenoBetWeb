package app.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BetHouse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String value;

	public BetHouse() {
	}

	public BetHouse(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	} 
	
	
	
	
	
	//bet365, Betclic,  bwin, PaddyPower, Tipico, Unibet, WilliamHill
}
