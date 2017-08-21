package app.dao.tabelle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;
import app.logic.app._0_eventsOddsDownloader.model.ChampBean;

@Service
public class ChampDao {

	@Autowired
	private ChampRepo champRepo;
	
	private HashMap<String, TimeType> timeTypeMap;
	
	public Champ findByNameAndStartYearAndNation(String name, int startYear, String nation) {
		List<Champ> findAll = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
		Champ first = findAll.get(0);
			
		return first;
	}

	public void initTable() {
		Champ serieA2017 = new Champ("Serie A", 2017, 2018, "Italy");
		champRepo.save(serieA2017);
		Champ serieB2016 = new Champ("Serie B", 2016, 2017, "Italy");
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
	
}
