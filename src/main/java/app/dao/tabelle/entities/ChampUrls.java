//package app.dao.tabelle.entities;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//import app.utils.RankCritEnum;
//
//@Entity
//public class ChampUrls {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int id;
//
//	@OneToOne
//	@JoinColumn(name = "champ_id")	//nome in questa tabella del riferimento a champ
//	private Champ champ;
//	
////	this.importantPositions = new ArrayList<Integer>(Arrays.asList(importantPositions));
////	this.rankCriteria = new ArrayList<RankCritEnum>(Arrays.asList(rankCriteria));
//	private String resultsUrl;
//	private String oddsWinUrl;
//	private String oddsUoUrl;
//	private String oddsHtUrl;
//	
//	
//	
//	
//	public ChampUrls(Champ champ, String resultsUrl, String oddsWinUrl, String oddsUoUrl, String oddsHtUrl) {
//		super();
//		this.champ = champ;
//		this.resultsUrl = resultsUrl;
//		this.oddsWinUrl = oddsWinUrl;
//		this.oddsUoUrl = oddsUoUrl;
//		this.oddsHtUrl = oddsHtUrl;
//	}
//
//	public Champ getChamp() {
//		return champ;
//	}
//
//	public void setChamp(Champ champ) {
//		this.champ = champ;
//	}
//
//	public String getOddsWinUrl() {
//		return oddsWinUrl;
//	}
//
//	public void setOddsWinUrl(String oddsWinUrl) {
//		this.oddsWinUrl = oddsWinUrl;
//	}
//
//	public String getOddsUoUrl() {
//		return oddsUoUrl;
//	}
//
//	public void setOddsUoUrl(String oddsUoUrl) {
//		this.oddsUoUrl = oddsUoUrl;
//	}
//
//	public String getOddsHtUrl() {
//		return oddsHtUrl;
//	}
//
//	public void setOddsHtUrl(String oddsHtUrl) {
//		this.oddsHtUrl = oddsHtUrl;
//	}
//
//	public ChampUrls() {
//		super();
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getResultsUrl() {
//		return resultsUrl;
//	}
//
//	public void setResultsUrl(String resultsUrl) {
//		this.resultsUrl = resultsUrl;
//	}
//
//}
