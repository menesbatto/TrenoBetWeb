package app._0_eventsOddsDownloader.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BetBean implements Serializable{
	
	private static final long serialVersionUID = 2839273854528890162L;

	private int id;
	
	private String _1;
	private String _X;
	private String _2;
	
	@JsonIgnore
	private String toIgnore;
	
	
	public BetBean() {
		toIgnore = "non lo vedo";
	}


	public BetBean(String _1, String _X, String _2) {
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

	public String getToIgnore() {
		return toIgnore;
	}
	public void setToIgnore(String toIgnore) {
		this.toIgnore = toIgnore;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


}
