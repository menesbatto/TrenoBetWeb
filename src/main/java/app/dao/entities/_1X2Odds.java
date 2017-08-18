package app.dao.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class _1X2Odds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private BetHouse betHouse;

	@ManyToOne
	private TimeType timeType;
	
	
	
	private String _1;
	private String _X;
	private String _2;
	
//	@JsonIgnore
//	private String toIgnore;
	
	
	
	public _1X2Odds() {
	}

	
	public _1X2Odds(String _1, String _X, String _2) {
 		this._1 = _1;
		this._2 = _2;
		this._X = _X;
	}


	public String get_1() {
		return _1;
	}


	public void set_1(String _1) {
		this._1 = _1;
	}


	public String get_X() {
		return _X;
	}


	public void set_X(String _X) {
		this._X = _X;
	}


	public String get_2() {
		return _2;
	}


	public void set_2(String _2) {
		this._2 = _2;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public BetHouse getBetHouse() {
		return betHouse;
	}


	public void setBetHouse(BetHouse betHouse) {
		this.betHouse = betHouse;
	}

	
	
	
}
