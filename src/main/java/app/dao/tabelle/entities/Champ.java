package app.dao.tabelle.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Champ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	
	private int startYear;
	
	private String nation;
	
	private String resultsUrl;
	private String oddsWinUrl;
	private String oddsUoUrl;
	private String oddsHtUrl;
	
//	@OneToOne(cascade=CascadeType.ALL, mappedBy = "champ") //nome nella ENTITY ChampUrls del campo che referenzia Champ
//	private ChampUrls urls;
//
//	@OneToOne
//	private ChampImpPos impPos;
//
//	@OneToOne
//	private ChampRanCri ranCri;
//	
	
	
	public Champ() {
		super();
	}

	public Champ(	String name, 		int startYear, 		String nation,
					String resultsUrl, 	String oddsWinUrl, 	String oddsUoUrl, String oddsHtUrl) {

		this.name= name;
		this.startYear= startYear;
		this.setNation(nation);
		
		this.resultsUrl = resultsUrl;
		this.oddsWinUrl = oddsWinUrl;
		this.oddsUoUrl = oddsUoUrl;
		this.oddsHtUrl = oddsHtUrl;
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

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getResultsUrl() {
		return resultsUrl;
	}

	public void setResultsUrl(String resultsUrl) {
		this.resultsUrl = resultsUrl;
	}

	public String getOddsWinUrl() {
		return oddsWinUrl;
	}

	public void setOddsWinUrl(String oddsWinUrl) {
		this.oddsWinUrl = oddsWinUrl;
	}

	public String getOddsUoUrl() {
		return oddsUoUrl;
	}

	public void setOddsUoUrl(String oddsUoUrl) {
		this.oddsUoUrl = oddsUoUrl;
	}

	public String getOddsHtUrl() {
		return oddsHtUrl;
	}

	public void setOddsHtUrl(String oddsHtUrl) {
		this.oddsHtUrl = oddsHtUrl;
	}

	
//	public ChampImpPos getImpPos() {
//		return impPos;
//	}
//
//	public void setImpPos(ChampImpPos impPos) {
//		this.impPos = impPos;
//	}
//
//	public ChampUrls getUrls() {
//		return urls;
//	}
//
//	public void setUrls(ChampUrls urls) {
//		this.urls = urls;
//	}
//
//	public ChampRanCri getRanCri() {
//		return ranCri;
//	}
//
//	public void setRanCri(ChampRanCri ranCri) {
//		this.ranCri = ranCri;
//	}
//	
	
	
}
