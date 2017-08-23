package app.dao.tipologiche.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UoThresholdType {
	@Id
	private int id;
	
	@Column(name="value", unique=true)
	private String value;

	public UoThresholdType() {
	}

	public UoThresholdType(String value) {
		this.value = value;
	}
	public UoThresholdType(int id, String value) {
		this.id = id;
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
	
	//	_0_5, _1_5,  _2_5, _3_5, _4_5, _5_5, _6_5, _7_5, _8_5, _9_5, _10_5

	
	
	
}
