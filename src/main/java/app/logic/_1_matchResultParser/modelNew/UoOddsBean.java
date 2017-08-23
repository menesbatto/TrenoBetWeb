package app.logic._1_matchResultParser.modelNew;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.dao.tabelle.entities.Matcho;

public class UoOddsBean implements Serializable, IBetBean{
	
	private static final long serialVersionUID = 2839273334528890162L;

	private int id;
	
	private String u;
	private String o;
	
	private String betHouseString;
	
	private String timeTypeString;

	private String thresholdTypeString;
	
	private MatchBean match;
	
	@JsonIgnore
	private String toIgnore;
	
	
	public UoOddsBean() {
		toIgnore = "non lo vedo";
	}


	public UoOddsBean(String u, String o) {
 		this.u = u;
		this.o = o;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getU() {
		return u;
	}


	public void setU(String u) {
		this.u = u;
	}


	public String getO() {
		return o;
	}


	public void setO(String o) {
		this.o = o;
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


	public String getThresholdTypeString() {
		return thresholdTypeString;
	}


	public void setThresholdTypeString(String thresholdTypeString) {
		this.thresholdTypeString = thresholdTypeString;
	}


	public MatchBean getMatch() {
		return match;
	}


	public void setMatch(MatchBean match) {
		this.match = match;
	}


	public String getToIgnore() {
		return toIgnore;
	}


	public void setToIgnore(String toIgnore) {
		this.toIgnore = toIgnore;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
