package app.dao.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Matcho {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Team homeTeam;

	@ManyToOne
	private Team awayTeam;
	
	private int fullTimeHomeGoals;
	private int fullTimeAwayGoals;

	private int halfTimeHomeGoals;
	private int halfTimeAwayGoals;
	
	private Date matchDate;
	
	@ManyToOne
	private Champ champ;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<_1X2Odds> _1X2odds;

	public Matcho() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getFullTimeHomeGoals() {
		return fullTimeHomeGoals;
	}

	public void setFullTimeHomeGoals(int fullTimeHomeGoals) {
		this.fullTimeHomeGoals = fullTimeHomeGoals;
	}

	public int getFullTimeAwayGoals() {
		return fullTimeAwayGoals;
	}

	public void setFullTimeAwayGoals(int fullTimeAwayGoals) {
		this.fullTimeAwayGoals = fullTimeAwayGoals;
	}

	public int getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public void setHalfTimeHomeGoals(int halfTimeHomeGoals) {
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public int getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public void setHalfTimeAwayGoals(int halfTimeAwayGoals) {
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Champ getChamp() {
		return champ;
	}

	public void setChamp(Champ champ) {
		this.champ = champ;
	}

	public List<_1X2Odds> get_1X2odds() {
		return _1X2odds;
	}

	public void set_1X2odds(List<_1X2Odds> _1x2odds) {
		_1X2odds = _1x2odds;
	}

	
	
	
}
