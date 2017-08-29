package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tipologiche.RankingCriteriaDao;
import app.dao.tipologiche.entities.RankingCriteria;
import app.logic._1_matchesDownlaoder.modelNew.ChampBean;
import app.utils.ChampEnum;
import app.utils.RankCritEnum;

@Service
public class ChampDao {

	@Autowired
	private ChampRepo champRepo;

	@Autowired
	private RankingCriteriaDao rankingCriteriaDao;
	
	private HashMap<ChampEnum, Champ> cacheMap;

	public Champ findByChampEnum(ChampEnum champEnum) {
		Champ first = findInCache(champEnum);
		if (first == null) {
			String name = champEnum.getName();
			int startYear = champEnum.getStartYear();
			String nation = champEnum.getNation();
			List<Champ> list = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
			first = list.get(0);
			
			cacheMap.put(champEnum, first);
		}
		return first;
	}

	

	private Champ findInCache(ChampEnum champEnum) {
		if (cacheMap == null) {
			cacheMap = new HashMap<ChampEnum, Champ>();
		}
		return cacheMap.get(champEnum);
	}
	
	

	
	public void initTable() {
		ChampEnum champEnum = ChampEnum.ENG_PREMIER;
		Champ champEnt = saveChamp(champEnum);
//		ChampImpPos champImpPosEnt =  saveChampImpPos(champEnum, champEnt);
		List<RankingCriteria> rankingCriteriaEnts =  getRankingCriteria(champEnum, champEnt);
		
//		champEnt.setImpPos(champImpPosEnt);
		champEnt.setRankingCriteria(rankingCriteriaEnts);
		champRepo.save(champEnt);
		//...
		System.out.println("");
		
	}
	
	private List<RankingCriteria> getRankingCriteria(ChampEnum champEnum, Champ champEnt) {
		List<RankingCriteria> criteriaEnt = new ArrayList<RankingCriteria>();
		RankingCriteria criteriumEnt;
		for (RankCritEnum criterium : champEnum.getRankCriteria()) {
			criteriumEnt = rankingCriteriaDao.findByValue(criterium.name());
			criteriaEnt.add(criteriumEnt);
		}
		
		return criteriaEnt;
	}



//	private ChampImpPos saveChampImpPos(ChampEnum champEnum, Champ champEnt) {
//		ChampImpPos champImpPos = new ChampImpPos(champEnt, champEnum.getImportantPositions());
//		champImpPosRepo.save(champImpPos);
//		return champImpPos;
//	}

	private Champ saveChamp(ChampEnum champEnum) {
		Champ champEnt = new Champ(	champEnum.getName(),		champEnum.getStartYear(), 	champEnum.getNation(), 
									champEnum.getResultsUrl(), 	champEnum.getNextMatchesUrl());
		champRepo.save(champEnt);
		return champEnt;
	}
	
//	public ChampUrls saveChampDett(ChampEnum champEnum, String resultsUrl, String oddsWinUrl, String oddsUoUrl,	String oddsHtUrl) {
//		Champ champ = findByChampEnum(champEnum);
//		ChampUrls champUrls = new ChampUrls(champ, resultsUrl, oddsWinUrl, oddsUoUrl, oddsHtUrl);
//		champUrlsRepo.save(champUrls);
//		return champUrls;
//	}
//
//	public ChampUrls saveChampUrls(ChampEnum champEnum, Champ champEnt) {
//		ChampUrls champUrls = new ChampUrls(champEnt, champEnum.getResultsUrl(), champEnum.getOddsWinUrl(),	champEnum.getOddsUoUrl(), champEnum.getOddsHtUrl());
//		champUrlsRepo.save(champUrls);
//		return champUrls;
//	}	
	
	
	
	
	@Deprecated
	private Champ saveChamp(String name, int startYear, String nation) {
		Champ champ = new Champ();
		champ.setName(name);
		champ.setStartYear(startYear);
		champ.setNation(nation);
		champRepo.save(champ);
		return champ;
	}
	

	@Deprecated
	public Champ findByNameAndStartYearAndNation(String name, int startYear, String nation) {
		List<Champ> findAll = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
		Champ first = findAll.get(0);
		
		return first;
	}
	
	
	@Deprecated
	public Champ findByChampBean(ChampBean champ) {
		String name = champ.getName();
		int startYear = champ.getStartYear();
		String nation = champ.getNation();
		List<Champ> findAll = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
		Champ first = findAll.get(0);
			
		return first;
	}

	@Deprecated
	private void initTimeTypeMap() {
//		timeTypeMap = new HashMap<String, TimeType>();
//		for (Iterator<TimeType> iter = findAll.iterator(); iter.hasNext(); ) {
//			TimeType element = iter.next();
//			timeTypeMap.put(element.getValue(), element);
//		}	
	}
	
}
