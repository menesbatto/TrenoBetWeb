package app.logic._2_matchResultAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.TeamDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.dao.tabelle.entities.GoalsStats;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.OddsRange;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.UoThresholdStats;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.IOUtils;

@Service
public class ResultAnalyzer {
	@Autowired
	private WinRangeStatsDao winRangeStatsDao;

	@Autowired
	private GoalsStatsDao goalsStatsDao;

	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private TimeTypeDao timeTypeDao;
	
	@Autowired
	private OddsRangeDao oddsRangeDao;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;
	
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

	public ArrayList<MatchResult> execute(){
		
		for (ChampEnum champ : ChampEnum.values()){
			System.out.println(champ);
			
			ArrayList<MatchResult> teamHomeMatches = matchDao.getDownloadedPastMatchByChamp(champ);
			HashMap<String, ArrayList<MatchResult>> matchesMapHome = createMatchMap(teamHomeMatches, "H");
			HashMap<String, ArrayList<MatchResult>> matchesMapAway = createMatchMap(teamHomeMatches, "A");
			
			ArrayList<String> teams = teamDao.findByChamp(champ);
			ArrayList<String> teamsCorrect = new ArrayList<String>();
			for (String team : teams) {
				team = team.replace(" ", " ").trim();
				teamsCorrect.add(team);
			}
			
			analyzeWinOdds(champ, matchesMapHome, matchesMapAway, teamsCorrect);
//			analyzeUnderOverOdds(champ, matchesMapHome, matchesMapAway, teamsCorrect);
		}
		
		return null;
	
	}

	private void analyzeUnderOverOdds(ChampEnum champ, HashMap<String, ArrayList<MatchResult>> matchesMapHome, HashMap<String, ArrayList<MatchResult>> matchesMapAway, ArrayList<String> teams ) {
		
//		HashMap<String, GoalsStatsBean> teamToUOStatsHome = new HashMap<String, GoalsStatsBean>();
//		HashMap<String, GoalsStatsBean> teamToUOStatsAway = new HashMap<String, GoalsStatsBean>();
//		HashMap<String, GoalsStatsBean> teamToUOStatsAll = new HashMap<String, GoalsStatsBean>();
		

		for (String teamName : teams) {

			// HOME
//			int lastSeasonDayOddsH = goalsStatsDao.getLastSeasonDayOddsAndPlayingField(teamName, champ, "H");
//			ArrayList<MatchResult> teamHomeMatches = matchDao.getDownloadedPastMatchByChampAndHomeTeamAfterSeasonDay(champ, teamName, lastSeasonDayOddsH);
		
			
			
			
			List<GoalsStatsBean> analyzeTeamResultUoH = analyzeTeamResultUo(teamName, matchesMapHome.get(teamName), champ, "H");
			
			
			// AWAY
//			int lastSeasonDayOddsA = goalsStatsDao.getLastSeasonDayOddsAndPlayingField(teamName, champ, "A");
//			ArrayList<MatchResult> teamAwayMatches = matchDao.getDownloadedPastMatchByChampAndAwayTeamAfterSeasonDay(champ, teamName, lastSeasonDayOddsA);
			List<GoalsStatsBean> analyzeTeamResultUoA = analyzeTeamResultUo(teamName, matchesMapAway.get(teamName), champ, "A");
			
			// TOTAL
			goalsStatsDao.calculateGoalsStatsNoPlayingField(teamName, champ, analyzeTeamResultUoH, analyzeTeamResultUoA);
		}
		
//		for (String teamName : teams) {
//			int lastSeasonDayOdds = goalsStatsDao.getLastSeasonDayOdds(teamName, champ);
//		}
		
//		for (String teamName : teams) {
//			
//		}
		
		
//		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesHome.get(champ).entrySet()) {
//		    analyzeTeamResultUo(entry.getKey(), entry.getValue(), teamToUOStatsHome);
//		}
//		
//		for (Map.Entry<String, ArrayList<MatchResult>> entry : teamToMatchesAway.get(champ).entrySet()) {
//			analyzeTeamResultUo(entry.getKey(), entry.getValue(), teamToUOStatsAway);
//			//System.out.println(entry.getKey() + "/" + entry.getValue());
//		}

		
//		System.out.println(allAnalyzedChampsUoHome);
//		System.out.println(allAnalyzedChampsUoAway);

//		allAnalyzedChampsUoHome.put(champ, teamToUOStatsHome);
//		allAnalyzedChampsUoAway.put(champ, teamToUOStatsAway);
//		allAnalyzedChampsUoAll.put(champ, teamToRangeStatsAll);
		
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_UO_HOME_PATH, allAnalyzedChampsUoHome);
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_UO_AWAY_PATH, allAnalyzedChampsUoAway);
//		IOUtils.write(AppConstants.TEAM_TO_RANGE_STATS_ALL_PATH, allAnalyzedChampsWinAll);
		
		
	}

	private HashMap<String, ArrayList<MatchResult>> createMatchMap(ArrayList<MatchResult> teamHomeMatches, String playingField) {
		HashMap<String, ArrayList<MatchResult>> matchesMap = new HashMap<String, ArrayList<MatchResult>>();
		for (MatchResult match : teamHomeMatches) {
			String team = "";
			if (playingField.equals("H")) {
				team = match.getHomeTeam();
			}
			else { //if (playingField.equals("A")
				team = match.getAwayTeam();
			}
			
			team = team.replace(" ", " ").trim();

			if ( !matchesMap.keySet().contains( team ) )
				matchesMap.put(team, new ArrayList<MatchResult>()) ;

			matchesMap.get(team).add(match);

						
		}
		return matchesMap;
	}

	private List<GoalsStatsBean> analyzeTeamResultUo(String teamName, ArrayList<MatchResult> matches, ChampEnum champ, String playingField) {
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		
		ArrayList<GoalsStatsBean> goalsStatsBeans = new ArrayList<GoalsStatsBean>();

		for (TimeTypeEnum timeType : timeTypes) {
			
			GoalsStatsBean goalsStatsBean = new GoalsStatsBean();//goalsStatsDao.findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champ, timeType, playingField);
			goalsStatsBean.setTimeTypeBean(timeType);
			Integer strikedGoals = 0;
			Integer takenGoals = 0;
			if (matches==  null) {
				continue;
			}
			for (MatchResult m : matches){
				if (m.getFTHG() == null){
					continue;
				}
		
				switch (timeType) {
				case _final:
					if (teamName.equals(m.getHomeTeam())){
						strikedGoals = m.getFTHG();
						takenGoals = m.getFTAG();
					}
					else if (teamName.equals(m.getAwayTeam())){
						takenGoals = m.getFTAG();
						strikedGoals = m.getFTHG();
					}
					break;
					
				case _1:
					if (teamName.equals(m.getHomeTeam())){
						strikedGoals = m.getHTHG();
						takenGoals = m.getHTAG();
					}
					else if (teamName.equals(m.getAwayTeam())){
						takenGoals = m.getHTAG();
						strikedGoals = m.getHTHG();
					}
					break;
					
				case _2:
					if (teamName.equals(m.getHomeTeam())){
						strikedGoals = m.getFTHG() - m.getHTHG();
						takenGoals = m.getFTAG() - m.getHTAG();
					}
					else if (teamName.equals(m.getAwayTeam())){
						takenGoals = m.getFTAG() - m.getHTAG();
						strikedGoals = m.getFTHG() - m.getHTHG();
					}
					break;

				default:
					break;
			}
			

			updateGoalsStats(goalsStatsBean, takenGoals, strikedGoals, teamName);
				
			}
			
			//CALCOLA LE PERCENTUALI
			for (Entry<UoThresholdEnum, UoThresholdStats> entry : goalsStatsBean.getThresholdMap().entrySet()){
				UoThresholdStats value = entry.getValue();
				double totalMatches = goalsStatsBean.getTotalMatches().doubleValue();
				if (totalMatches != 0) {
					value.setUnderPerc( value.getUnderHit().doubleValue() / totalMatches );
					value.setOverPerc( value.getOverHit().doubleValue() / totalMatches );
				}
			}
	
			goalsStatsBeans.add(goalsStatsBean);
		}
		List<GoalsStatsBean> saveGoalsStats = goalsStatsDao.saveGoalsStats(goalsStatsBeans, teamName, champ, playingField);
		
		return saveGoalsStats;
	}

	private void updateGoalsStats(GoalsStatsBean goalsStatsBean, Integer takenGoals, Integer strikedGoals, String teamName) {
		
		Integer allGoals = takenGoals + strikedGoals;
		goalsStatsBean.setTotalMatches(goalsStatsBean.getTotalMatches() + 1);
		goalsStatsBean.setStrikedGoalsTotal(goalsStatsBean.getStrikedGoalsTotal() 	+ strikedGoals);
		goalsStatsBean.setTakenGoalsTotal(goalsStatsBean.getTakenGoalsTotal() 		+ takenGoals);
		goalsStatsBean.setTotalGoals(goalsStatsBean.getTotalGoals() 				+ strikedGoals + takenGoals);
		goalsStatsBean.setTeamName(teamName);
		
		for (Entry<UoThresholdEnum, UoThresholdStats> entry : goalsStatsBean.getThresholdMap().entrySet()){
			UoThresholdEnum key = entry.getKey();
			UoThresholdStats value = entry.getValue();
			
			if (key.getValueNum() > allGoals)
				value.setUnderHit( value.getUnderHit() + 1 );
			else //if (elem.getThreshold() < allGoals)
				value.setOverHit( value.getOverHit() + 1 );

		}
		
	}

	private void analyzeWinOdds(ChampEnum champ, HashMap<String, ArrayList<MatchResult>> matchesMapHome, HashMap<String, ArrayList<MatchResult>> matchesMapAway, ArrayList<String> teams) {
		
		for (String teamName : teams) {
			
			// HOME
//			int lastSeasonDayOddsH = goalsStatsDao.getLastSeasonDayOddsAndPlayingField(teamName, champ, "H"); //USO GOALS PERCHE VANNO DI PARI PASSO
//			ArrayList<MatchResult> teamHomeMatches = matchDao.getDownloadedPastMatchByChampAndHomeTeamAfterSeasonDay(champ, teamName, lastSeasonDayOddsH);
			analyzeTeamResultWin(teamName, matchesMapHome.get(teamName), champ, "H");
			
			
			// AWAY
//			int lastSeasonDayOddsA = goalsStatsDao.getLastSeasonDayOddsAndPlayingField(teamName, champ, "A"); //USO GOALS PERCHE VANNO DI PARI PASSO
//			ArrayList<MatchResult> teamAwayMatches = matchDao.getDownloadedPastMatchByChampAndAwayTeamAfterSeasonDay(champ, teamName, lastSeasonDayOddsA);
			analyzeTeamResultWin(teamName,  matchesMapAway.get(teamName), champ, "A");
			
			
			// TOTAL
			winRangeStatsDao.calculateWinStatsNoPlayingField(teamName, champ);
		}
//		
//		for (String teamName : teams) {
//			
//		}
//
//		for (String teamName : teams) {
//			
//		}
		
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
		
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		List<OddsRange> oddsRangeList = oddsRangeDao.findAll();
		
		
		ArrayList<WinRangeStatsBean> allRanges = new ArrayList<WinRangeStatsBean>();
		for (TimeTypeEnum timeType : timeTypes) {
			ArrayList<WinRangeStatsBean> ranges = createRanges(oddsRangeList, timeType, teamName);
			
			
			//ArrayList<WinRangeStatsBean> ranges = winRangeStatsDao.findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champ, timeType, playingField);
			
			WinRangeStatsBean winRangeStatsBean = new WinRangeStatsBean();
			winRangeStatsBean.setTimeTypeBean(timeType);
			if (matches ==  null) {
				continue;
			}
			
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
				
				
//				if (homeOdds == null || drawOdds == null || awayOdds == null){
//					homeOdds = m.getBWH();
//					drawOdds = m.getBWD();
//					awayOdds = m.getBWA();
//				}
//				if (homeOdds == null || drawOdds == null || awayOdds == null){
//					homeOdds = m.getPSCH();
//					drawOdds = m.getPSCD();
//					awayOdds = m.getPSCA();
//				}	
				if (homeOdds == null || drawOdds == null || awayOdds == null){
					homeOdds = 0.0;
					drawOdds = 0.0;
					awayOdds = 0.0;
				}
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
				
				Double oddsOfTeamAnalyzed = null;
				
				// capisce se la quota su cui andare a inserire la statistica � della squadra in casa o fuoricasa
				if (teamName.equals(m.getHomeTeam()))
					oddsOfTeamAnalyzed = homeOddsAdjusted;
				else 
					oddsOfTeamAnalyzed = awayOddsAdjusted;
				
				updateRangeStats(ranges, result, oddsOfTeamAnalyzed);
				
			}
			
			if (!matches.isEmpty())
				enrichTeamResult(ranges, playingField);
		
			allRanges.addAll(ranges);
		}
		List<WinRangeStats> saveWinRangeStats = winRangeStatsDao.saveWinRangeStats(allRanges, teamName, champ, playingField);
		
	}

	private ArrayList<WinRangeStatsBean> createRanges(List<OddsRange> oddsRangeList, TimeTypeEnum timeType, String teamName) {
		ArrayList<WinRangeStatsBean> ranges = new ArrayList<WinRangeStatsBean>();
		for (OddsRange elemRange : oddsRangeList) {
			WinRangeStatsBean elem = new WinRangeStatsBean();
			elem.setTeamName(teamName);
			elem.setTimeTypeBean(timeType);
			elem.setEdgeUp(elemRange.getValueUp());
			elem.setEdgeDown(elemRange.getValueDown());
			elem.setRange(elemRange.getValueUp() + "-" + elemRange.getValueDown());
			ranges.add(elem);
		}
		return ranges;
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
