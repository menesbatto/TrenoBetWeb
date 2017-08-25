package app.logic._1_matchResultParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.MatchoDao;
import app.logic._0_nextMatchesDownloader.MatchesDownloader;
import app.logic._1_matchResultParser.model.BetHouseEnum;
import app.logic._1_matchResultParser.model.EhEnum;
import app.logic._1_matchResultParser.model.EhTimeType;
import app.logic._1_matchResultParser.model.MatchResult;
import app.logic._1_matchResultParser.model.TimeTypeEnum;
import app.logic._1_matchResultParser.model.UoFull;
import app.logic._1_matchResultParser.model.UoLeaf;
import app.logic._1_matchResultParser.model.UoThresholdEnum;
import app.logic._1_matchResultParser.model.UoTimeType;
import app.logic._1_matchResultParser.model._1x2Full;
import app.logic._1_matchResultParser.model._1x2Leaf;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.HttpUtils;
import app.utils.IOUtils;
import app.utils.Utils;

@Service
public class PastMatchesDownlaoder {

	@Autowired
	private MatchoDao matchDao;
	
//	private HashMap<ChampEnum, ArrayList<MatchResult>> allMatchResults;
//	private HashMap<ChampEnum, ArrayList<String>> allTeams;
	
	
	
//	private void initStaticFields() {
//		allMatchResults = retrieveAllMatchResults();
//		allTeams = retrieveTeams();
//	}
	@Autowired
	private MatchesDownloader matchesDownloader;
	
	public void execute(){
		matchesDownloader.execute("Past");
	}
	
	public void executeOld(){
//		initStaticFields();
		
//		ArrayList<MatchResult> matchResult;
		ArrayList<String> teams;

		//Ciclo su tutti i campionati per i risultati dei match giocati e le quote
		for (ChampEnum champ : ChampEnum.values()){

			int savedMatchesNum = downloadChampionshipResults(champ.getResultsUrl(), champ);
			//allMatchResults.put(champ, matchResult);
			
//			teams = getTeams(matchResult);
//			allTeams.put(champ, teams);
//			IOUtils.write(AppConstants.TEAMS_PATH, allTeams);

//			saveAllMatchesResults();
		}
		
//		IOUtils.write(AppConstants.TEAMS_PATH, allTeams);
//		IOUtils.write(AppConstants.MATCHES_RESULTS_PATH, allMatchResults);
			
	}

	
	private int downloadChampionshipResults(String champSuffixUrl, ChampEnum champ) {
		Document doc = null;
		
		String champSubsetUrl = champSuffixUrl; 
		doc = HttpUtils.getHtmlPage(champSubsetUrl);

//		ArrayList<MatchResult> alreadySavedMatches = allMatchResults.get(champ);
//		int size = 0;
//		if (alreadySavedMatches != null){
//			size = alreadySavedMatches.size();
//		}else {
//			alreadySavedMatches = new ArrayList<MatchResult>();
//		}
		
		int size = matchDao.getDownloadedMatchByChamp(champ);
		
		Element matchesTable;
//		ArrayList<MatchResult> matchesResultsSubset;
		if (size < 50){
			matchesTable = doc.getElementById("tournamentTable");
//			matchesResultsSubset = createMatches(matchesTable, champSubsetUrl, champ);
//			alreadySavedMatches.addAll(matchesResultsSubset);
//			saveAllMatchesResults();
			int savedMatchesNum = createMatches(matchesTable, champSubsetUrl, champ);
			size = savedMatchesNum;
		}
		
		// Calcolo numero di pagine in cui sono divisi i risultati
		Element paginationElement = doc.getElementById("pagination");
		if (paginationElement != null) {	// se è null stiamo alle prime giornate
			String resultsPagesString = paginationElement.getElementsByTag("a").last().attr("x-page");
			Integer resultsPages = Integer.valueOf(resultsPagesString);
			int i =  ( size / 50 ) + 1; 
			for (; i <= resultsPages; i++){
				champSubsetUrl = champSuffixUrl + "/#/page/" + i + "/";
				doc = HttpUtils.getHtmlPage(champSubsetUrl);
				matchesTable = doc.getElementById("tournamentTable");
//				matchesResultsSubset = createMatches(matchesTable, champSubsetUrl, champ);
//				alreadySavedMatches.addAll(matchesResultsSubset);
//				saveAllMatchesResults();
				int savedMatchesNum = createMatches(matchesTable, champSubsetUrl, champ);
				size += savedMatchesNum;
			}
		}
		
		return size;
	}
	
	
	
	private int createMatches(Element matchesTable, String champSubsetUrl, ChampEnum champ) {
//		ArrayList<MatchResult> matchesResults = new ArrayList<MatchResult>();
		Elements tableRows = matchesTable.getElementsByTag("tr");
		Date matchDate = null;
		MatchResult matchResult = null;
		int matchNum = 1;
		
		// VIA
		Element obj = tableRows.get(0); // remember first item
		Element obj2 = tableRows.get(1); // remember first item
		Element obj3 = tableRows.get(2); // remember first item
		Element obj4 = tableRows.get(3); // remember first item
		tableRows.clear(); // clear complete list
		tableRows.add(obj); // add first item
		tableRows.add(obj2); // add first item
		tableRows.add(obj3); // add first item
		tableRows.add(obj4); // add first item
		// VIA 
		
		
		
		int savedMatches = 0;
		for (Element row : tableRows){
//			if (row.hasClass("dark")){
//				//ricerca della nazione, ma gia ce l'ho.
//			}
			if (row.hasClass("nob-border")){
				String dateString = row.getElementsByClass("datet").text();
				if (dateString.contains("Today"))
					dateString = dateString.substring(7) + " 2017";
				else if (dateString.contains("Yesterday"))
					dateString = dateString.substring(11) + " 2017";
				
				matchDate = Utils.convertDateString(dateString); 
			}
			else if (row.hasClass("deactivate")){
					long startTime = System.nanoTime();
					System.out.println("Match " + matchNum++);
				matchResult = createMatchResult(row, matchDate, champSubsetUrl); 
				matchResult.setChamp(champ);
				matchDao.save(matchResult);
				savedMatches++;
//				matchesResults.add(matchResult);
				
					long currentTime = System.nanoTime();
					long duration = (currentTime - startTime);  //divide by 1000000 to get milliseconds.
					System.out.print("DONE\t" + duration / 1000000);
					System.out.println();
			}
			
		}
		
		
		return savedMatches;
	}


	
//	private void saveAllMatchesResults() {
//		IOUtils.write(AppConstants.MATCHES_RESULTS_PATH, allMatchResults);
//	}

	private static MatchResult createMatchResult(Element row, Date matchDate, String champSubsetUrl) {
		
		MatchResult m = new MatchResult();

		
//		<tr class="odd deactivate" xeid="dQnalo0H">
//		 <td class="table-time datet t1495997100-1-1-0-0 ">18:45</td>
//		 <td class="name table-participant"><a href="/soccer/italy/serie-a/crotone-lazio-dQnalo0H/"><span class="bold">Crotone</span> - Lazio</a></td>
//		 <td class="center bold table-odds table-score">3:1</td>
//		 <td class="odds-nowrp" xoid="E-2g6u2xv464x0x5cmkr" xodd="xz88fxzp	result-ok 	"><a href="" onclick="globals.ch.togle(this , 'E-2g6u2xv464x0x5cmkr');return false;" xparam="odds_text">2.70</a></td>
//		 <td class="odds-nowrp" xoid="E-2g6u2xv498x0x0" 	xodd="cz8fczpe				"><a href="" onclick="globals.ch.togle(this , 'E-2g6u2xv498x0x0');return false;" xparam="odds_text">3.75</a></td>
//		 <td class="odds-nowrp" xoid="E-2g6u2xv464x0x5cmks" xodd="xzpfxztc				"><a href="" onclick="globals.ch.togle(this , 'E-2g6u2xv464x0x5cmks');return false;" xparam="odds_text">2.43</a></td>
//		 <td class="center info-value">7</td>
//		</tr>

		
		// GENERAL INFO
		String time = row.getElementsByClass("datet").get(0).text();
		Integer hours = Integer.valueOf(time.split(":")[0]);
		Integer minutes = Integer.valueOf(time.split(":")[1]);
		matchDate.setHours(hours);
		matchDate.setMinutes(minutes);
		m.setMatchDate(matchDate);
		
		DateFormat df = new SimpleDateFormat("dd MM yyyy");
		String dateString = df.format(matchDate);
		
		m.setDate(dateString);
		
		Element teamsElement = row.getElementsByClass("table-participant").get(0);
		
		String teams = teamsElement.text();
		String homeTeam = teams.split(" - ")[0];
		m.setHomeTeam(homeTeam);
		String awayTeam = teams.split(" - ")[1];;
		m.setAwayTeam(awayTeam);
		
		String result = row.getElementsByClass("table-score").get(0).text();
		Integer homeScoreScoredGoals = Integer.valueOf(result.split(":")[0]);
		m.setFTHG(homeScoreScoredGoals);
		Integer awayScoredGoals = Integer.valueOf(result.split(":")[1]);
		m.setFTAG(awayScoredGoals);
		
		Double H = Double.valueOf(row.getElementsByClass("odds-nowrp").get(0).text());
		Double D = Double.valueOf(row.getElementsByClass("odds-nowrp").get(1).text());
		Double A = Double.valueOf(row.getElementsByClass("odds-nowrp").get(2).text());
		m.setPSCH(H);
		m.setPSCD(D);
		m.setPSCA(A);
		_1x2Leaf avg1x2Odds = new _1x2Leaf(H, D, A);
		m.get_1x2().get(TimeTypeEnum._final).setAvg1x2Odds(avg1x2Odds);
		
		
		
		String finalResult;
		if (homeScoreScoredGoals > awayScoredGoals)
			finalResult = "H";
		else if (homeScoreScoredGoals == awayScoredGoals)
			finalResult = "D";
		else
			finalResult = "A";
			
		m.setFTR(finalResult);
		

		
		// ADDITIONAL MATCH INFO
		String matchSuffixUrl = teamsElement.getElementsByTag("a").attr("href");
		
		// 		1x2
		String matchUrl = AppConstants.SITE_URL + matchSuffixUrl;
		populateMatch1X2(m, matchUrl);
//		System.out.println(m.get_1x2());
		
		//		ASIAN HANDICAP
//		populateMatchAH(m, matchUrl);

		// 		UNDER OVER
//		populateMatchUO(m, matchUrl);
//		System.out.println(m.getUo());
		
		// 		EUROPEAN HANDICAP
//		populateMatchEH(m, matchUrl);
//		System.out.println(m.getEh().get(TimeTypeEnum._2));
		
		// 		DOUBLE CHANCE
//		populateMatchDC(m, matchUrl);
		
		// 		CORRECT SCORE
//		populateMatchCS(m, matchUrl);
		
		// 		DRAW NO BET
//		populateMatchDNB(m, matchUrl);
		
//		System.out.println(m);
		System.out.print(".");

		return m;
	}

	


	

	private static void populateMatchAH(MatchResult m, String matchUrl) {
		String asianHandicapSuffix = "/#ah;2";
		
	}

	private static void populateMatchUO(MatchResult m, String matchUrl) {
		// U O - FINAL
		for (TimeTypeEnum timeType : TimeTypeEnum.values()){
			populateMatchUOSpecificType(m, matchUrl, timeType);
		}
		
	}

	private static void populateMatchUOSpecificType(MatchResult m, String matchUrl, TimeTypeEnum timeType) {
		String infoUrl = matchUrl + timeType.getUoUrlSuffix();
		Document matchPage = HttpUtils.getHtmlPageUO(infoUrl);
		Elements thresholds = matchPage.getElementById("odds-data-table").getElementsByClass("table-container");

		
		
		for (Element treshold : thresholds){
			
			// Recupera l'elemento corretto skippando quelli inutili
			Elements uoAvgElems = treshold.getElementsByClass("table-header-light");
			if (uoAvgElems.size() == 0 ){
				continue;
			}
			Element uoAvgElem = uoAvgElems.get(0);
			
			String underAvgString = uoAvgElem.getElementsByTag("span").get(1).text();
			if( underAvgString.equals("") ){
				//qui becca il 0,75 che è roba nascosta...skippalo col try catch oppure in modo diverso
				continue;
			}
			
			
			// Recupera l'enum della soglia
			String uoThresholdRaw = treshold.getElementsByTag("strong").get(0).text();
			String uoThresholdString = uoThresholdRaw.substring(uoThresholdRaw.indexOf("+")).replace(".", "_").replace("+", "_");
			UoThresholdEnum uoThresholdEnum = UoThresholdEnum.valueOf(uoThresholdString);

			// Setta l'avg
			UoTimeType uoTimeType = m.getUo().get(timeType);
			UoFull uoThreshold = getUoThreshold(uoThresholdEnum, uoTimeType);
			Double underAvg = Double.valueOf(underAvgString);
			Double overAvg = Double.valueOf(uoAvgElem.getElementsByTag("span").get(2).text());
			UoLeaf uoAvgMatch = new UoLeaf(underAvg , overAvg);
			uoThreshold.setAvgUoOdds(uoAvgMatch);
			
			// Setta per i valori di UO per tutte le betHouse
			Elements allTrs = treshold.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			for (Element tr : allTrs){
				Elements elementsMatchingText = tr.getElementsByAttributeValueStarting("title", "Go to");
				if (elementsMatchingText.size() > 0){
					String betHouseName = getBetHouseName(tr);
					Double underBetRoom = Double.valueOf(tr.getElementsByTag("div").get(2).text());
					Double overBetRoom = Double.valueOf(tr.getElementsByTag("div").get(1).text());

					BetHouseEnum betHouse = BetHouseEnum.valueOf(betHouseName);
					
					UoLeaf uoMatch =  new UoLeaf(underBetRoom, overBetRoom);
					uoThreshold.getBetHouseToUoOdds().put(betHouse, uoMatch);
				}
			}
			
		}
	}

	private static UoFull getUoThreshold(UoThresholdEnum uoThresholdEnum, UoTimeType uoType) {
		UoFull uoThreshold = null;
		uoThreshold = uoType.getMap().get(uoThresholdEnum);
//		switch (uoThresholdEnum) {
//		case _0_5:
//			uoThreshold = uoType.getUo0_5(); 	break;
//		case _1_5:
//			uoThreshold = uoType.getUo1_5(); 	break;
//		case _2_5:
//			uoThreshold = uoType.getUo2_5();	break;
//		case _3_5:
//			uoThreshold = uoType.getUo3_5(); 	break;
//		case _4_5:
//			uoThreshold = uoType.getUo4_5(); 	break;
//		case _5_5:
//			uoThreshold = uoType.getUo5_5(); 	break;
//		case _6_5:
//			uoThreshold = uoType.getUo6_5(); 	break;
//		case _7_5:
//			uoThreshold = uoType.getUo7_5(); 	break;
//		case _8_5:
//			uoThreshold = uoType.getUo7_5(); 	break;
//		case _9_5:
//			uoThreshold = uoType.getUo7_5(); 	break;
//		case _10_5:
//			uoThreshold = uoType.getUo7_5(); 	break;
//		default:
//			break;
//		}
		return uoThreshold;
	}

	private static String getBetHouseName(Element tr) {
		return tr.getElementsByClass("name").text().split("\\.")[0];
	}
	
	private static void populateMatchDNB(MatchResult m, String matchUrl) {
		String drawNoBetSuffix = "/#dnb;2";
		
	}
	
	private static void populateMatchEH(MatchResult m, String matchUrl) {
		// European Handicap - FINAL
		for (TimeTypeEnum timeType : TimeTypeEnum.values()){
			populateMatchEHSpecificType(m, matchUrl, timeType);
		}
		
	}
	
	private static void populateMatchEHSpecificType(MatchResult m, String matchUrl, TimeTypeEnum timeType) {
		String infoUrl = matchUrl + timeType.getEhUrlSuffix();
		Document matchPage = HttpUtils.getHtmlPageEH(infoUrl);
//		System.out.println(matchPage);
		Elements handicaps = matchPage.getElementById("odds-data-table").getElementsByClass("table-container");

		
		for (Element handicap : handicaps){
			
			// Recupera l'elemento corretto skippando quelli inutili
			Elements uoAvgElems = handicap.getElementsByClass("table-header-light");
			if (uoAvgElems.size() == 0 ){
				continue;
			}
			Element uoAvgElem = uoAvgElems.get(0);
			
			String odds1AvgString = uoAvgElem.getElementsByTag("span").get(1).text();
			if( odds1AvgString.equals("") ){
				//qui becca il 0,75 che è roba nascosta...skippalo col try catch oppure in modo diverso
				continue;
			}
			
			
			// Recupera l'enum della soglia
			String ehThresholdRaw = handicap.getElementsByTag("strong").get(0).text();
			String ehThresholdString = ehThresholdRaw.substring(ehThresholdRaw.lastIndexOf(" ")+1).replace("-", "m").replace("+", "p");
			EhEnum ehThresholdEnum = EhEnum.valueOf(ehThresholdString);

			// Setta l'avg
			EhTimeType ehTimeType = m.getEh().get(timeType);
			_1x2Full ehThreshold = getEhThreshold(ehThresholdEnum, ehTimeType);
			
			Double odds1Avg = Double.valueOf(odds1AvgString);
			Double oddsXAvg = Double.valueOf(uoAvgElem.getElementsByTag("span").get(2).text());
			Double odds2Avg = Double.valueOf(uoAvgElem.getElementsByTag("span").get(3).text());
			_1x2Leaf _1x2AvgMatch = new _1x2Leaf(odds1Avg , oddsXAvg, odds2Avg);
			
			ehThreshold.setAvg1x2Odds(_1x2AvgMatch);
			
			// Setta per i valori di UO per tutte le betHouse
			Elements allTrs = handicap.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			for (Element tr : allTrs){
				Elements elementsMatchingText = tr.getElementsByAttributeValueStarting("title", "Go to");
				if (elementsMatchingText.size() > 0){
					String betHouseName = getBetHouseName(tr);
					Double odd1 = Double.valueOf(tr.getElementsByTag("div").get(3).text());
					Double oddX = Double.valueOf(tr.getElementsByTag("div").get(2).text());
					Double odd2 = Double.valueOf(tr.getElementsByTag("div").get(1).text());

					BetHouseEnum betHouse = BetHouseEnum.valueOf(betHouseName);
					
					_1x2Leaf _1x2Match = new _1x2Leaf(odd1 , oddX, odd2);
					ehThreshold.getBetHouseTo1x2Odds().put(betHouse, _1x2Match);
				}
			}
			
		}
		
	}


	private static _1x2Full getEhThreshold(EhEnum ehThresholdEnum, EhTimeType ehTimeType) {
		_1x2Full handicap = null;
		handicap = ehTimeType.getMap().get(ehThresholdEnum);
//		switch (ehThresholdEnum) {
//		case m1:
//			handicap = ehTimeType.getM1(); 	break;
//		case m2:
//			handicap = ehTimeType.getM2(); 	break;
//		case m3:
//			handicap = ehTimeType.getM3(); 	break;
//		case m4:
//			handicap = ehTimeType.getM4(); 	break;
//		case m5:
//			handicap = ehTimeType.getM5(); 	break;
//		case m6:
//			handicap = ehTimeType.getM6(); 	break;
//		case m7:
//			handicap = ehTimeType.getM6(); 	break;
//		case m8:
//			handicap = ehTimeType.getM6(); 	break;
//		case m9:
//			handicap = ehTimeType.getM6(); 	break;
//		case p1:
//			handicap = ehTimeType.getP1(); 	break;
//		case p2:
//			handicap = ehTimeType.getP2(); 	break;
//		case p3:
//			handicap = ehTimeType.getP3(); 	break;
//		case p4:
//			handicap = ehTimeType.getP4(); 	break;
//		case p5:
//			handicap = ehTimeType.getP5(); 	break;
//		case p6:
//			handicap = ehTimeType.getP6(); 	break;
//		case p7:
//			handicap = ehTimeType.getP6(); 	break;
//		case p8:
//			handicap = ehTimeType.getP6(); 	break;
//		case p9:
//			handicap = ehTimeType.getP6(); 	break;
//		default:
//			break;
//		}
		return handicap;
	}

	private static void populateMatchDC(MatchResult m, String matchUrl) {
		String doubleChanceSuffix = "/#double;2";
		
	}
	
	private static void populateMatchCS(MatchResult m, String matchUrl) {
		String correctScoreSuffix = "/#cs;2";
		
	}
	
	private static void populateMatch1X2(MatchResult m, String matchUrl) {
		
		for (TimeTypeEnum timeType : TimeTypeEnum.values()){
			populateMatch1X2SpecificType(m, matchUrl, timeType);
		}
		
		
		
	}

	private static void populateMatch1X2SpecificType(MatchResult m, String matchUrl, TimeTypeEnum timeType) {
		
		// 1 X 2 - FINAL
		String infoUrl = matchUrl + timeType.get_1x2urlSuffix();
		Document infoPage = HttpUtils.getHtmlPage(infoUrl);
		
		String halfTimeResultString = infoPage.getElementsByClass("result").get(0).text().split("\\(")[1].split(",")[0];
		Integer hthg = Integer.valueOf(halfTimeResultString.split(":")[0]);
		Integer htag = Integer.valueOf(halfTimeResultString.split(":")[1]);
		m.setHTHG(hthg);
		m.setHTAG(htag);
		
		Elements betHouses = infoPage.getElementById("col-content").getElementsByClass("detail-odds").get(0).getElementsByClass("lo");
		
		_1x2Full _1x2 = m.get_1x2().get(timeType);

		Elements averageTds = infoPage.getElementById("col-content").getElementsByClass("detail-odds").get(0).getElementsByClass("aver").first().getElementsByTag("td");
		Double oddsH = Double.valueOf(averageTds.get(1).text());
		Double oddsD = Double.valueOf(averageTds.get(2).text());
		Double oddsA = Double.valueOf(averageTds.get(3).text());
		_1x2Leaf _1x2avgMatch = new _1x2Leaf(oddsH, oddsD, oddsA);
		_1x2.setAvg1x2Odds(_1x2avgMatch);
		
		for (Element house : betHouses){
			String betHouseName = getBetHouseName(house);
			BetHouseEnum betHouse = BetHouseEnum.valueOf(betHouseName);
			oddsH = Double.valueOf(house.getElementsByClass("right").get(0).text());
			oddsD = Double.valueOf(house.getElementsByClass("right").get(1).text());
			oddsA = Double.valueOf(house.getElementsByClass("right").get(2).text());
			
			
			_1x2Leaf _1x2Match = new _1x2Leaf(oddsH, oddsD, oddsA);
			
			_1x2.getBetHouseTo1x2Odds().put(betHouse, _1x2Match);
			
		}
		
		
		
	}

	private static ArrayList<String> getTeams(ArrayList<MatchResult> matchesResults) {
		Set<String> teams = new HashSet<String>();
		for (int i = 0; i < 30; i++){
			teams.add(matchesResults.get(i).getHomeTeam());
			teams.add(matchesResults.get(i).getAwayTeam());
		}
		ArrayList<String> teamsList = new ArrayList<String>(teams);
		return teamsList;
	}
	
	public static HashMap<ChampEnum, ArrayList<String>>  retrieveTeams() {
		HashMap<ChampEnum, ArrayList<String>> teams = IOUtils.read(AppConstants.TEAMS_PATH, HashMap.class);
		if (teams == null){
			teams = new HashMap<ChampEnum, ArrayList<String>>();
		}
		return teams;
	}
	
	public static HashMap<ChampEnum, ArrayList<MatchResult>> retrieveAllMatchResults() {
		HashMap<ChampEnum,ArrayList<MatchResult>>  matches = IOUtils.read(AppConstants.MATCHES_RESULTS_PATH, HashMap.class);
		if (matches == null){
			matches = new HashMap<ChampEnum,ArrayList<MatchResult>>();
		}
		return matches;
	}
	
	
}
