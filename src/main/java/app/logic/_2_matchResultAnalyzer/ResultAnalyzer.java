package app.logic._2_matchResultAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.TeamDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.IOUtils;

@Service
public class ResultAnalyzer {
	@Autowired
	private WinRangeStatsDao winRangeStatsDao;

	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private TimeTypeDao timeTypeDao;
	
	private static HashMap<ChampEnum, ArrayList<MatchResult>> allMatchesResults;
	private static HashMap<ChampEnum, ArrayList<String>> allTeams;
	
	private static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> allAnalyzedChampsWinHome;
	private static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> allAnalyzedChampsWinAway;
	private static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> allAnalyzedChampsWinAll;
	
	private static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> allAnalyzedChampsUoHome;
	private static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> allAnalyzedChampsUoAway;
	private static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> allAnalyzedChampsUoAll;
	private static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> teamToMatchesAll;
	private static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> teamToMatchesAway;
	private static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> teamToMatchesHome;
//	private static int no365 = 0;
//	private static int all = 0;
//	private static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> champToTeamToMatchesAll;
	
//	
//	public static void main(String args[]) {
//		execute(null);
//	}
	
//	public ArrayList<MatchResult> execute(HashMap<ChampEnum, ArrayList<MatchResult>> allMatchesResultsInput){
	public ArrayList<MatchResult> execute(){
//		allMatchesResults = allMatchesResultsInput;
//		initStaticFields();
		
		for (ChampEnum champ : ChampEnum.values()){
//			System.out.println(champ);
			analizeSingleChampionshipWin(champ);
		}
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_WIN_HOME_PATH, allAnalyzedChampsWinHome);
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_WIN_AWAY_PATH, allAnalyzedChampsWinAway);
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_WIN_ALL_PATH, allAnalyzedChampsWinAll);
		
		
//		System.out.println(allAnalyzedChampsUoHome);
	
//		System.out.println(all);
		return null;
	
}

	private void analizeSingleChampionshipWin(ChampEnum champ) {
				
		analyzeWinOdds(champ);
		analyzeUnderOverOdds(champ);
		
		
//		System.out.println(teamToRangeStatsHome);
//		System.out.println(teamToRangeStatsAll);
		
	}

	private void analyzeUnderOverOdds(ChampEnum champ) {
		
		HashMap<String, GoalsStatsBean> teamToUOStatsHome = new HashMap<String, GoalsStatsBean>();
		HashMap<String, GoalsStatsBean> teamToUOStatsAway = new HashMap<String, GoalsStatsBean>();
		HashMap<String, GoalsStatsBean> teamToUOStatsAll = new HashMap<String, GoalsStatsBean>();
		
		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesHome.get(champ).entrySet()) {
		    analyzeTeamResultUo(entry.getKey(), entry.getValue(), teamToUOStatsHome);
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		
		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesAway.get(champ).entrySet()) {
			analyzeTeamResultUo(entry.getKey(), entry.getValue(), teamToUOStatsAway);
			//System.out.println(entry.getKey() + "/" + entry.getValue());
		}

		
//		System.out.println(allAnalyzedChampsUoHome);
//		System.out.println(allAnalyzedChampsUoAway);

		allAnalyzedChampsUoHome.put(champ, teamToUOStatsHome);
		allAnalyzedChampsUoAway.put(champ, teamToUOStatsAway);
//		allAnalyzedChampsUoAll.put(champ, teamToRangeStatsAll);
		
		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_UO_HOME_PATH, allAnalyzedChampsUoHome);
		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_UO_AWAY_PATH, allAnalyzedChampsUoAway);
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_ALL_PATH, allAnalyzedChampsWinAll);
		
		
	}

	private static void analyzeTeamResultUo(String teamName, ArrayList<MatchResult> matches, HashMap<String, GoalsStatsBean> teamToUOStats) {
		GoalsStatsBean statUO = new GoalsStatsBean();
		Integer strikedGoalsTot = 0;
		Integer takenGoalsTot = 0;
		Integer strikedGoals = 0;
		Integer takenGoals = 0;
		Integer allGoals = 0;
		
		
		for (MatchResult m : matches) {
			if (m.getFTHG() == null){
				continue;
			}
			if (teamName.equals(m.getHomeTeam())){
				strikedGoals = m.getFTHG();
				takenGoals = m.getFTAG();
			}
			else if (teamName.equals(m.getAwayTeam())){
				takenGoals = m.getFTAG();
				strikedGoals = m.getFTHG();
			}
			
			allGoals = takenGoals + strikedGoals;
					
			
			if (allGoals == 0){
				statUO.setUnder0_5Hit(statUO.getUnder0_5Hit()+1);
				statUO.setUnder1_5Hit(statUO.getUnder1_5Hit()+1);
				statUO.setUnder2_5Hit(statUO.getUnder2_5Hit()+1);
				statUO.setUnder3_5Hit(statUO.getUnder3_5Hit()+1);
				statUO.setUnder4_5Hit(statUO.getUnder4_5Hit()+1);
			}
			else if (allGoals == 1) {
				statUO.setOver0_5Hit(statUO.getOver0_5Hit()+1);
				statUO.setUnder1_5Hit(statUO.getUnder1_5Hit()+1);
				statUO.setUnder2_5Hit(statUO.getUnder2_5Hit()+1);
				statUO.setUnder3_5Hit(statUO.getUnder3_5Hit()+1);
				statUO.setUnder4_5Hit(statUO.getUnder4_5Hit()+1);
			}
			else if (allGoals == 2) {
				statUO.setOver0_5Hit(statUO.getOver0_5Hit()+1);
				statUO.setOver1_5Hit(statUO.getOver1_5Hit()+1);
				statUO.setUnder2_5Hit(statUO.getUnder2_5Hit()+1);
				statUO.setUnder3_5Hit(statUO.getUnder3_5Hit()+1);
				statUO.setUnder4_5Hit(statUO.getUnder4_5Hit()+1);
			}
			else if (allGoals == 3) {
				statUO.setOver0_5Hit(statUO.getOver0_5Hit()+1);
				statUO.setOver1_5Hit(statUO.getOver1_5Hit()+1);
				statUO.setOver2_5Hit(statUO.getOver2_5Hit()+1);
				statUO.setUnder3_5Hit(statUO.getUnder3_5Hit()+1);
				statUO.setUnder4_5Hit(statUO.getUnder4_5Hit()+1);
			}
			else if (allGoals == 4) {
				statUO.setOver0_5Hit(statUO.getOver0_5Hit()+1);
				statUO.setOver1_5Hit(statUO.getOver1_5Hit()+1);
				statUO.setOver2_5Hit(statUO.getOver2_5Hit()+1);
				statUO.setOver3_5Hit(statUO.getOver3_5Hit()+1);
				statUO.setUnder4_5Hit(statUO.getUnder4_5Hit()+1);
			}
			else if (allGoals >= 5) {
				statUO.setOver0_5Hit(statUO.getOver0_5Hit()+1);
				statUO.setOver1_5Hit(statUO.getOver1_5Hit()+1);
				statUO.setOver2_5Hit(statUO.getOver2_5Hit()+1);
				statUO.setOver3_5Hit(statUO.getOver3_5Hit()+1);
				statUO.setOver4_5Hit(statUO.getOver4_5Hit()+1);
			}
			
			strikedGoalsTot += strikedGoals;
			takenGoalsTot += takenGoals;
			
		}
		
		statUO.setTotalGoals(strikedGoalsTot + takenGoalsTot);
		statUO.setStrikedGoalsTotal(strikedGoalsTot);
		statUO.setTakenGoalsTotal(takenGoalsTot);
		
		statUO.setTotalMatches(matches.size());
		
		statUO.setOver0_5Perc(new Double(statUO.getOver0_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setOver1_5Perc(new Double(statUO.getOver1_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setOver2_5Perc(new Double(statUO.getOver2_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setOver3_5Perc(new Double(statUO.getOver3_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setOver4_5Perc(new Double(statUO.getOver4_5Hit())/new Double(statUO.getTotalMatches()));

		statUO.setUnder0_5Perc(new Double(statUO.getUnder0_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setUnder1_5Perc(new Double(statUO.getUnder1_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setUnder2_5Perc(new Double(statUO.getUnder2_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setUnder3_5Perc(new Double(statUO.getUnder3_5Hit())/new Double(statUO.getTotalMatches()));
		statUO.setUnder4_5Perc(new Double(statUO.getUnder4_5Hit())/new Double(statUO.getTotalMatches()));

		statUO.setTeamName(teamName); 
		
		teamToUOStats.put(teamName, statUO);
		
	}

	private void analyzeWinOdds(ChampEnum champ) {
		
//		System.out.println(teamToMatchesAway.get("Juventus"))

//		HashMap<String, ArrayList<WinRangeStatsBean>> teamToRangeStatsHome = new HashMap<String, ArrayList<WinRangeStatsBean>>();
//		HashMap<String, ArrayList<WinRangeStatsBean>> teamToRangeStatsAway = new HashMap<String, ArrayList<WinRangeStatsBean>>();
//		HashMap<String, ArrayList<WinRangeStatsBean>> teamToRangeStatsAll = new HashMap<String, ArrayList<WinRangeStatsBean>>();

		ArrayList<String> teams = teamDao.findByChamp(champ);
		for (String teamName : teams) {
			ArrayList<MatchResult> teamHomeMatches = matchDao.getDownloadedPastMatchByChampAndHomeTeam(champ, teamName);
			analyzeTeamResultWin(teamName, teamHomeMatches, champ, "H");
		}
//		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesHome.get(champ).entrySet()) {
//		    analyzeTeamResultWin(entry.getKey(), entry.getValue(), champ);
//		    //System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		enrichTeamResult(teamToRangeStatsHome, "HOME");
		
		
		for (String teamName : teams) {
			ArrayList<MatchResult> teamHomeMatches = matchDao.getDownloadedPastMatchByChampAndAwayTeam(champ, teamName);
			analyzeTeamResultWin(teamName, teamHomeMatches, champ, "A");
		}
//		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesAway.get(champ).entrySet()) {
//			analyzeTeamResultWin(entry.getKey(), entry.getValue(), champ);
//			//System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		enrichTeamResult(teamToRangeStatsAway, "AWAY");
		for (String teamName : teams) {
			winRangeStatsDao.calculateWinStatsNoPlayingField(teamName, champ);
		}
//		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesAll.get(champ).entrySet()) {
//			analyzeTeamResultWin(entry.getKey(), entry.getValue(), champ);
//			//System.out.println(entry.getKey() + "/" + entry.getValue());
//		}

//		allAnalyzedChampsWinHome.put(champ, teamToRangeStatsHome);
//		allAnalyzedChampsWinAway.put(champ, teamToRangeStatsAway);
//		allAnalyzedChampsWinAll.put(champ, teamToRangeStatsAll);
		
		
	}
	
	private static void enrichTeamResult(ArrayList<WinRangeStatsBean> rangeList, String where) {
		for (WinRangeStatsBean range : rangeList){
			Double winPerc = null;
			Double drawPerc = null;
			Double losePerc = null;
			if (range.getTotal() != null && range.getTotal() != 0){
				if (where.equals("H")){
					winPerc = new Double(range.getHomeHits()) / new Double (range.getTotal());
					drawPerc = new Double(range.getDrawHits()) / new Double (range.getTotal());
					losePerc = new Double(range.getAwayHits()) / new Double (range.getTotal());
				}
				else {//if (where.equals("A")){
					winPerc = new Double(range.getAwayHits()) / new Double (range.getTotal());
					drawPerc = new Double(range.getDrawHits()) / new Double (range.getTotal());
					losePerc = new Double(range.getHomeHits()) / new Double (range.getTotal());
				}
			}
			range.setWinPerc(winPerc);
			range.setDrawPerc(drawPerc);
			range.setLosePerc(losePerc);
		}
	}


	// Ogni volta che l'atalanta che gioca in casa � quotata a una quota che va da 1,5 a 1,7 allora finora si � comportata cosi. 
	// Ogni volta che l'atalanta che gioca fuori casa � quotata a una quota che va da 1,5 a 1,7 allora finora si � comportata cosi. 
	private void analyzeTeamResultWin(String teamName, ArrayList<MatchResult> matches, ChampEnum champ, String playingField) {
		//ArrayList<WinRangeStatsBean> ranges = initStats(teamName);
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		
		for (TimeTypeEnum timeType : timeTypes) {
			
			ArrayList<WinRangeStatsBean> ranges = initWinStatsByTimeType(teamName, champ, timeType, playingField);
			
			for (MatchResult m : matches){
				if (m.getFTHG() == null){
					continue;
				}
				
				
				Integer homeGoals = 0;
				Integer awayGoals = 0;
				String resultString = "";
				_1x2Leaf avg1x2Odds =  m.get_1x2().get(timeType).getAvg1x2Odds();
				Double homeOdds = avg1x2Odds.getOdd1();
				Double drawOdds = avg1x2Odds.getOddX();
				Double awayOdds = avg1x2Odds.getOdd2();
				MatchResultEnum result = null;

				switch (timeType) {
					case _final:
						homeGoals = m.getFTHG();
						awayGoals = m.getFTAG();
						resultString = m.getFTR();
						result = MatchResultEnum.valueOf(resultString);
						break;
					case _1:
						homeGoals = m.getHTHG();
						awayGoals = m.getHTAG();
						resultString = m.getHTR();
						result = MatchResultEnum.valueOf(resultString);
						break;
					case _2:
						homeGoals = m.getFTHG() - m.getHTHG();
						awayGoals = m.getFTAG() - m.getHTAG();
						
						if (homeGoals > awayGoals)				resultString = "H";
						else if (homeGoals == awayGoals)		resultString = "D";
						else									resultString = "A";
						
						result = MatchResultEnum.valueOf(resultString);
						break;
	
					default:
						break;
				}
				
				
				if (homeOdds == null || drawOdds == null || awayOdds == null){
	//				System.out.println("NO BET 365 " + no365++);
					homeOdds = m.getBWH();
					drawOdds = m.getBWD();
					awayOdds = m.getBWA();
				}
				if (homeOdds == null || drawOdds == null || awayOdds == null){
					homeOdds = m.getPSCH();
					drawOdds = m.getPSCD();
					awayOdds = m.getPSCA();
				}	
				if (homeOdds == null || drawOdds == null || awayOdds == null){
					homeOdds = 0.0;
					drawOdds = 0.0;
					awayOdds = 0.0;
				}
	//			all++;
				Double percHome = 1/homeOdds;
				Double percDraw = 1/drawOdds;
				Double percAway = 1/awayOdds;
				
				Double percTotal = percHome + percDraw + percAway;
	
				Double percHomeAdjusted = percHome  / percTotal;
				Double percDrawAdjusted = percDraw  / percTotal;
				Double percAwayAdjusted = percAway  / percTotal;
				
				Double homeOddsAdjusted = 1 / percHomeAdjusted;
				Double drawOddsAdjusted = 1 / percDrawAdjusted;
				Double awayOddsAdjusted = 1 / percAwayAdjusted;
				
				
				
//				Double under2_5Odds = m.getBbAvU2_5();
//				Double over2_5Odds = m.getBbAvO2_5();
				
				Double oddsOfTeamAnalyzed = null;
				
				// capisce se la quota su cui andare a inserire la statistica � della squadra in casa o fuoricasa
				if (teamName.equals(m.getHomeTeam()))
					oddsOfTeamAnalyzed = homeOddsAdjusted;
				else 
					oddsOfTeamAnalyzed = awayOddsAdjusted;
				
				updateRangeStats(ranges, result, oddsOfTeamAnalyzed);
//				switch (result) {
//				case H:		updateRangeStats(ranges, MatchResultEnum.H, oddsOfTeamAnalyzed);				break;
//				case D:		updateRangeStats(ranges, MatchResultEnum.D, oddsOfTeamAnalyzed);				break;
//				case A:		updateRangeStats(ranges, MatchResultEnum.A, oddsOfTeamAnalyzed);				break;
//				default:				break;
//				}
				
			}
			
			if (!matches.isEmpty())
				enrichTeamResult(ranges, playingField);
		
			List<WinRangeStats> saveWinRangeStats = winRangeStatsDao.saveWinRangeStats(ranges, teamName, champ, timeType, playingField);
		}
		//teamToRangeStats.put(teamName, ranges);
			
		
	}

	private static void updateRangeStats(List<WinRangeStatsBean> ranges, MatchResultEnum result, Double hitOdds) {
		for (WinRangeStatsBean range : ranges) {
			if (hitOdds < range.getEdgeUp()){
				if (result.equals(MatchResultEnum.H)){
					range.setHomeHits(range.getHomeHits() + 1);
					range.setDrawMisses(range.getDrawMisses() + 1);
					range.setAwayMisses(range.getAwayMisses() + 1);
				}
				else if (result.equals(MatchResultEnum.D)){
					range.setHomeMisses(range.getHomeMisses() + 1);
					range.setDrawHits(range.getDrawHits() + 1);
					range.setAwayMisses(range.getAwayMisses() + 1);
				}
				else {//if (result.equals(Result.A)){
					range.setHomeMisses(range.getHomeMisses() + 1);
					range.setDrawMisses(range.getDrawMisses() + 1);
					range.setAwayHits(range.getAwayHits() + 1);
				}
				range.setTotal(range.getTotal() + 1);
				break;
			}
		}
		
	}

	private ArrayList<WinRangeStatsBean> initWinStatsByTimeType(String teamName, ChampEnum champ, TimeTypeEnum timeType, String playingField) {
		
		ArrayList<WinRangeStatsBean> winRangeStatsList = winRangeStatsDao.findByTeamNameAndChampAndTimeType(teamName, champ, timeType, playingField);
//		ArrayList<WinRangeStatsBean> stats = new ArrayList<WinRangeStatsBean>();
//		WinRangeStatsBean current;
//		for (int i = 0; i < AppConstants.RANGE_EDGES.size()-1; i++) {
//			current = new WinRangeStatsBean();
//			current.setTeamName(teamName);
//			current.setEdgeDown(AppConstants.RANGE_EDGES.get(i));
//			current.setEdgeUp(AppConstants.RANGE_EDGES.get(i+1));
//			current.setRange(current.getEdgeDown() + " - " + current.getEdgeUp());
//			stats.add(current);
//		}
		
		return winRangeStatsList;
	}

	private static void initStaticFields() {
//		if (allMatchesResults == null)
//			allMatchesResults =  ResultParserOld.retrieveAllMatchResults();
////		allMatchesResults.get(ChampEnum.SPA_LIGA)
////		System.out.println(matches);
//		allTeams =  ResultParserOld.retrieveTeams();
//		allAnalyzedChampsWinHome = new HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStats>>>();
//		allAnalyzedChampsWinAway = new HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStats>>>();
//		allAnalyzedChampsWinAll = new HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStats>>>();
//		
//		allAnalyzedChampsUoHome = new HashMap<ChampEnum, HashMap<String, GoalsStats>>();
//		allAnalyzedChampsUoAway = new HashMap<ChampEnum, HashMap<String, GoalsStats>>();
//		
//		
//		teamToMatchesHome = new HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>>();
//		teamToMatchesAway = new HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>>();
//		teamToMatchesAll = new HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>>();
//		
//		for (ChampEnum champ : ChampEnum.values()){
//			
//			teamToMatchesHome.put(champ, new HashMap<String, ArrayList<MatchResult>>());
//			teamToMatchesAway.put(champ, new HashMap<String, ArrayList<MatchResult>>());
//			teamToMatchesAll.put(champ, new HashMap<String, ArrayList<MatchResult>>());
//			for (String teamName : allTeams.get(champ)){
//				teamToMatchesHome.get(champ).put(teamName, new ArrayList<MatchResult>());
//				teamToMatchesAway.get(champ).put(teamName, new ArrayList<MatchResult>());
//				teamToMatchesAll.get(champ).put(teamName, new ArrayList<MatchResult>());
//			}
//	
//			for (MatchResult mr : allMatchesResults.get(champ)){
//				teamToMatchesHome.get(champ).get(mr.getHomeTeam()).add(mr);
//				
//				teamToMatchesAway.get(champ).get(mr.getAwayTeam()).add(mr);
//				
//				teamToMatchesAll.get(champ).get(mr.getHomeTeam()).add(mr);
//				teamToMatchesAll.get(champ).get(mr.getAwayTeam()).add(mr);
//			}
//		}
//		
//		IOUtils.write(AppConstants.TEAM_TO_MATCHES_ALL_PATH, teamToMatchesAll);

	}
	
	public static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>>  retrieveTeamsToRangeStatsWinHome() {
		HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> teamToRangeStatsWinHome = IOUtils.read(AppConstants.TEAM_TO_RANGE_STATS_WIN_HOME_PATH,  HashMap.class);
		return teamToRangeStatsWinHome;
	}
	
	public static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>>  retrieveTeamsToRangeStatsWinAway() {
		HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> teamToRangeStatsWinAway = IOUtils.read(AppConstants.TEAM_TO_RANGE_STATS_WIN_AWAY_PATH,  HashMap.class);
		return teamToRangeStatsWinAway;
	}
	
	public static  HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>>  retrieveTeamsToRangeStatsWinAll() {
		HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> teamToRangeStatsWinAll = IOUtils.read(AppConstants.TEAM_TO_RANGE_STATS_WIN_ALL_PATH,  HashMap.class);
		return teamToRangeStatsWinAll;
	}

	public static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>>  retrieveTeamsToRangeStatsUoHome() {
		HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> teamToRangeStatsUoHome = IOUtils.read(AppConstants.TEAM_TO_RANGE_STATS_UO_HOME_PATH,  HashMap.class);
		return teamToRangeStatsUoHome;
	}
	
	public static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>>  retrieveTeamsToRangeStatsUoAway() {
		HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> teamToRangeStatsUoAway = IOUtils.read(AppConstants.TEAM_TO_RANGE_STATS_UO_AWAY_PATH,  HashMap.class);
		return teamToRangeStatsUoAway;
	}
	
	public static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>>  retrieveTeamsToMatchesAll() {
		HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> teamToMatchesAll = IOUtils.read(AppConstants.TEAM_TO_MATCHES_ALL_PATH,  HashMap.class);
		return teamToMatchesAll;
	}
	
	
}
