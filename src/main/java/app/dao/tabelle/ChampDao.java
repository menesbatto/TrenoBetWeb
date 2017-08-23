package app.dao.tabelle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchResultParser.modelNew.ChampBean;
import app.utils.ChampEnum;

@Service
public class ChampDao {

	@Autowired
	private ChampRepo champRepo;
	
	private HashMap<ChampEnum, Champ> cacheMap;
	
	public Champ findByNameAndStartYearAndNation(String name, int startYear, String nation) {
		List<Champ> findAll = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
		Champ first = findAll.get(0);
			
		return first;
	}

	public void initTable() {
		Champ premierLeague = new Champ("Premier League", 2017, 2018, "England");
		champRepo.save(premierLeague);
		Champ serieB2016 = new Champ("Serie A", 2017, 2018, "Italy");
		champRepo.save(serieB2016);
		Champ liga2017 = new Champ("Liga", 2017, 2018, "Spain");
		champRepo.save(liga2017);
	}
	
	private void initTimeTypeMap() {
//		timeTypeMap = new HashMap<String, TimeType>();
//		for (Iterator<TimeType> iter = findAll.iterator(); iter.hasNext(); ) {
//			TimeType element = iter.next();
//			timeTypeMap.put(element.getValue(), element);
//		}	
	}

	public Champ findByNameAndStartYearAndNation(ChampBean champ) {
		String name = champ.getName();
		int startYear = champ.getStartYear();
		String nation = champ.getNation();
		List<Champ> findAll = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
		Champ first = findAll.get(0);
			
		return first;
	}

	public Champ findByChampEnum(ChampEnum champEnum) {
		Champ first = findInCache(champEnum);
		if (first == null) {
			String name = champEnum.name();
			int startYear = 2017;	//@TODO
			String nation = champEnum.getNation();
			List<Champ> list = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
			if (list.isEmpty())
				first = saveChamp(name, startYear, nation);
			else 
				first = list.get(0);
			
			cacheMap.put(champEnum, first);
		}
		return first;
	}

	private Champ saveChamp(String name, int startYear, String nation) {
		Champ champ = new Champ();
		champ.setName(name);
		champ.setStartYear(startYear);
		champ.setNation(nation);
		champRepo.save(champ);
		return champ;
	}

	private Champ findInCache(ChampEnum champEnum) {
		if (cacheMap == null) {
			cacheMap = new HashMap<ChampEnum, Champ>();
		}
		return cacheMap.get(champEnum);
	}
	
}
