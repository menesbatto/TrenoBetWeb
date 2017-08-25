package app.dao.tipologiche;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;

@Service
public class TimeTypeDao {

	@Autowired
	private TimeTypeRepo timeTypeRepo;
	
	private HashMap<String, TimeType> cacheMap;
	
	public TimeType findByValue(String betHouseString) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		TimeType tymeType = cacheMap.get(betHouseString);
		
		return tymeType;
	}

	public void initTable() {
		TimeType _final = new TimeType("final");
		timeTypeRepo.save(_final);
		TimeType _1 = new TimeType("_1");
		timeTypeRepo.save(_1);
		TimeType _2 = new TimeType("_2");
		timeTypeRepo.save(_2);
		
	}
	
	private void initCacheMap() {
		cacheMap = new HashMap<String, TimeType>();
		Iterable<TimeType> findAll = timeTypeRepo.findAll();
		for (Iterator<TimeType> iter = findAll.iterator(); iter.hasNext(); ) {
			TimeType element = iter.next();
			cacheMap.put(element.getValue(), element);
		}	
	}
	
}
