package app._0_eventsOddsDownloader.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MatchBean implements Serializable{

	private static final long serialVersionUID = -3928046995109408135L;

	private int id;
	
	private TeamBean homeTeam;

	private TeamBean awayTeam;
	
	private int fullTimeHomeGoals;
	private int fullTimeAwayGoals;

	private int halfTimeHomeGoals;
	private int halfTimeAwayGoals;
	
	private Date matchDate;
	
	private ChampBean champ;

	private List<_1X2OddsBean> _1X2;

	private List<EhOddsBean> eh;
	
	private List<UoOddsBean> uo;
	
	public MatchBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TeamBean getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamBean homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamBean getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamBean awayTeam) {
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

	public ChampBean getChamp() {
		return champ;
	}

	public void setChamp(ChampBean champ) {
		this.champ = champ;
	}

	public List<_1X2OddsBean> get_1X2() {
		return _1X2;
	}

	public void set_1X2(List<_1X2OddsBean> _1x2odds) {
		_1X2 = _1x2odds;
	}

	public List<EhOddsBean> getEh() {
		return eh;
	}

	public void setEh(List<EhOddsBean> ehOdds) {
		this.eh = ehOdds;
	}

	public List<UoOddsBean> getUo() {
		return uo;
	}

	public void setUo(List<UoOddsBean> uoOdds) {
		this.uo = uoOdds;
	}

	
	
	
}
