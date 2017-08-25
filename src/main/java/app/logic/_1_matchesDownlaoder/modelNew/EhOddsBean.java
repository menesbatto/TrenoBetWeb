package app.logic._1_matchesDownlaoder.modelNew;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.dao.tabelle.entities.Matcho;

public class EhOddsBean implements Serializable, IBetBean{
	
	private static final long serialVersionUID = 2839273854528890133L;

	private int id;
	
	private String _1;
	private String _X;
	private String _2;
	
	private String betHouseString;
	
	private String timeTypeString;
	
	private String homeVariationTypeString;
	
	private MatchBean match;
	
	
	public EhOddsBean() {
	}


	public EhOddsBean(String _1, String _X, String _2) {
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


	public String getBetHouseString() {
		return betHouseString;
	}


	public void setBetHouseString(String betHouseString) {
		this.betHouseString = betHouseString;
	}


	public String getTimeTypeString() {
		return timeTypeString;
	}


	public void setTimeTypeString(String timeTypeString) {
		this.timeTypeString = timeTypeString;
	}


	public MatchBean getMatch() {
		return match;
	}


	public void setMatch(MatchBean match) {
		this.match = match;
	}


	public String getHomeVariationTypeString() {
		return homeVariationTypeString;
	}


	public void setHomeVariationTypeString(String homeVariationTypeString) {
		this.homeVariationTypeString = homeVariationTypeString;
	}





}
