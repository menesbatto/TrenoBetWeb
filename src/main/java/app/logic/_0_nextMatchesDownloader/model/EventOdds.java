package app.logic._0_nextMatchesDownloader.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.utils.Utils;

public class EventOdds implements Serializable, Comparable<EventOdds>{
	private static final long serialVersionUID = 7778211632531238253L;

	private ResultGoodness homeResultGoodness;
	private ResultGoodness awayResultGoodness;
	private ResultGoodness totalResultGoodness;
	
	private String homeTeam;
	private String awayTeam;
	
	private Double oddsH;
	private Double oddsD;
	private Double oddsA;
	
	private Double oddsHalfTimeH;
	private Double oddsHalfTimeD;
	private Double oddsHalfTimeA;
	
	private Double oddsHD;
	private Double oddsDA;
	private Double oddsHA;
	
	private Double oddsU;
	private Double oddsO;
	
	private Date date;

	private String homeTrend;
	private String awayTrend;

	private String homeTrendUo;
	private String awayTrendUo;

	private Double homeMotivation;
	private Double awayMotivation;
	
	private BetType betType;
	private MatchResultEnum matchResult;
	private Double winOdds;

	@Override
	public String toString() {
		if (homeResultGoodness == null){
			return homeTeam + " - " + awayTeam + 
				
				"\n\tWIN\t" + oddsH + "\t" + oddsD + "\t" + oddsA + 
				
				"\n\tUO\t" + oddsU + "\t" + oddsO + "\n";
		}
		else {
			String s =  
	
				Utils.redimString(homeTeam,16) + " " + Utils.redimString(awayTeam,16) + "\t\t" + betType + "\t" + matchResult + "\t" + getFormattedDate() + "\t" + winOdds + "\n" + 
	
				Utils.redimString(homeTrend,16) + " " + Utils.redimString(homeTrendUo,16) + " - " + Utils.redimString(awayTrend,16) + " - " + Utils.redimString(awayTrendUo,16) +
				
				"\n\tQUOTE\t | " + "1 - " + "\t" + oddsH + "\t\t\t\t | " + "X - " + "\t" + oddsD + "\t | " + "2 - " + "\t" + oddsA + "\t\t\t\t | " + "U - " + oddsU + "\t" + "O - " + oddsO +
				
//				"\n\tWIN\t | " + oddsH + "\t\t\t\t\t | " + oddsD + "\t | " + oddsD +"\t\t\t\t\t | " + oddsU + "\t\t" + oddsO +

				"\n\t\t | " + "clea" + "\t" + "mot" + "\t" + "withMot" + "\t" + "withTre" + "\t" + "withAll" + "\t | " + "clea" + "\t\t | "  + "clea" + "\t" + "mot" + "\t" + "withMot" + "\t" + "withTre" + "\t" + "withAll" + "\t | " + "%" + "\t\t" + "%" +
				
	//			"\n\tGOOD\t | " + homeResultGoodness.getGoodnessW() + "\t" + Utils.redimString(homeResultGoodness.getGoodnessWwithTrends()) + "\t | " + homeResultGoodness.getGoodnessD() + "\t | " + homeResultGoodness.getGoodnessL() + "\t" + Utils.redimString(homeResultGoodness.getGoodnessLwithTrends()) + "\t\t | " + homeResultGoodness.getGoodnessU() + "\t" + homeResultGoodness.getGoodnessO() +  
	//			
	//			"\n\tGOOD\t | " + awayResultGoodness.getGoodnessL() + "\t" + Utils.redimString(awayResultGoodness.getGoodnessLwithTrends()) + "\t | " + awayResultGoodness.getGoodnessD() + "\t | " + awayResultGoodness.getGoodnessW() + "\t" + Utils.redimString(awayResultGoodness.getGoodnessWwithTrends()) + "\t\t | " +awayResultGoodness.getGoodnessU() + "\t" + awayResultGoodness.getGoodnessO() + "\n"; 
				
				"\n\tH GOOD\t | " + Utils.redimString(homeResultGoodness.getGoodnessW()) + "\t" + Utils.redimString(homeMotivation) + "\t" + Utils.redimString(homeResultGoodness.getGoodnessWwithMotivation()) + "\t" + Utils.redimString(homeResultGoodness.getGoodnessWwithTrends()) + "\t" + Utils.redimString(homeResultGoodness.getGoodnessWfinal()) + "\t | " + homeResultGoodness.getGoodnessD() + "\t\t | " +  Utils.redimString(homeResultGoodness.getGoodnessL()) + "\t" + Utils.redimString(homeMotivation) + "\t" +  Utils.redimString(homeResultGoodness.getGoodnessLwithMotivation()) + "\t" +  Utils.redimString(homeResultGoodness.getGoodnessLwithTrends()) + "\t" +  Utils.redimString(homeResultGoodness.getGoodnessLfinal()) + "\t | " +  Utils.redimString(homeResultGoodness.getGoodnessU()+"",5) + "\t" +  Utils.redimString(homeResultGoodness.getGoodnessO()+"",5) +  
				
				"\n\tA GOOD\t | " + Utils.redimString(awayResultGoodness.getGoodnessL()) + "\t" + Utils.redimString(awayMotivation) + "\t" + Utils.redimString(awayResultGoodness.getGoodnessLwithMotivation()) + "\t" + Utils.redimString(awayResultGoodness.getGoodnessLwithTrends()) + "\t" + Utils.redimString(awayResultGoodness.getGoodnessLfinal()) + "\t | " + awayResultGoodness.getGoodnessD() + "\t\t | " +  Utils.redimString(awayResultGoodness.getGoodnessW()) + "\t" + Utils.redimString(awayMotivation) + "\t" +  Utils.redimString(awayResultGoodness.getGoodnessWwithMotivation()) + "\t" +  Utils.redimString(awayResultGoodness.getGoodnessWwithTrends()) + "\t" +  Utils.redimString(awayResultGoodness.getGoodnessWfinal()) + "\t | " +  Utils.redimString(awayResultGoodness.getGoodnessU()+"",5) + "\t" +  Utils.redimString(awayResultGoodness.getGoodnessO()+"",5) + "\n\n";
				
//				"\n\tA GOOD\t | " + awayResultGoodness.getGoodnessL() + "\t" + Utils.redimString(awayMotivation) + "\t | " + awayResultGoodness.getGoodnessD() + "\t | " + awayResultGoodness.getGoodnessW() + "\t" + Utils.redimString(awayMotivation) + "\t\t | " + awayResultGoodness.getGoodnessU() + "\t" + awayResultGoodness.getGoodnessO() + "\n\n"; 
	
			return s;
		}
	}


	private String getFormattedDate() {
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        return dt1.format(date);
    }


	public String getHomeTeam() {
		return homeTeam;
	}



	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}



	public String getAwayTeam() {
		return awayTeam;
	}



	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}



	public Double getOddsH() {
		return oddsH;
	}



	public void setOddsH(Double oddsH) {
		this.oddsH = oddsH;
	}



	public Double getOddsD() {
		return oddsD;
	}



	public void setOddsD(Double oddsD) {
		this.oddsD = oddsD;
	}



	public Double getOddsA() {
		return oddsA;
	}



	public void setOddsA(Double oddsA) {
		this.oddsA = oddsA;
	}



	public Double getOddsHalfTimeH() {
		return oddsHalfTimeH;
	}



	public void setOddsHalfTimeH(Double oddsHalfTimeH) {
		this.oddsHalfTimeH = oddsHalfTimeH;
	}



	public Double getOddsHalfTimeD() {
		return oddsHalfTimeD;
	}



	public void setOddsHalfTimeD(Double oddsHalfTimeD) {
		this.oddsHalfTimeD = oddsHalfTimeD;
	}



	public Double getOddsHalfTimeA() {
		return oddsHalfTimeA;
	}



	public void setOddsHalfTimeA(Double oddsHalfTimeA) {
		this.oddsHalfTimeA = oddsHalfTimeA;
	}



	public Double getOddsHD() {
		return oddsHD;
	}



	public void setOddsHD(Double oddsHD) {
		this.oddsHD = oddsHD;
	}



	public Double getOddsDA() {
		return oddsDA;
	}



	public void setOddsDA(Double oddsDA) {
		this.oddsDA = oddsDA;
	}



	public Double getOddsHA() {
		return oddsHA;
	}



	public void setOddsHA(Double oddsHA) {
		this.oddsHA = oddsHA;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Double getOddsU() {
		return oddsU;
	}



	public void setOddsU(Double oddsU) {
		this.oddsU = oddsU;
	}



	public Double getOddsO() {
		return oddsO;
	}



	public void setOddsO(Double oddsO) {
		this.oddsO = oddsO;
	}


	public ResultGoodness getHomeResultGoodness() {
		return homeResultGoodness;
	}


	public void setHomeResultGoodness(ResultGoodness homeResultGoodness) {
		this.homeResultGoodness = homeResultGoodness;
	}


	public ResultGoodness getAwayResultGoodness() {
		return awayResultGoodness;
	}


	public void setAwayResultGoodness(ResultGoodness awayResultGoodness) {
		this.awayResultGoodness = awayResultGoodness;
	}


	public ResultGoodness getTotalResultGoodness() {
		return totalResultGoodness;
	}


	public void setTotalResultGoodness(ResultGoodness totalResultGoodness) {
		this.totalResultGoodness = totalResultGoodness;
	}


	public String getHomeTrend() {
		return homeTrend;
	}


	public void setHomeTrend(String homeTrend) {
		this.homeTrend = homeTrend;
	}


	public String getAwayTrend() {
		return awayTrend;
	}


	public void setAwayTrend(String awayTrend) {
		this.awayTrend = awayTrend;
	}


	public Double getHomeMotivation() {
		return homeMotivation;
	}


	public void setHomeMotivation(Double homeMotivation) {
		this.homeMotivation = homeMotivation;
	}


	public Double getAwayMotivation() {
		return awayMotivation;
	}


	public void setAwayMotivation(Double awayMotivation) {
		this.awayMotivation = awayMotivation;
	}


	public BetType getBetType() {
		return betType;
	}


	public void setBetType(BetType betType) {
		this.betType = betType;
	}


	public MatchResultEnum getMatchResult() {
		return matchResult;
	}


	public void setMatchResult(MatchResultEnum matchResult) {
		this.matchResult = matchResult;
	}



	
	
	public int compareTo(EventOdds eo) {
		if(date.before(eo.getDate()))
			return -1;
		return 1;

	}


	public Double getWinOdds() {
		return winOdds;
	}


	public void setWinOdds(Double winOdds) {
		this.winOdds = winOdds;
	}


	public String getHomeTrendUo() {
		return homeTrendUo;
	}


	public void setHomeTrendUo(String homeTrendUo) {
		this.homeTrendUo = homeTrendUo;
	}


	public String getAwayTrendUo() {
		return awayTrendUo;
	}


	public void setAwayTrendUo(String awayTrendUo) {
		this.awayTrendUo = awayTrendUo;
	}
	
	
}
