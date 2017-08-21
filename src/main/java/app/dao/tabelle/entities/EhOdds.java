package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app._0_eventsOddsDownloader.model.MatchBean;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;

@Entity
public class EhOdds implements IBet{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private BetHouse betHouse;

	@ManyToOne
	private TimeType timeType;
	
	@ManyToOne
	private HomeVariationType homeVariationType;
	
	
	@ManyToOne
	private Matcho match;
	
	private String _1;
	private String _X;
	private String _2;
	
	public EhOdds() {
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

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public HomeVariationType getHomeVariationType() {
		return homeVariationType;
	}

	public void setHomeVariationType(HomeVariationType homeVariationType) {
		this.homeVariationType = homeVariationType;
	}

	public Matcho getMatch() {
		return match;
	}

	public void setMatch(Matcho match) {
		this.match = match;
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

	
	
}
